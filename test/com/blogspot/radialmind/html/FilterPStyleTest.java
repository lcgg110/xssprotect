/**
 * Copyright 2007 Gerard Toonstra
 * 
 * Licensed under the terms of the Apache Software License v2
 *
 * This file is part of the XSS Protect library
 */

package com.blogspot.radialmind.html;

import java.io.StringReader;
import java.io.StringWriter;

import junit.framework.TestCase;

public class FilterPStyleTest extends TestCase implements IHTMLFilter {
	
	String html = "<html><head>\n<title>test</title>\n</head>\n<body><p style=\"font-weight: bold;\">Some text</p></body></html>\n";
	
	public void testFilterPStyle() {
		StringReader reader = new StringReader( html );
		StringWriter writer = new StringWriter();
		
		try {
			HTMLParser.process( reader, writer, this, true );
			String buffer = new String( writer.toString() );
			System.out.println( buffer );
			assertEquals( buffer, "<html><head><title>test</title></head><body><p>Some text</p></body></html>" );
		} catch (HandlingException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	public boolean filterAttribute(String tagName, String attrName, String attrValue) {
		if ( attrName.equals( "style" )) {
			return true;
		}
		return false;

	}

	public boolean filterTag(String tagName) {
		return false;
	}

	public String modifyAttributeValue(String tagName, String attrName, String value) {
		return value;
	}

	public String modifyNodeText(String tagName, String text) {
		return text;
	}
}
