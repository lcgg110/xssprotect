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

public class ScriptElementTest extends BaseTestCase {
	String scriptJsJPG = "<html><head><title>test</title><script src=\"http://ha.ckers.org/xss.jpg\"></script></head><body></body></html>";
	String scriptEscaped = "<html><head><title>test</title><script>\";alert('XSS');//</script></head><body></body></html>";
	String nonClosingTag = "<html><head><title>test</title><script src=http://ha.ckers.org/xss.js</head><body></body></html>";
	String protocolResolution = "<html><head><script src=//ha.ckers.org/.j></head><body></body></html>";
	String noQuotesOrColons = "<html><head><script>a=/XSS/\nalert(a.source)</script></head><body></body></html>";
	
	public void testScriptElement() {		
		testExecute(scriptJsJPG, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testEscaped() {		
		testExecute(scriptEscaped, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testNonClosingTag() {		
		testExecute(nonClosingTag, "<html><head><title>test</title><body></body></head></html>" );
	}
	
	public void testProtocolResolution() {		
		testExecute(protocolResolution, "<html><head></head><body></body></html>" );
	}
	
	public void testNoQuotesColons() {		
		testExecute(noQuotesOrColons, "<html><head></head><body></body></html>" );
	}
}
