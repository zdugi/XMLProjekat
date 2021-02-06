# XMLProjekat

## Portovi

Organ
- platforma 8089
- baza 8111

Poverenik
- platforma 8081
- baza 8080

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

<code>
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=VAŠ GMAIL
spring.mail.password=LOZINKA
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

server.port=8099

cxf.path=/ws/
</code>

**NAPOMENA**: Klijentske aplikacije čine isključivo statičke datoteke, pa su za njihovo serviranje iskorišćeni serveri platformi - zbog organičenja resursa. Očuvana je logička struktura zadata u specifikaciji (RESTful komunikacija korišćenjem XML datoteka).

## Registracija na sistem

Platforma organa vlasti sadrži ugrađen sistem za registraciju na klijentskoj strani, dok je na platformi poverenika potrebno zahtev poslati eksterno (korišćenjem Postmana).

<code> ... kod za gradjanina ... </code>

<code> ... kod za poverenika ... </code>
