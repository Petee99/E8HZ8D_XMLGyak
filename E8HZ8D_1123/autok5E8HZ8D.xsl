<?xml version = "1.0" encoding = "UTF-8"?>

<xsl:stylesheet version = "1.0" 
xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">   

	<xsl:key name="markak" match="auto" use="tipus" />

    <xsl:template match="/">
        <html>
            <body>
                <h2>Tipusok darabszama csokkeno sorrendben</h2>
                <ul>  
	                <xsl:for-each select="/autok/auto[count(. | key('markak', tipus)[1]) = 1]">
				      <xsl:sort select="count(key('markak', tipus))" order="descending" />
				        <li>Tipus: <xsl:value-of select="tipus"/><br />
				        Darab: <xsl:value-of select="count(key('markak', tipus))"/></li>
			    	</xsl:for-each>
		    	</ul>  
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>