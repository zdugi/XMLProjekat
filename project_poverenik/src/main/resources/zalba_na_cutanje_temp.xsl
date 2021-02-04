<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:zalba_na_cutanje="http://ftn.uns.ac.rs/xml_zalba_na_cutanje" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
    <xsl:template match="/">
        <html xmlns:zalba_na_cutanje="http://ftn.uns.ac.rs/xml_zalba_na_cutanje" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
            <head>
                    <style>
                        body {
                        font-family: Arial Unicode MS, FreeSans;
                        }
                        p {
                        width: 700px;
                        margin: 0 auto;
                        }

                        .datum, .trazilac, .adresa, .kontakt {
                        width: 300px;
                        }
                        
                        .desni {
                        float: right;
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
            <br />
                <div>
                    <p class="center"><b>ЖАЛБА КАДА ОРГАН ВЛАСТИ НИЈЕ ПОСТУПИО/ није поступио у целости/ ПО ЗАХТЕВУ ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)</b>
                    </p><br/>
                    <p style="margin-top: 50px;" align="center"><b><xsl:value-of select="//zalba_na_cutanje:Primalac"/> <xsl:text>, </xsl:text></b><br/></p>
                    <p style="margin-top: 50px;" align="left">Адреса за пошту:
                        <xsl:value-of select="//zalba_na_cutanje:Adresa_primaoca/opste:Mesto"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//zalba_na_cutanje:Adresa_primaoca/opste:Ulica"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//zalba_na_cutanje:Adresa_primaoca/opste:Broj"/>,
                    </p>
                    <p align="left">У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим:</p>
                </div>
                <p style="margin-top: 50px;" align="center"><b>Ж А Л Б У</b><br/>против</p>
                <p style="margin-top: 50px;" align="center">
                    <xsl:value-of select="//zalba_na_cutanje:Telo_zalbe/zalba_na_cutanje:Organ/opste:Naziv"/> <xsl:text>, </xsl:text>
                </p>
                <br/>
                <p align="center">(навести назив органа)</p>
                <br/>
                <p align="center">због тога што орган власти: </p>
                <br/>
                <xsl:for-each select="//zalba_na_cutanje:Telo_zalbe/zalba_na_cutanje:Razlozi_zalbe/zalba_na_cutanje:Razlog_zalbe">
                    <p align="center"><b><xsl:if test="current()/@odabrano = 'true'"><b>[x]</b><xsl:text> </xsl:text></xsl:if> <xsl:value-of select="text()"/></b></p>

                </xsl:for-each>
                <p style="margin-top: 20px;" align="left">по мом захтеву за слободан приступ информацијама од јавног значаја који сам поднео том органу дана по мом захтеву за слободан приступ информацијама од јавног значаја који сам поднео том органу  дана
                    <xsl:value-of select="//zalba_na_cutanje:Telo_zalbe/zalba_na_cutanje:Datum_podnosenja_zahteva"/> <xsl:text> </xsl:text>
                    године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације о /у вези са :
                </p>
                <br/>

                <p style="text-decoration: underline; margin-top: 3px;"><xsl:value-of select="//zalba_na_cutanje:Telo_zalbe/zalba_na_cutanje:Podaci_o_zahtevu_i_informacija"/></p>
                <p align="center" style="font-size: 12px;">(навести податке о захтеву и информацији/ама)</p>
                <br/>
                <p align="left"> На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма. </p>
                <p align="left"> Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти. </p>
                <p align="left"><b><xsl:value-of select="//zalba_na_cutanje:Telo_zalbe/zalba_na_cutanje:Napomena"/> <xsl:text> </xsl:text></b><br/></p>
                <br/>
                <br/>
                
                <div class="dno">
                    
                    <div class="levi">
                        
                        <div class="datum">
                            <div style="text-decoration: underline; text-align: center;"><xsl:value-of select="//zalba_na_cutanje:Dodatne_informacije/opste:Datum"/></div>
                            <div style="text-align: center;">Datum</div>
                        </div>
                        
                    </div>
                    
                    
                    
                    <div class="desni">
                        <div class="trazilac">
                            <div style="text-decoration: underline; text-align: center;">
                                <xsl:value-of select="//zalba_na_cutanje:Dodatne_informacije/opste:Trazilac/opste:Osoba/opste:Ime"/> <xsl:text> </xsl:text>
                                <xsl:value-of select="//zalba_na_cutanje:Dodatne_informacije/opste:Trazilac/opste:Osoba/opste:Prezime"/>
                            </div>
                            <div style="text-align: center;">Trazilac</div>
                        </div>

                        <br/>
                        
                        <div class="adresa">
                            <div style="text-decoration: underline; text-align: center;">
                                <xsl:value-of select="//zalba_na_cutanje:Dodatne_informacije/opste:Trazilac/opste:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                                <xsl:value-of select="//zalba_na_cutanje:Dodatne_informacije/opste:Trazilac/opste:Adresa/opste:Broj"/> <xsl:text> </xsl:text>
                                <xsl:value-of select="//zalba_na_cutanje:Dodatne_informacije/opste:Trazilac/opste:Adresa/opste:Mesto"/>
                            </div>
                            <div style="text-align: center;">Adresa</div>
                        </div>

                        <br/>
                        
                        <div class="kontakt">
                            <div></div>
                            <div style="text-decoration: underline; text-align: center;"><xsl:value-of select="//zalba_na_cutanje:Dodatne_informacije/opste:Trazilac/opste:Kontakt"/></div>
                            <div style="text-align: center;">Kontakt</div>
                            
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>