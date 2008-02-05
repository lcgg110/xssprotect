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

public class TableTest extends BaseTestCase {
	String table = "<html><head><title>test</title></head><body><table background=\"javascript:alert('XSS')\"></table></body></html>";
	String td = "<html><head><title>test</title></head><body><table><td background=\"javascript:alert('XSS')\"></td></table></body></html>";
	
	public void testTable() {
		testExecute(table, "<html><head><title>test</title></head><body><table></table></body></html>" );
	}
	
	public void testTD() {		
		testExecute(td, "<html><head><title>test</title></head><body><table><td></td></table></body></html>" );
	}
}
