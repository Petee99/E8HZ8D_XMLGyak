<?xml version = "1.0" encoding = "UTF-8"?>

<xsl:stylesheet version = "1.0" 
xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">   

    <xsl:template match="/">
        <html>
            <body>
                <h2>Ennyi 30000-nel dragabb auto van:</h2>
                <ul>  
	            	<li><xsl:value-of select="count(autok/auto[ar>=30000])" /></li>
	            </ul>  
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>