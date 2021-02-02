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
                <p>Dobar dan svima iz resenja</p>
                <p>
                    <xsl:value-of select="//resenje:ZalilacOsoba/opste:Ime"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="//resenje:ZalilacOsoba/opste:Prezime"/> <xsl:text> </xsl:text>
                    gospodjetina se zalila na
                    <xsl:value-of select="//resenje:Organ/opste:Naziv"/> <xsl:text> </xsl:text>
                </p>
                <p>
                    <xsl:value-of select="//resenje:Organ/opste:Naziv"/> <xsl:text> </xsl:text>

                    <xsl:value-of select="//resenje:Uvod/text()"/> <xsl:text> </xsl:text>
                    <xsl:for-each select="//resenje:Uvod/text()">
                        <xsl:value-of select="normalize-space(.)"/>
                    </xsl:for-each>
                    <xsl:value-of select="Resenje/Resenje_Ukratko"/> <xsl:text> </xsl:text>
                    <xsl:value-of select="Resenje/Obrazlozenje"/> <xsl:text> </xsl:text>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>