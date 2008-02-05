<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html"/>

<xsl:template match="/xss">
	<xsl:for-each select="attack">

<html>
<head><title><xsl:value-of select="name"/></title>
</head>
<body>
<H1><xsl:value-of select="name"/></H1>
<H4><xsl:value-of select="label"/></H4>
<P><xsl:value-of select="desc"/></P>
<P><I><xsl:value-of select="browser"/></I></P>
<xsl:value-of select="code" disable-output-escaping="yes"/>
</body>
</html>

	</xsl:for-each>
</xsl:template>

</xsl:stylesheet>
