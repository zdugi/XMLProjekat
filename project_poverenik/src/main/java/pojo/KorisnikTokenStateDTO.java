package pojo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "korisnik_token")
public class KorisnikTokenStateDTO {

    @XmlElement(name = "accessToken", required = true)
    public String accessToken;

    @XmlElement(name = "expiresIn", required = true)
    public int expiresIn;

    public KorisnikTokenStateDTO(String jwt, int expiresIn) {
        this.accessToken = jwt;
        this.expiresIn = expiresIn;
    }

    public KorisnikTokenStateDTO() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
