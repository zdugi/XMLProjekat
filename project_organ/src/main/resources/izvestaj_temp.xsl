<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:izvestaj="http://ftn.uns.ac.rs/xml_izvestaj" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
    <xsl:template match="/">
        <html xmlns:izvestaj="http://ftn.uns.ac.rs/xml_izvestaj" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
            <head>
                <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
            </head>
            <body>
                <h1>Izvestaj</h1>
                <p><b>Datum: </b><xsl:value-of select="//izvestaj:Izvestaj/izvestaj:DatumPodnosenja"/></p>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Broj uvaženih zahteva</th>
                            <th>Broj odbijenih zahteva</th>
                            <th>Broj podnetih zahteva</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td align="center"><xsl:value-of select="//izvestaj:Izvestaj/izvestaj:PodnetiZahtevi - //izvestaj:Izvestaj/izvestaj:OdbijeniZahtevi"/></td>
                            <td align="center"><xsl:value-of select="//izvestaj:Izvestaj/izvestaj:OdbijeniZahtevi"/></td>
                            <td align="center"><xsl:value-of select="//izvestaj:Izvestaj/izvestaj:PodnetiZahtevi"/></td>
                        </tr>
                    </tbody>
                </table>
                <br />
                <h2>Žalbe na odbijene zahteve (ukupno <xsl:value-of select="count(//izvestaj:Izvestaj/izvestaj:SadrzinaOdbijenihZalbi/izvestaj:SadrzinaOdbijeneZalbe)"/>)</h2>

                <xsl:for-each select="//izvestaj:Izvestaj/izvestaj:SadrzinaOdbijenihZalbi/izvestaj:SadrzinaOdbijeneZalbe">
                    <p><xsl:value-of select="current()"/></p>
                    <hr/>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>