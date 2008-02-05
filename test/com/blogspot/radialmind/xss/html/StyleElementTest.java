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

public class StyleElementTest extends BaseTestCase {
	String styleType = "<html><head><title>test</title><style type=\"text/javascript\">alert('XSS');</style></head><body></body></html>";
	String styleWithComment = "<html><head><title>test</title></head><body><img style=\"xss:expr/*XSS*/ession(alert('XSS'))\"></body></html>";
	String styleWithAnonHTML = "<html><head><title>test</title></head><body><xss style=\"xss:expression(alert('XSS'))\"></body></html>";
	String styleWithBGImg = "<html><head><title>test</title><style>.XSS{background-image:url(\"javascript:alert('XSS')\");}</style></head><body><a class=\"XSS\"></a></body></html>";
	String styleWithBg = "<html><head><title>test</title><style type=\"text/css\">BODY{background:url(\"javascript:alert('XSS')\")}</style></head><body></body></html>";
	String brokenStyleTag = "<html><head><title>test</title><style>@im\\port'\\ja\\vasc\\ript:alert(\"XSS\")';</style></head><body></body></html>";
	
	public void testStylElement() {
		testExecute(styleType, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testStyleWithComment() {
		testExecute(styleWithComment, "<html><head><title>test</title></head><body><img/></body></html>" );
	}
	
	public void testStyleWithAnonHTML() {
		testExecute(styleWithAnonHTML, "<html><head><title>test</title></head><body><xss></xss></body></html>" );
	}
	
	public void testStyleWithBGImg() {
		testExecute(styleWithBGImg, "<html><head><title>test</title></head><body><a class=\"XSS\"></a></body></html>" );
	}
	
	public void testStyleWithBG() {
		testExecute(styleWithBg, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testBrokenStyleTag() {
		testExecute(brokenStyleTag, "<html><head><title>test</title></head><body></body></html>" );
	}
}
