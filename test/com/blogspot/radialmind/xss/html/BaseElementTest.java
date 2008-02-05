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

public class BaseElementTest extends BaseTestCase {
	String html = "<html><head><base href=\"javascript:alert('XSS');//\"><title>test</title></head><body><img src=\"test.jpg\"></body></html>";
	String disabled = "<html><head></head><body><a href=http://www.google.com disabled></body></html>";
	
	public void testExecute() {
		testExecute(html, "<html><head><base><title>test</title></base></head><body><img src=\"test.jpg\"/></body></html>" );
	}
	
	public void testDisabled() {
		testExecute(disabled, "<html><head></head><body><a href=\"http://www.google.com\" disabled></a></body></html>" );
	}
}
