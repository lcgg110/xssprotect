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

public class LayerElementTest extends BaseTestCase {
	String layerSrc = "<html><head><title>test</title></head><body><layer src=\"http://ha.ckers.org/scriptlet.html\"></layer></body></html>";
	
	public void testLayerElementTest() {		
		testExecute(layerSrc, "<html><head><title>test</title></head><body></body></html>" );
	}
}
