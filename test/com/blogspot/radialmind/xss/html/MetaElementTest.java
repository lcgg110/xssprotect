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

public class MetaElementTest extends BaseTestCase {
	String metaRefreshTest = "<html><head><title>test</title><meta http-equiv=\"refresh\" content=\"0;url=javascript:alert('XSS');\"></head><body></body></html>";
	String metaWithDataTest = "<html><head><title>test</title><meta http-equiv=\"refresh\" content=\"0;url=data:text/html;base64,PHNjcmlwdD5hbGVydCgnWFNTJyk8L3NjcmlwdD4K\"></head><body></body></html>";
	String metaWithAddUrlTest = "<html><head><title>test</title><meta http-equiv=\"refresh\" content=\"0; URL=http://;URL=javascript:alert('XSS');\"></head><body></body></html>";
	String metaManipulation = "<html><head><title>test</title><meta http-equiv=\"Set-Cookie\" content=\"USERID=<SCRIPT>alert('XSS')</SCRIPT>\"></head><body></body></html>"; 
		
	public void testMetaTag() {
		testExecute(metaRefreshTest, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testMetaWithDataTag() {
		testExecute(metaWithDataTest, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testMetaWithAddUrlTag() {
		testExecute(metaWithAddUrlTest, "<html><head><title>test</title></head><body></body></html>" );
	}
	
	public void testMetaManipulation() {
		testExecute(metaManipulation, "<html><head><title>test</title></head><body></body></html>" );
	}
}
