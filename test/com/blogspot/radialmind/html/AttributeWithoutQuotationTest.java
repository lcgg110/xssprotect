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

import com.blogspot.radialmind.BaseTestCase;

public class AttributeWithoutQuotationTest extends BaseTestCase implements IHTMLFilter {
	
	String html = "<html><head>\n<title>test</title>\n</head>\n<body><img border='0px' width=220px src=\"test.jpg\"><p>The image should be there too</p></body></html>\n";
	
	public void testAttributeWithoutQuotation() {
		StringReader reader = new StringReader( html );
		StringWriter writer = new StringWriter();
		
		try {
			HTMLParser.process( reader, writer, this, true );
			String buffer = new String( writer.toString() );
			System.out.println( buffer );
			assertEquals( "<html><head><title>test</title></head><body><img border=\"0px\" width=\"220px\" src=\"test.jpg\"/><p>The image should be there too</p></body></html>", buffer );
		} catch (HandlingException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	public boolean filterAttribute(String tagName, String attrName, String attrValue) {
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
