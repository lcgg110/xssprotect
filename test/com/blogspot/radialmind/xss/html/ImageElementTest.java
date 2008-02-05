/**
 * Copyright 2007 Gerard Toonstra
 * 
 * Licensed under the terms of the Apache Software License v2
 *
 * This file is part of the XSS Protect library
 */

package com.blogspot.radialmind.xss.html;

import com.blogspot.radialmind.BaseTestCase;

// Verified vulnerability in :
// 		Firefox 2.0		not vulnerable
//
// Needs checking in IE6.0 

public class ImageElementTest extends BaseTestCase {
	String inputImage = "<html><head><title>test</title></head><input type=\"IMAGE\" src=\"javascript:alert('XSS');\"></html>";
	String imgSrc = "<html><head><title>test</title></head><img src=\"javascript:alert('XSS');\"></html>";
	String imgNoQuotesSemicolon = "<html><head><title>test</title></head><img src=javascript:alert('XSS');></html>";
	String imgDynSrc = "<html><head><title>test</title></head><img dynsrc=\"javascript:alert('XSS');\"></html>";
	String imgLowSrc = "<html><head><title>test</title></head><img lowsrc=\"javascript:alert('XSS');\"></html>";
	String imgEmbeddedCmd = "<html><head><title>test</title></head><img src=\"http://www.thesiteyouareon.com/somecommand.php?somevariables=maliciouscode\"/></html>";
	String imgStyleExpression = "<html><img style=\"exp/*<xss style='no\\xss:noxss(\"*//*\");xss:&#101;x&#x2F;*XSS*//*/*/pression(alert(\"XSS\"))'>></html>";
	String imgVBscript = "<html><head><title>test</title></head><img src='vbscript:msgbox(\"XSS\")'></html>";
	String caseInsensitive1 = "<html><head><title>test</title></head><img src=JaVaScRiPt:alert('XSS')></html>";
	String caseInsensitive2 = "<html><head><title>test</title></head><img src=VbScRiPt:alert('XSS')></html>";
	String htmlEntities = "<html><head><title>test</title></head><img src=javascript:alert(&quot;XSS&quot;)></html>";
	String graveAccents = "<html><head><title>test</title></head><img src=`javascript:alert(\"RSnake says, 'XSS'\")`></html>";
	String charCode = "<html><head><title>test</title></head><img src=javascript:alert(String.fromCharCode(88,83,83))></html>";
	String imgUnicode = "<html><head><title>test</title></head><img src=&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116;&#58;&#97;&#108;&#101;&#114;&#116;&#40;&#39;&#88;&#83;&#83;&#39;&#41;></html>";
	String imgLongUnicode = "<html><head><title>test</title></head><img src=&#0000106&#0000097&#0000118&#0000097&#0000115&#0000099&#0000114&#0000105&#0000112&#0000116&#0000058&#0000097&#0000108&#0000101&#0000114&#0000116&#0000040&#0000039&#0000088&#0000083&#0000083&#0000039&#0000041></html>";
	String imgHexEncoding = "<html><head><title>test</title></head><img src=&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74&#x3A&#x61&#x6C&#x65&#x72&#x74&#x28&#x27&#x58&#x53&#x53&#x27&#x29></html>";
	String embeddedTab = "<html><head><title>test</title></head><body><img src=\"jav	ascript:alert('XSS');\"></body></html>";
	String embeddedEncodedTab = "<html><head><title>test</title></head><body><img src=\"jav&#x09;ascript:alert('XSS');\"></body></html>";
	String embeddedNewline = "<html><head><title>test</title></head><body><img src=\"jav&#x0A;ascript:alert('XSS');\"></body></html>";
	String embeddedCR = "<html><head><title>test</title></head><body><img src=\"jav&#x0D;ascript:alert('XSS');\"></body></html>";
	String multiEmbeddedCRs = "<html><head><title>test</title></head><body><img src\r=\r\"\rj\ra\rv\ra\rs\rc\rr\ri\rp\rt\r:\ra\rl\re\rr\rt\r(\r'\rX\rS\rS\r'\r)\r;\r\"></body></html>";
	String halfopenImg = "<html><head><title>test</title></head><body><img src=\"javascript:alert('XSS')\"</body></html>";
	String malformedImg = "<html><head><title>test</title></head><body><img \"\"\"><script>alert(\"XSS\")</script>\"></body></html>";
	
	public void testInputImage() {
		testExecute(inputImage, "<html><head><title>test</title></head><input type=\"IMAGE\"/></html>" );
	}
	
	public void testImageSrc() {		
		testExecute(imgSrc, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testImageNoQuotesSemiColon() {
		testExecute(imgNoQuotesSemicolon, "<html><head><title>test</title></head><img/></html>" );
	}	
	
	public void testImageDynSrc() {		
		testExecute(imgDynSrc, "<html><head><title>test</title></head><img/></html>" );
	}	
	
	public void testImageLowSrc() {
		testExecute(imgLowSrc, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testImageEmbeddedCommand() {
		testExecute(imgEmbeddedCmd, "<html><head><title>test</title></head><img src=\"http://www.thesiteyouareon.com/somecommand.php?somevariables=maliciouscode\"/></html>" );
	}	
	
	public void testImageStyleWithExpression() {		
		testExecute(imgStyleExpression, "<html><img style=\"exp/*<xss style='no\\xss:noxss(\" xss: x x2f xss pression alert xss/>></html>" );
	}
	
	public void testImageVBScript() {
		testExecute(imgVBscript, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testCaseInsensitive1() {		
		testExecute(caseInsensitive1, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testCaseInsensitive2() {		
		testExecute(caseInsensitive2, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testHtmlEntities() {		
		testExecute(htmlEntities, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testGraveAccents() {
		testExecute(graveAccents, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testCharCode() {
		testExecute(charCode, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testUnicode() {		
		testExecute(imgUnicode, "<html><head><title>test</title></head><img/></html>" );
	}
	
	public void testLongUnicode() {
		testExecute(imgLongUnicode, "<html><head><title>test</title></head><img/></html>" );
	}	
	
	public void testHexEncoding() {		
		testExecute(imgHexEncoding, "<html><head><title>test</title></head><img/></html>" );
	}	
	
	public void testEmbeddedTab() {		
		testExecute(embeddedTab, "<html><head><title>test</title></head><body><img/></body></html>" );
	}	
	
	public void testEmbeddedEncodedTab() {		
		testExecute(embeddedEncodedTab, "<html><head><title>test</title></head><body><img/></body></html>" );
	}	

	public void testEmbeddedNewline() {		
		testExecute(embeddedNewline, "<html><head><title>test</title></head><body><img/></body></html>" );
	}

	public void testEmbeddedCR() {		
		testExecute(embeddedCR, "<html><head><title>test</title></head><body><img/></body></html>" );
	}

	public void testHalfOpenImg() {		
		testExecute(halfopenImg, "<html><head><title>test</title></head><body></body></html>" );
	}
}
