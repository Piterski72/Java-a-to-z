<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <xsl:element name="entries">
            <xsl:apply-templates select="entries"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="entries/entry">
        <entry>
            <xsl:attribute name="field">
                <xsl:value-of select="field"/>
            </xsl:attribute>
        </entry>

    </xsl:template>
</xsl:stylesheet>