# XMLProjekat

## Portovi

Platforma organa 8089
Baza organa 8111

Platforma poverenika 8081
Baza poverenika 8080

# Pokretanje

Za pokretanje pojedinačne platforme, potrebno je:
- pokrenuti pribavljanje Maven paketa
- uključiti ručno dodatne biblioteke iz **libs** direktorijuma
- podići Tomcat server sa instancama Fuseki i Exists bazama
- kreirati dataset **EDataset** u Fusekiju

**NAPOMENA**: Klijentske aplikacije čine isključivo statičke datoteke, pa su za njihovo serviranje iskorišćeni serveri platformi - zbog organičenja resursa. Očuvana je logička struktura zadata u specifikaciji (RESTful komunikacija korišćenjem XML datoteka).

## Registracija na sistem

Platforma organa vlasti sadrži ugrađen sistem za registraciju na klijentskoj strani, dok je na platformi poverenika potrebno zahtev poslati eksterno (korišćenjem Postmana).

<code> ... kod za gradjanina ... </code>
<code> ... kod za poverenika ... </code>
