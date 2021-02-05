<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:obavestenje="http://ftn.uns.ac.rs/xml_obavestenja" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
    <xsl:template match="/">
        <html>
        <head>
            <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
            <style>
                body {
                    font-family: Arial Unicode MS, FreeSans;
                }
                div {
                    padding-left : 50px;
                    padding-right : 50px;
                }
            </style>
        </head>
        <body>

                <div >
                    <p>Broj predmeta : <xsl:value-of select="//@Broj_predmeta"/><xsl:text></xsl:text> </p>
                    <p>Datum : <xsl:value-of select="//obavestenje:Datum"/> <xsl:text></xsl:text></p>
                </div>

                <div >
                    <p style="text-decoration: underline; margin-top: 100px;" >
                        <xsl:value-of select="//obavestenje:Organ/opste:Naziv"/> <xsl:text>, </xsl:text><br/>
                        <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Broj"/><br/>
                        <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Mesto"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Postanski_broj"/> <xsl:text> </xsl:text><br/>
                        <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Drzava"/><xsl:text></xsl:text>
                    </p>
                    <p >naziv i sedište organa </p>
                </div>
                <div >
                    <p style="text-decoration: underline; margin-top: 100px;">
                        <xsl:value-of select="//obavestenje:Podaci_podnosioca/obavestenje:Osoba/opste:Ime"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//obavestenje:Podaci_podnosioca/obavestenje:Osoba/opste:Prezime"/> <xsl:text> </xsl:text><br/>
                        <xsl:value-of select="//obavestenje:Podaci_podnosioca/obavestenje:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//obavestenje:Podaci_podnosioca/obavestenje:Adresa/opste:Broj"/><br/>
                        <xsl:value-of select="//obavestenje:Podaci_podnosioca/obavestenje:Adresa/opste:Mesto"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//obavestenje:Podaci_podnosioca/obavestenje:Adresa/opste:Postanski_broj"/> <xsl:text> </xsl:text><br/>
                        <xsl:value-of select="//obavestenje:Podaci_podnosioca/obavestenje:Adresa/opste:Drzava"/><xsl:text></xsl:text>
                    </p>
                    <p >naziv i sedište podnosioca</p>
                </div>
                <div class = "item">
                    <p style="margin-top: 50px; font-size : 16;" align="center"><b>O B A V E Š T E NJ E </b><br/></p>
                    <p style="margin-top: 50px;" align="center"><b>o stavljanju na uvid dokumenta koji sadrži<br/>
                    traženu informaciju i o izradi kopije</b><br/></p>
                    <p>
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Zakon/opste:Naziv_zakona"/>
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Godina"/>
                        god., kojim ste tražili uvid u dokument/e sa informacijama o / u vezi sa:<br/>
                        <p align="center"><xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Opis"/><xsl:text> </xsl:text><br/>
                        (<xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Opis/@Savet"/>)<br/>
                        </p>
                        obaveštavamo vas da dana
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Dan"/>
                        , u časova
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Sati"/>
                        , odnosno u vremenu od
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Pocetni_sat"/>
                        do
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Zavrsni_sat"/>
                        časova, u prostorijama organa
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Adresa/opste:Broj"/>,
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Adresa/opste:Mesto"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Adresa/opste:Postanski_broj"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Adresa/opste:Drzava"/><xsl:value-of select="text()"/>
                        , kancelarija br.
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Kancelarija"/>
                        možete izvršiti uvid u dokument/e u kome je sadržana tražena informacija.
                        <br/>Tom prilikom, na vaš zahtev, može vam se izdati i kopija dokumenta sa traženom informacijom.
                        <br/><br/>Troškovi su utvrđeni Uredbom Vlade Republike Srbije („Sl. glasnik RS“, br. 8/06), i to: kopija strane A4 formata iznosi 3 dinara, A3 formata 6 dinara, CD 35 dinara, diskete 20 dinara, DVD 40 dinara, audio-kaseta – 150 dinara, video-kaseta 300 dinara, pretvaranje jedne strane dokumenta iz fizičkog u elektronski oblik – 30 dinara.
                        <br/><br/>Iznos ukupnih troškova izrade kopije dokumenta po vašem zahtevu iznosi
                        <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Suma"/><xsl:value-of select="text()"/>
                        dinara i uplaćuje se na žiro-račun Budžeta Republike Srbije br. 840-742328-843-30, s pozivom na broj 97 – oznaka šifre opštine/grada gde se nalazi organ vlasti (iz Pravilnika o uslovima i načinu vođenja računa – „Sl. glasnik RS“, 20/07... 40/10).

                        <br/><br/>
                        <xsl:if test="//obavestenje:Dostavljeno/@Arhivi = 'true'"> Arhivi </xsl:if>
                        <br/>
                        <xsl:if test="//obavestenje:Dostavljeno/@Imenovanom = 'true'"> Imenovanom </xsl:if>
                    </p>
                </div>

        </body>
    </html>
    </xsl:template>
</xsl:stylesheet>