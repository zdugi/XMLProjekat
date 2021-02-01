<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:izvestaj="http://ftn.uns.ac.rs/xml_izvestaj" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
    <xsl:template match="/">
        <html xmlns:izvestaj="http://ftn.uns.ac.rs/xml_izvestaj" xmlns:opste="http://ftn.uns.ac.rs/xml_opste">
            <head>
                
            </head>
            <body>
                <h1>Izvestaj</h1>
                <p><b>Datum: </b><xsl:value-of select="//izvestaj:Izvestaj/izvestaj:DatumPodnosenja"/></p>
                <table border="1">
                    <thead>
                        <th>Broj uveženih zahteva</th>
                        <th>Broj odbijenih zahteva</th>
                        <th>Broj podnetih zahteva</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td align="center"><xsl:value-of select="//Izvestaj/PodnetiZahtevi - //Izvestaj/OdbijeniZahtevi"/></td>
                            <td align="center"><xsl:value-of select="//Izvestaj/OdbijeniZahtevi"/></td>
                            <td align="center"><xsl:value-of select="//Izvestaj/PodnetiZahtevi"/></td>
                        </tr>
                    </tbody>
                </table>
                <br />
                <h2>Odbijeni zahtevi</h2>
                
                <xsl:for-each select="//izvestaj:Izvestaj/izvestaj:SadrzinaOdbijenihZalbi/izvestaj:SadrzinaOdbijeneZalbe">
                    <p><xsl:value-of select="current()"/></p>
                    <hr/>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>