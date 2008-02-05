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

public class EmbeddedObjectsTest extends BaseTestCase {
	String objectHTML = "<html><head><title>test</title></head><body><object type=\"text/x-scriptlet\" data=\"http://ha.ckers.org/scriptlet.html\"></object></body></html>";
	String objectWithEmbXSSHTML = "<html><head><title>test</title></head><body><object classid=clsid:ae24fdae-03c6-11d1-8b76-0080c744f389><param name=url value=javascript:alert('XSS')/></object></body></html>";
	String embeddedFlash = "<html><head><title>test</title></head><body><embed src=\"http://ha.ckers.org/xss.swf\" allowscriptaccess=\"always\"></embed></body></html>";
	
	// Not tested (actionscript for xss vector obfuscation)
	String flash2 = "<html><head><title>test</title></head><body><script language=\"javascript\">a=\"get\";&#10;b=\"URL(\"\";&#10;c=\"javascript:\";&#10;d=\"alert('XSS');\")\";eval(a+b+c+d);</script></body></html>";
	
	public void testObjectHTML() {
		testExecute(objectHTML, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testObjectWithEmbXSSHTML() {
		testExecute(objectWithEmbXSSHTML, "<html><head><title>test</title></head><body><param name=\"url\"></param></body></html>" );
	}
	
	public void testEmbeddedFlash() {
		testExecute(embeddedFlash, "<html><head><title>test</title></head><body></body></html>" );
	}
}
