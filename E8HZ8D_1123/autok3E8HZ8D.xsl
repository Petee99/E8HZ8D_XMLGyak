<?xml version = "1.0" encoding = "UTF-8"?>

<xsl:stylesheet version = "1.0" 
xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">   

    <xsl:template match="/">
        <html>
            <body>
                <h2>Ennyi elem van a dokumentumban:</h2>
                <ul>  
	            	<li><xsl:value-of select="count(*)" /></li>
	            </ul>  
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>