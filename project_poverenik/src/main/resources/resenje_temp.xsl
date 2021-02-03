<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:resenje="http://ftn.uns.ac.rs/xml_resenje" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
    <xsl:template match="/">
        <html xmlns:resenje="http://ftn.uns.ac.rs/xml_resenje" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
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
                <p>Решење: жалба <xsl:value-of select="//resenje:Zalba_Prihvacena/text()"/> </p>
                <p class="levi">Бр. <xsl:value-of select="//resenje:Zalba/resenje:Broj/text()"/></p>
                <p class="desni">Датум: <xsl:value-of select="//resenje:Datum/text()"/></p>
                <br/>
                <!--<p>
                    <xsl:value-of select="//resenje:ZalilacOsoba/opste:Ime"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//resenje:ZalilacOsoba/opste:Prezime"/> <xsl:text> </xsl:text>
                    gospodjetina se zalila na
                    <xsl:value-of select="//resenje:Organ/opste:Naziv"/> <xsl:text> </xsl:text>
                </p>-->
                <p>
                    <!--<xsl:value-of select="//resenje:Organ/opste:Naziv"/> <xsl:text> </xsl:text>-->

                    <!--<xsl:value-of select="//resenje:Uvod/text()"/> <xsl:text> </xsl:text>
                    <xsl:for-each select="//resenje:Uvod/text()">
                        <xsl:value-of select="normalize-space(.)"/>

                    </xsl:for-each>-->
                    <xsl:for-each select="//resenje:Uvod">
                        <xsl:value-of select="normalize-space(.)"/>

                    </xsl:for-each>
                    <!--<xsl:value-of select="Resenje/Resenje_Ukratko"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="Resenje/Obrazlozenje"/> <xsl:text> </xsl:text>-->
                </p>
                <br/>
                <p> Р Е Ш Е Њ Е </p>
                <p><xsl:value-of select="//resenje:Resenje_Ukratko"/></p>
                <br/>
                <p>О б р а з л о ж е њ е</p>
                <p> <xsl:value-of select="//resenje:Obrazlozenje"/> </p>
                <br/>
                <p class="desni">ПОВЕРЕНИК</p>
                <p> <xsl:value-of select="//resenje:Poverenik/opste:Ime"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//resenje:Poverenik/opste:Prezime"/> </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>