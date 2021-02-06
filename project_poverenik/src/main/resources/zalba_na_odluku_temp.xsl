<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:zalbanaodluku="http://ftn.uns.ac.rs/xml_zalbanaodluku" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
    <xsl:template match="/">
        <html xmlns:zalbanaodluku="http://ftn.uns.ac.rs/xml_zalbanaodluku" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
            <head>
                <style>
                    body {
                    font-family: Arial Unicode MS, FreeSans;
                    }
                    p {
                    width: 700px;
                    margin: 0 auto;
                    }
                    .center {
                        text-align: center;
                    }

                    .datum, .trazilac, .adresa, .kontakt {
                    width: 300px;
                    }

                    .desni {
                    float: right;
                    }
                    .underline {
                    text-decoration: underline;
                    }

                    .levi {
                    float: left;
                    }

                    .dno {
                    overflow: auto;
                    margin-bottom: 100px;
                    }
                </style>
            </head>
            <body>
                <br/>
                <p class="center"><b>ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ
                    ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ ЗА ПРИСТУП ИНФОРМАЦИЈИ</b>
                </p>
                <br/>
                <p><b>Поверенику за информације од јавног значаја и заштиту података о личности</b></p>
                <!--<p>     <xsl:for-each select="//zalbanaodluku:Adresa_primaoca">
                        <xsl:value-of select="normalize-space(.)"/>
                    </xsl:for-each>

                </p>-->
                <p> Адреса за пошту:
                    <xsl:value-of select="//zalbanaodluku:Adresa_primaoca/opste:Ulica"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//zalbanaodluku:Adresa_primaoca/opste:Broj"/> <xsl:text>, </xsl:text>
                    <xsl:value-of select="//zalbanaodluku:Adresa_primaoca/opste:Mesto"/> </p>
                <br/>
                <br/>
                <p class="center"><b>Ж А Л Б А</b></p>
                <br/>
                <p class="center underline">
                    <xsl:value-of select="//zalbanaodluku:Telo_zalbe_na_odluku/zalbanaodluku:ZalilacOsoba/opste:Ime"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="//zalbanaodluku:Telo_zalbe_na_odluku/zalbanaodluku:ZalilacOsoba/opste:Prezime"/>

                    <!--<xsl:for-each select="zalbanaodluku:Telo_zalbe_na_odluku/zalbanaodluku:ZalilacOsoba">
                        <p>mozee</p>
                        <xsl:value-of select="//zalbanaodluku:Telo_zalbe_na_odluku/zalbanaodluku:ZalilacOrgan/opste:Naziv"/>
                        <xsl:text>, </xsl:text>
                        <xsl:value-of select="//zalbanaodluku:ZalilacOrgan/opste:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//zalbanaodluku:ZalilacOrgan/opste:Adresa/opste:Broj"/> <xsl:text>, </xsl:text>
                        <xsl:value-of select="//zalbanaodluku:ZalilacOrgan/opste:Adresa/opste:Mesto"/>
                    </xsl:for-each>-->

                    <xsl:value-of select="//zalbanaodluku:Telo_zalbe_na_odluku/zalbanaodluku:ZalilacOrgan/opste:Naziv"/>
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="//zalbanaodluku:ZalilacOrgan/opste:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//zalbanaodluku:ZalilacOrgan/opste:Adresa/opste:Broj"/> <xsl:text>, </xsl:text>
                    <xsl:value-of select="//zalbanaodluku:ZalilacOrgan/opste:Adresa/opste:Mesto"/>

                </p>
                <p class="center">(Име, презиме, односно назив, адреса и седиште жалиоца)</p>
                <br/>
                <p class="center">против решења-закључка </p>

                <p class="center underline"><xsl:value-of select="//zalbanaodluku:OrganDonosilacOdluke/opste:Naziv"/></p>
                <p class="center">(назив органа који је донео одлуку)</p>

                <br/>
                <p>Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону,
                    одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана
                    <b><xsl:value-of select="//zalbanaodluku:Datum_podnosenja_zahteva"/></b> године и тако ми ускраћено-онемогућено
                    остваривање уставног и законског права на слободан приступ информацијама од јавног значаја.
                    Oдлуку побијам у целости, односно у делу којим
                    <b><xsl:value-of select="//zalbanaodluku:OpisZalbe"/></b> јер није заснована на Закону о слободном приступу информацијама од јавног значаја.
                </p>
                <p>На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.</p>
                <p>Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст. 1. Закона о слободном приступу информацијама од јавног значаја.
                </p>
                <br/>
                <br/>
                <p>У <xsl:value-of select="//zalbanaodluku:DodatneInformacije/opste:Mesto"/></p>
                <p>Дана <xsl:value-of select="//zalbanaodluku:DodatneInformacije/opste:Datum"/></p>
                <br/>
                <p class="underline"><xsl:value-of select="//zalbanaodluku:DodatneInformacije/opste:Trazilac/opste:Osoba/opste:Ime"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="//zalbanaodluku:DodatneInformacije/opste:Trazilac/opste:Osoba/opste:Prezime"/>
                </p>
                <p>Подносилац жалбе / Име и презиме</p>
                <br/>
                <p class="underline"><xsl:value-of select="//zalbanaodluku:DodatneInformacije/opste:Trazilac/opste:Kontakt"/></p>
                <p>Други подаци за контакт</p>
                <br/>
                <p class="underline"> <xsl:value-of select="//zalbanaodluku:DodatneInformacije/opste:Trazilac/opste:Adresa/opste:Mesto"/></p>
                <p>Адреса</p>
                <br/>
                <br/>
                <p><b>Напомене:</b></p>
                <xsl:for-each select="//zalbanaodluku:Napomene">
                    <xsl:value-of select="." separator="&#10;"/> idel to sine <xsl:text>&#10;</xsl:text>

                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>