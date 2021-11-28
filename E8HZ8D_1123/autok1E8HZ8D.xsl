<?xml version = "1.0" encoding = "UTF-8"?>

<xsl:stylesheet version = "1.0" 
xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">   

    <xsl:template match="/">
        <html>
            <body>
                <h2>Rendszamok arak szerint rendezve</h2>
                <ul>  
	                <xsl:for-each select="autok/auto">
				      <xsl:sort select="ar" />
				        <li>Rendszam: <xsl:value-of select="@rsz"/>
				        <br />Ar: <xsl:value-of select="ar" data-type="number" order="ascending" /></li>
			    	</xsl:for-each>
		    	</ul>  
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>