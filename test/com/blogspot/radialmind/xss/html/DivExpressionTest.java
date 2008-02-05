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

public class DivExpressionTest extends BaseTestCase {
	String html1 = "<html><head><title>test</title></head><body><div style=\"width: expression(alert('XSS'));\"></div></body></html>";
	
	public void testExecute1() {
		testExecute(html1, "<html><head><title>test</title></head><body><div></div></body></html>" );
	}
}
