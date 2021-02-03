<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:obavestenje="http://ftn.uns.ac.rs/xml_obavestenja" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
    <xsl:template match="/">
        <html xmlns:zahtev="http://ftn.uns.ac.rs/xml_obavestenje" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
        <head>
        </head>
        <body>
            <div>
                <p><xsl:value-of select="//obavestenje:Datum"/> <xsl:text></xsl:text></p>
            </div>
            <div>
                <p>
                    <xsl:value-of select="//obavestenje:Organ/opste:Naziv"/> <xsl:text>, </xsl:text>
                    <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Broj"/>, 
                    <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Mesto"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Postanski_broj"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//obavestenje:Organ/opste:Adresa/opste:Drzava"/>
                </p>
                <p align="center">naziv i sedište organa </p>
            </div>
            <div>
                <p>
                    <xsl:value-of select="//obavestenje:Podaci_podnosioca/opste:Naziv"/> <xsl:text>, </xsl:text>
                    <xsl:value-of select="//obavestenje:Podaci_podnosioca/opste:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//obavestenje:Podaci_podnosioca/opste:Adresa/opste:Broj"/>, 
                    <xsl:value-of select="//obavestenje:Podaci_podnosioca/opste:Adresa/opste:Mesto"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//obavestenje:Podaci_podnosioca/opste:Adresa/opste:Postanski_broj"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//obavestenje:Podaci_podnosioca/opste:Adresa/opste:Drzava"/>
                </p>
                <p align="center">naziv i sedište podnosioca</p>
            </div>
            <div>
                <p>
                    <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Zakon/opste:Naziv_zakona"/>
                    <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Godina"/>
                    god., kojim ste tražili uvid u dokument/e sa informacijama o / u vezi sa:
                    <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Opis/@Savet"/>
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
                    Tom prilikom, na vaš zahtev, može vam se izdati i kopija dokumenta sa traženom informacijom.
                    Troškovi su utvrđeni Uredbom Vlade Republike Srbije („Sl. glasnik RS“, br. 8/06), i to: kopija strane A4 formata iznosi 3 dinara, A3 formata 6 dinara, CD 35 dinara, diskete 20 dinara, DVD 40 dinara, audio-kaseta – 150 dinara, video-kaseta 300 dinara, pretvaranje jedne strane dokumenta iz fizičkog u elektronski oblik – 30 dinara.
                    Iznos ukupnih troškova izrade kopije dokumenta po vašem zahtevu iznosi
                    <xsl:value-of select="//obavestenje:Telo_obavestenja/obavestenje:Suma"/><xsl:value-of select="text()"/>
                    dinara i uplaćuje se na žiro-račun Budžeta Republike Srbije br. 840-742328-843-30, s pozivom na broj 97 – oznaka šifre opštine/grada gde se nalazi organ vlasti (iz Pravilnika o uslovima i načinu vođenja računa – „Sl. glasnik RS“, 20/07... 40/10).
                </p>
            </div>
            <div>
                <xsl:value-of select="//obavestenje:Dostavljeno/@Arhivi"/>
                <xsl:value-of select="//obavestenje:Dostavljeno/@Imenovanom"/>
            </div>
        </body>
    </html>
    </xsl:template>
</xsl:stylesheet>