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

public class BodyOnloadTest extends BaseTestCase {
	String html = "<html><head><title>test</title></head><body onload=\"alert('XSS')\"></body></html>";
	
	public void testExecute() {
		testExecute(html, "<html><head><title>test</title></head><body></body></html>" );
	}
}
