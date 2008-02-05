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

public class DivStyleBackgroundTest extends BaseTestCase {
	String html1 = "<html><head><title>test</title></head><body><div style=\"background-image: url(javascript:alert('XSS'))\"></div></body></html>";
	String html2 = "<html><head><title>test</title></head><body><div style=\"background-image: url(&#1;javascript:alert('XSS'))\"></div></body></html>";
	String divEncoding = "<html><head><title>test</title></head><body><div style=\"background-image:\0075\0072\006C\0028'\006a\0061\0076\0061\0073\0063\0072\0069\0070\0074\003a\0061\006c\0065\0072\0074\0028.1027\0058.1053\0053\0027\0029'\0029\"></div></body></html>";

	public void testExecute1() {
		testExecute( html1, "<html><head><title>test</title></head><body><div></div></body></html>" );
	}
	
	public void testExecute2() {
		testExecute(html2, "<html><head><title>test</title></head><body><div></div></body></html>" );
	}	
	
	public void testDivEncoding() {
		testExecute(divEncoding, "<html><head><title>test</title></head><body><div style=\"background-image:\0075\0072\006C\0028'\006a\0061\0076\0061\0073\0063\0072\0069\0070\0074\003a\0061\006c\0065\0072\0074\0028.1027\0058.1053\0053\0027\0029'\0029\"></div></body></html>" );
	}	
}
