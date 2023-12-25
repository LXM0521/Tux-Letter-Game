<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:pro='http://myGame/tux'
    version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Profile joueur</title>
            </head>
            <body>
                <hr color="blue"/> 
                <h1 style="color:blue; text-align:center;"> Profil du joueur </h1>
                <hr color="blue"/>
                <br/>
                <hr color="red"/> 
                <h2 style="color:red; text-align:center;">Informations joueurs</h2>
                <hr color="red"/> 
                <br/>
                <table>
                    <tr>
                        <th>Nom :</th>
                        <td>
                            <xsl:value-of  select="//pro:nom"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Avatar :</th>
                        <td>
                            <xsl:value-of  select="//pro:avatar"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Date :</th>
                        <td>
                            <xsl:value-of  select="//pro:anniversaire"/>
                        </td>
                    </tr>
                </table>
                <br/>
                <hr color="red"/> 
                <h2 style="color:red; text-align:center;">Parties :</h2>
                <hr color="red"/> 
                <xsl:apply-templates select="//pro:partie"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="pro:partie">
        <hr color="black"/> 
        <table>
            <tr>
                <th>Date :</th>
                <td>
                    <xsl:value-of  select="./@date"/>
                </td>
            </tr>
            <tr>
                <th>Temps :</th>
                <td>
                    <xsl:value-of  select="./pro:temps/text()"/>
                </td>
            </tr>
            <tr>
                <th>Mot :</th>
                <td>
                    <xsl:value-of  select="./pro:mot/@numNiveau"/>
                </td>
                <td>
                    <xsl:value-of  select="./pro:mot/text()"/>
                </td>
            </tr>
        </table>
        <hr color="black"/>
    </xsl:template>
</xsl:stylesheet>
