<?xml version = "1.0" encoding = "UTF-8"?>

<xsl:stylesheet version = "1.0" 
xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">   

    <xsl:template match = "/"> 
    	
      <html> 
         <body> 
            <h2>Students</h2> 
				
            <table border = "1"> 
               <tr bgcolor = "#9acd32"> 
                  <th>ID</th> 
                  <th>Keresztnev</th> 
                  <th>Vezeteknev</th> 
                  <th>Becenev</th> 
                  <th>Kor</th>
                  <th>Fokozat</th>
               </tr> 
				
               <xsl:for-each select="class/student"> 
                  <tr> 
                     <td>
                        <xsl:value-of select = "@id"/> 
                     </td> 
						
                     <td><xsl:value-of select = "keresztnev"/></td> 
                     <td><xsl:value-of select = "vezeteknev"/></td> 
                     <td><xsl:value-of select = "becenev"/></td> 
                     <td><xsl:value-of select = "kor"/></td> 
                     <xsl:choose>
                        <xsl:when test="fizetes&ge;'60000'">
                           <td>Magas</td>
                        </xsl:when>
                        <xsl:when test="fizetes&ge;'50000'">
                           <td>Kozepes</td>
                        </xsl:when>
                        <xsl:otherwise>
                           <td>Alacsony</td>
                        </xsl:otherwise>
                  </xsl:choose>
						
                  </tr> 
               </xsl:for-each> 
					
            </table> 
         </body> 
      </html> 
   </xsl:template>  
</xsl:stylesheet>