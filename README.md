# XMLProjekat

## Portovi

DB
- 8080

Organ
- 8089

Poverenik
- 8081

MailServer
- 8099

# Pokretanje

Za pokretanje pojedinačne platforme, potrebno je:
- pokrenuti pribavljanje Maven paketa
- uključiti ručno dodatne biblioteke iz **libs** direktorijuma
- podići Tomcat server sa instancama Fuseki i Exists bazama
- kreirati dataset **EDataset** u Fusekiju

Za pokretanje Mail servera, potrebno je:
- pokrenuti pribavljanje Maven paketa
- ubaciti **application.properties** u sledećem formatu

```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=VAŠ GMAIL
spring.mail.password=LOZINKA
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
server.port=8099
cxf.path=/ws/
```

**NAPOMENA**: Klijentske aplikacije čine isključivo statičke datoteke, pa su za njihovo serviranje iskorišćeni serveri platformi - zbog organičenja resursa. Očuvana je logička struktura zadata u specifikaciji (RESTful komunikacija korišćenjem XML datoteka).

**NAPOMENA**: Zbog ograničenih resursa, projekat je konfigurisan tako da se konektuje na jedan Tomcat server na kom su razdvojene baze za svaku platformu da bi se zadovoljila logička struktura zadata u specifikaciji.
