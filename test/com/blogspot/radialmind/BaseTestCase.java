/**
 * Copyright 2007 Gerard Toonstra
 * 
 * Licensed under the terms of the Apache Software License v2
 *
 * This file is part of the XSS Protect library
 */

package com.blogspot.radialmind;

import java.io.StringReader;
import java.io.StringWriter;

import junit.framework.TestCase;

import com.blogspot.radialmind.html.HTMLParser;
import com.blogspot.radialmind.html.HandlingException;
import com.blogspot.radialmind.xss.XSSFilter;

// Verified vulnerability in :
// 		Firefox 2.0		not vulnerable
//
// Needs checking in IE6.0 

public abstract class BaseTestCase extends TestCase {
	protected void testExecute( String html, String result ) {
		StringReader reader = new StringReader( html );
		StringWriter writer = new StringWriter();
		
		try {
			HTMLParser.process( reader, writer, new XSSFilter(), true );
			String buffer = new String( writer.toString() );
			System.out.println( buffer );
			assertEquals( result, buffer );
		} catch (HandlingException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
}
