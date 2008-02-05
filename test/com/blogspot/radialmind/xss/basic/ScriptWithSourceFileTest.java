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
//
// Needs checking in IE6.0 

public class ScriptWithSourceFileTest extends BaseTestCase {
	String html = "<html><head><title>test</title></head><body><SCRIPT SRC=http://ha.ckers.org/xss.js></SCRIPT></body></html>";
	
	public void testScriptSourceFile() {
		testExecute(html, "<html><head><title>test</title></head><body></body></html>");
	}
}
