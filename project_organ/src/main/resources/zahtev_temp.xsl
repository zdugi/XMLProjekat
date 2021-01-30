<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:zahtev="http://ftn.uns.ac.rs/xml_zahtev" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
    <xsl:template match="/">
        <html xmlns:zahtev="http://ftn.uns.ac.rs/xml_zahtev" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
            <head>
                    <style>
                        p, .dno {
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
                        margin-top: 100px;
                        margin-bottom: 100px;
                        }
                        
                        .adresa, .kontakt {
                        margin-top: 30px;
                        }
                    </style>
            </head>
            <body>
                <div>
                    <p style="text-decoration: underline; margin-top: 100px;" align="center">
                        <xsl:value-of select="//zahtev:Organ/opste:Naziv"/> <xsl:text>, </xsl:text>
                        <xsl:value-of select="//zahtev:Organ/opste:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//zahtev:Organ/opste:Adresa/opste:Broj"/>, <xsl:value-of select="//zahtev:Organ/opste:Adresa/opste:Mesto"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//zahtev:Organ/opste:Adresa/opste:Postanski_broj"/> <xsl:text> </xsl:text>
                        <xsl:value-of select="//zahtev:Organ/opste:Adresa/opste:Drzava"/>
                    </p>
                    <p align="center">naziv i sedište organa kome se zahtev upućuje</p>
                </div>
                <p style="margin-top: 50px;" align="center"><b>Z A H T E V </b><br/><b>za pristup informaciji od javnog značaja</b></p>
                <p style="margin-top: 50px;">
                    <xsl:value-of select="//zahtev:Telo_zahteva/zahtev:Paragraf/text()"/>
                </p>
                <br/>
                <xsl:for-each select="//zahtev:Telo_zahteva/zahtev:Paragraf/zahtev:Tipovi_zahteva/zahtev:Tip_zahteva">
                    <p style="text-indent: 50px;"><xsl:if test="current()/@Odabrano = 'true'">[X]</xsl:if> <xsl:value-of select="text()"/></p>
                    
                    <!--<p><b><xsl:value-of select="node()/zahtev:Tip_dostave"/></b></p>-->
                    <xsl:for-each select="node()/zahtev:Tip_dostave">
                        <p style="text-indent: 100px;"><xsl:if test="current()/@Odabrano = 'true'">[X]</xsl:if> <xsl:value-of select="node()"/></p>
                    </xsl:for-each>
                </xsl:for-each>
     
                <p style="text-indent: 50px; margin-top: 20px;"><xsl:value-of select="//zahtev:Telo_zahteva/zahtev:Informacije/zahtev:Naslov"/></p>
                <p style="text-decoration: underline; margin-top: 3px;"><xsl:value-of select="//zahtev:Telo_zahteva/zahtev:Informacije/zahtev:Opis"/></p>
                <p align="center" style="font-size: 12px;">(<xsl:value-of select="//zahtev:Telo_zahteva/zahtev:Informacije[@Savet]"/>)</p>
                
                
                <div class="dno">
                    
                    <div class="levi">
                        
                        <div class="datum">
                            <div style="text-decoration: underline; text-align: center;"><xsl:value-of select="//zahtev:Dodatne_informacije/opste:Datum"/></div>
                            <div style="text-align: center;">Datum</div>
                        </div>
                        
                    </div>
                    
                    
                    
                    <div class="desni">
                        <div class="trazilac">
                            <div style="text-decoration: underline; text-align: center;">
                                <xsl:value-of select="//zahtev:Dodatne_informacije/opste:Trazilac/opste:Osoba/opste:Ime"/> <xsl:text> </xsl:text>
                                <xsl:value-of select="//zahtev:Dodatne_informacije/opste:Trazilac/opste:Osoba/opste:Prezime"/>
                            </div>
                            <div style="text-align: center;">Trazilac</div>
                        </div>
                        
                        <div class="adresa">
                            <div style="text-decoration: underline; text-align: center;">
                                <xsl:value-of select="//zahtev:Dodatne_informacije/opste:Trazilac/opste:Adresa/opste:Ulica"/> <xsl:text> </xsl:text>
                                <xsl:value-of select="//zahtev:Dodatne_informacije/opste:Trazilac/opste:Adresa/opste:Broj"/> <xsl:text>, </xsl:text>
                                <xsl:value-of select="//zahtev:Dodatne_informacije/opste:Trazilac/opste:Adresa/opste:Mesto"/>
                            </div>
                            <div style="text-align: center;">Adresa</div>
                        </div>
                        
                        <div class="kontakt">
                            <div></div>
                            <div style="text-decoration: underline; text-align: center;"><xsl:value-of select="//zahtev:Dodatne_informacije/opste:Trazilac/opste:Kontakt"/></div>
                            <div style="text-align: center;">Kontakt</div>
                            
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>