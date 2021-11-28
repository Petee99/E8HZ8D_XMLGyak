<?xml version = "1.0" encoding = "UTF-8"?>

<xsl:stylesheet version = "1.0" 
xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">   

	<xsl:key name="varosok" match="auto" use="tulaj/varos" />

    <xsl:template match="/">
        <html>
            <body>
                <h2>Varosonkenti autok darabszama es osszara</h2>
                <ul>  
	                <xsl:for-each select="/autok/auto[count(. | key('varosok', tulaj/varos)[1]) = 1]">
				        <li>Varos: <xsl:value-of select="tulaj/varos"/><br />
				        Darab: <xsl:value-of select="count(key('varosok', tulaj/varos))"/><br />
				        Osszar: <xsl:value-of select="sum(key('varosok', tulaj/varos)/ar)"/></li>
			    	</xsl:for-each>
		    	</ul>  
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>