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

public class ListStyleTest extends BaseTestCase {
	String listStyle = "<html><head><title>test</title></head><style>li {list-style-image: url(\"javascript:alert('XSS')\");}</style><body><ul><li>XSS</li></ul></body></html>";
	
	public void testListStyle() {		
		testExecute(listStyle, "<html><head><title>test</title></head><body><ul><li>XSS</li></ul></body></html>" );
	}
}
