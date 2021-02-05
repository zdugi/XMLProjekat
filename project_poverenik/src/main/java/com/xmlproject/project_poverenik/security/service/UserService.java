package com.xmlproject.project_poverenik.security.service;

import com.xmlproject.project_poverenik.model.xml_korisnik.Korisnik;
import com.xmlproject.project_poverenik.model.xml_korisnik.ObjectFactory;
import com.xmlproject.project_poverenik.model.xml_korisnik.TAutorizacijaInformacije;
import com.xmlproject.project_poverenik.model.xml_korisnik.TLicneInformacije;
import com.xmlproject.project_poverenik.model.xml_opste.TAdresa;
import com.xmlproject.project_poverenik.model.xml_opste.TOsoba;
import com.xmlproject.project_poverenik.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import pojo.KorisnikDTO;

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
		ta.setRole(userDTO.role);
        newUser.setAutorizacijaInformacije(ta);
		TLicneInformacije tl = new TLicneInformacije();
		TOsoba osoba = new TOsoba();
		osoba.setIme(userDTO.ime);
		osoba.setPrezime(userDTO.prezime);
		tl.setKontakt(userDTO.kontakt);
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
