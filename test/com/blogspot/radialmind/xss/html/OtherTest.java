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

public class OtherTest extends BaseTestCase {
	String commented = "<html><head><title>test</title></head><body><!--[if gte IE 4]><SCRIPT>alert('XSS');</SCRIPT><![endif]--></body></html>";
	String charEncoding = "<html><head><%3C&lt&lt;&LT&LT;&#60&#060&#0060&#00060&#000060&#0000060&#60;&#060;&#0060;&#00060;&#000060;&#0000060;&#x3c&#x03c&#x003c&#x0003c&#x00003c&#x000003c&#x3c;&#x03c;&#x003c;&#x0003c;&#x00003c;&#x000003c;&#X3c&#X03c&#X003c&#X0003c&#X00003c&#X000003c&#X3c;&#X03c;&#X003c;&#X0003c;&#X00003c;&#X000003c;&#x3C&#x03C&#x003C&#x0003C&#x00003C&#x000003C&#x3C;&#x03C;&#x003C;&#x0003C;&#x00003C;&#x000003C;&#X3C&#X03C&#X003C&#X0003C&#X00003C&#X000003C&#X3C;&#X03C;&#X003C;&#X0003C;&#X00003C;&#X000003C;\\x3c\\x3C\\u003c\\u003C</head></html>"; 
	String utf7Encoding = "<html><head><meta http-equiv=\"CONTENT-TYPE\" content=\"text/html; charset=UTF-7\"></head>+ADw-SCRIPT+AD4-alert('XSS');+ADw-/SCRIPT+AD4-</html>";
	String nullChar = "<html><body><img src=java\0script:alert(\"XSS\")></body></html>";
	String nullChar2 = "<html><body>&<scr\0ipt>alert(\"XSS\")</scr\0ipt></body></html>";
	String spaceMetaChar = "<html><body><img src=\" &#14;  javascript:alert('XSS');\"></body></html>";
	String incorrectTagBreakup = "<html><script/xss src=\"http://ha.ckers.org/xss.js\"></script><body></body></html>";
	String nonAlphaNonDigit = "<html><body onload!#$%&()*~+-_.,:;?@[/|\\]^`=alert(\"XSS\")></body></html>";
	String doubleOpenAngleBrackets = "<html><iframe src=http://ha.ckers.org/scriptlet.html <</html>";
	String extraneousOpenBrackets = "<html><<script>alert(\"XSS\");//<</script><body></body></html>";
	String evadeRegExFilter1 = "<html><script a=\">\" src=\"http://ha.ckers.org/xss.js\"></script></html>";
	String evadeRegExFilter2 = "<html><script =\"blah\" src=\"http://ha.ckers.org/xss.js\"></script></html>";
	String evadeRegExFilter3 = "<html><script a=\"blah\" '' src=\"http://ha.ckers.org/xss.js\"></script></html>";
	String evadeRegExFilter4 = "<html><script \"a='>'\" src=\"http://ha.ckers.org/xss.js\"></script></html>";
	String evadeRegExFilter5 = "<html><script a=`>` src=\"http://ha.ckers.org/xss.js\"></script></html>";
	String filterEvasion1 = "<html><script>document.write(\"<SCRI\");</script>PT src=\"http://ha.ckers.org/xss.js\"></script></html>";
	String filterEvasion2 = "<html><script a=\">'>\" src=\"http://ha.ckers.org/xss.js\"></script></html>";
	String ipEncoding = "<html><body><a href=\"http://66.102.7.147/\">XSS</a></body></html>";
	String urlEncoding = "<html><body><a href=\"http://%77%77%77%2E%67%6F%6F%67%6C%65%2E%63%6F%6D\">XSS</a></body></html>";
	String dwordEncoding = "<html><body><a href=\"http://1113982867/\">XSS</a></body></html>";
	String hexEncoding = "<html><body><a href=\"http://0x42.0x0000066.0x7.0x93/\">XSS</a></body></html>";
	String octalEncoding = "<html><body><a href=\"http://0102.0146.0007.00000223/\">XSS</a></body></html>";
	String mixedEncoding = "<html><body><a href=\"h\rtt	p://6&#09;6.000146.0x7.147/\">XSS</a></body></html>";
	String protocolResolutionBypass = "<html><body><a href=\"//www.google.com/\">XSS</a></body></html>";
	String firefoxLookup1 = "<html><body><a href=\"//google\">XSS</a></body></html>";
	String firefoxLookup2 = "<html><body><a href=\"http://ha.ckers.org@google/\">XSS</a></body></html>";
	String firefoxLookup3 = "<html><body><a href=\"http://google:ha.ckers.org/\">XSS</a></body></html>";
	String removeCName = "<html><body><a href=\"http://google.com/\">XSS</a></body></html>";
	String extraDot = "<html><body><a href=\"http://www.google.com./\">XSS</a></body></html>";
	String extraDotNoQuotes = "<html><body><a href=http://www.google.com./>XSS</a></body></html>";
	String javascriptLink = "<html><body><a href=\"javascript:document.location='http://www.google.com/\">XSS</a></body></html>";
	String contentReplace = "<html><body><a href=\"http://www.gohttp://www.google.com/ogle.com/\">XSS</a></body></html>";
	
	public void testHexEncoding() {
		testExecute(hexEncoding, "<html><body><a href=\"http://0x42.0x0000066.0x7.0x93/\">XSS</a></body></html>" );
	}
	
	public void testCommented() {
		testExecute(commented, "<html><head><title>test</title></head><body><if gte ie><endif></endif></if></body></html>" );
	}

	public void testCharEncoding() {
		testExecute(charEncoding, "<html><head><c lt lt lt lt x3c x03c x003c x0003c x00003c x000003c x3c x03c x003c x0003c x00003c x000003c x3c x03c x003c x0003c x00003c x000003c x3c x03c x003c x0003c x00003c x000003c x3c x03c x003c x0003c x00003c x000003c x3c x03c x003c x0003c x00003c x000003c x3c x03c x003c x0003c x00003c x000003c x3c x03c x003c x0003c x00003c x000003c x3c x3c u003c u003c head></c></head></html>" );
	}

	public void testUtf7Encoding() {
		testExecute(utf7Encoding, "<html><head></head>+ADw-SCRIPT+AD4-alert('XSS');+ADw-/SCRIPT+AD4-</html>" );
	}

	public void testNullChar() {
		testExecute(nullChar, "<html><body><img src=\"java\" script:alert xss/></body></html>" );
	}

	public void testNullChar2() {
		testExecute(nullChar2, "<html><body><p>&</p><scr ipt>alert(\"XSS\")</scr></body></html>" );
	}

	public void testSpaceMetaChar() {
		testExecute(spaceMetaChar, "<html><body><img/></body></html>" );
	}

	public void testTagBreakupIncorrect() {
		testExecute(incorrectTagBreakup, "<html></html>" );
	}

	public void testNonAlphaNonDigit() {
		testExecute(nonAlphaNonDigit, "<html></html>" );
	}

	public void testDoubleOpenAngleBrackets() {
		testExecute(doubleOpenAngleBrackets, "<html></html>" );
	}

	public void testExtraneousOpenBrackets() {
		testExecute(extraneousOpenBrackets, "<html><body></body></html>" );
	}

	public void testEvadeRegExFilter1() {
		testExecute(evadeRegExFilter1, "<html></html>" );
	}

	public void testEvadeRegExFilter2() {
		testExecute(evadeRegExFilter2, "<html></html>" );
	}
	
	public void testEvadeRegExFilter3() {
		testExecute(evadeRegExFilter3, "<html></html>" );
	}
	
	public void testEvadeRegExFilter4() {
		testExecute(evadeRegExFilter4, "<html></html>" );
	}
	
	public void testEvadeRegExFilter5() {
		testExecute(evadeRegExFilter5, "<html></html>" );
	}

	public void testFilterEvasion1() {
		testExecute(filterEvasion1, "<html><scri script></scri></html>" );
	}
	
	public void testFilterEvasion2() {
		testExecute(filterEvasion2, "<html></html>" );
	}
	
	public void testIpEncoding() {
		testExecute(ipEncoding, "<html><body><a href=\"http://66.102.7.147/\">XSS</a></body></html>" );
	}
	
	public void testUrlEncoding() {
		testExecute(urlEncoding, "<html><body><a href=\"http://%77%77%77%2E%67%6F%6F%67%6C%65%2E%63%6F%6D\">XSS</a></body></html>" );
	}
	
	public void testDwordEncoding() {
		testExecute(dwordEncoding, "<html><body><a href=\"http://1113982867/\">XSS</a></body></html>" );
	}
	
	public void testOctalEncoding() {
		testExecute(octalEncoding, "<html><body><a href=\"http://0102.0146.0007.00000223/\">XSS</a></body></html>" );
	}
	
	public void testMixedEncoding() {
		testExecute(mixedEncoding, "<html><body><a>XSS</a></body></html>" );
	}
	
	public void testProtocolResolutionBypass() {
		testExecute(protocolResolutionBypass, "<html><body><a href=\"//www.google.com/\">XSS</a></body></html>" );
	}
	
	public void testFirefoxLookup1() {
		testExecute(firefoxLookup1, "<html><body><a href=\"//google\">XSS</a></body></html>" );
	}
	
	public void testFirefoxLookup2() {
		testExecute(firefoxLookup2, "<html><body><a href=\"http://ha.ckers.org@google/\">XSS</a></body></html>" );
	}
	
	public void testFirefoxLookup3() {
		testExecute(firefoxLookup3, "<html><body><a href=\"http://google:ha.ckers.org/\">XSS</a></body></html>" );
	}
	
	public void testRemoveCname() {
		testExecute(removeCName, "<html><body><a href=\"http://google.com/\">XSS</a></body></html>" );
	}

	public void testExtraDot() {
		testExecute(extraDot, "<html><body><a href=\"http://www.google.com./\">XSS</a></body></html>" );
	}

	public void testJavaScriptLink() {
		testExecute(javascriptLink, "<html><body><a>XSS</a></body></html>" );
	}	
	
	public void testContentReplace() {
		testExecute( contentReplace, "<html><body><a href=\"http://www.gohttp://www.google.com/ogle.com/\">XSS</a></body></html>" );		
	}
}
