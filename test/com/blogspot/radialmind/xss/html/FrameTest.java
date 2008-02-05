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

public class FrameTest extends BaseTestCase {
	String frameset = "<html><head><title>test</title></head><frameset><frame src=\"javascript:alert('XSS');\"></frame></frameset></html>";
	String iframe = "<html><head><title>test</title></head><iframe src=\"javascript:alert('XSS');\"></iframe></html>";
	
	public void testFrameSet() {		
		testExecute(frameset, "<html><head><title>test</title></head><frameset></frameset></html>" );
	}
	
	public void testIFrame() {
		testExecute(iframe, "<html><head><title>test</title></head></html>" );
	}
}
