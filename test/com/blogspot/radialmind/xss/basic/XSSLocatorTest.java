/**
 * Copyright 2007 Gerard Toonstra
 * 
 * Licensed under the terms of the Apache Software License v2
 *
 * This file is part of the XSS Protect library
 */
package com.blogspot.radialmind.xss.basic;

import com.blogspot.radialmind.BaseTestCase;

// Verified vulnerability in :
//	Firefox 2.0		not vulnerable


public class XSSLocatorTest extends BaseTestCase {
	String html = "<html><head><title>XSS Locator</title></head>\n<body><P>';alert(String.fromCharCode(88,83,83))//\';alert(String.fromCharCode(88,83,83))//\";alert(String.fromCharCode(88,83,83))//\";alert(String.fromCharCode(88,83,83))//--></SCRIPT>\">'><SCRIPT>alert(String.fromCharCode(88,83,83))</SCRIPT>=&{}</p>\n</body></html>";
	
	public void testLocatorTest() {
		testExecute(html, "<html><head><title>XSS Locator</title></head><body><p>';alert(String.fromCharCode(88,83,83))//';alert(String.fromCharCode(88,83,83))//\";alert(String.fromCharCode(88,83,83))//\";alert(String.fromCharCode(88,83,83))//--></p></body></html>");
	}
}
