package com.organ.project_organ.security.service;


import com.organ.project_organ.model.xml_korisnik.Korisnik;
import com.organ.project_organ.model.xml_korisnik.ObjectFactory;
import com.organ.project_organ.model.xml_korisnik.TAutorizacijaInformacije;
import com.organ.project_organ.model.xml_korisnik.TLicneInformacije;
import com.organ.project_organ.model.xml_opste.TAdresa;
import com.organ.project_organ.model.xml_opste.TOsoba;
import com.organ.project_organ.pojo.KorisnikDTO;
import com.organ.project_organ.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.math.BigInteger;
import java.util.UUID;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			String id = userRepository.findByUsername(username);
			Korisnik k = userRepository.getOne(id);
			return k;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
    }

    public void registerUser(KorisnikDTO userDTO) throws Exception {
        Korisnik newUser = (new ObjectFactory()).createKorisnik();
		TAutorizacijaInformacije ta = new TAutorizacijaInformacije();
		ta.setUsername(userDTO.email);
		ta.setPassword(passwordEncoder.encode(userDTO.password));

		switch (userDTO.role) {
			case "official":
				userDTO.role = "ROLE_OFFICIAL";
				break;
			default:
				userDTO.role = "ROLE_CITIZEN";
		}

		ta.setRole(userDTO.role);
        newUser.setAutorizacijaInformacije(ta);
		TLicneInformacije tl = new TLicneInformacije();
		TOsoba osoba = new TOsoba();
		osoba.setIme(userDTO.ime);
		osoba.setPrezime(userDTO.prezime);
		tl.setOsoba(osoba);

		TAdresa adresa = new TAdresa();
		adresa.setUlica(userDTO.ulica);
		adresa.setBroj(BigInteger.valueOf(Long.valueOf(userDTO.broj)));
		TAdresa.Mesto mesto = new TAdresa.Mesto();
		mesto.setValue(userDTO.mesto);
		adresa.setMesto(mesto);
		TAdresa.Drzava drzava = new TAdresa.Drzava();
		drzava.setValue(userDTO.drzava);
		adresa.setDrzava(drzava);
		tl.setAdresa(adresa);

		adresa.setPostanskiBroj(BigInteger.valueOf(Long.valueOf(userDTO.postanskiBroj)));
		newUser.setLicneInformacije(tl);
		String id = UUID.randomUUID().toString();
		newUser.setId(id);
        userRepository.save(id, newUser);
    }

}
