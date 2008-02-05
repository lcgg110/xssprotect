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

public class SSITest extends BaseTestCase {
	String scriptSSI = "<html><head><title>test</title></head><body><!--#exec cmd=\"/bin/echo '<SCRIPT SRC'\"--><!--#exec cmd=\"/bin/echo '=http://ha.ckers.org/xss.js></SCRIPT>'\"--></body></html>";
	String phpCode = "<html><head><title>test</title></head><body><? echo('<SCR)';echo('IPT>alert(\"XSS\")</SCRIPT>'); ?></body></html>";
	String jsInclude = "<html><head><title>test</title></head><body><br size=\"&{alert('XSS')}\"></body></html>";
	
	public void testssi() {
		testExecute(scriptSSI, "<html><head><title>test</title></head><body><exec cmd=\"/bin/echo '<SCRIPT SRC'\"><exec cmd=\"/bin/echo '=http://ha.ckers.org/xss.js></SCRIPT>'\"></exec></exec></body></html>" );
	}
	
	public void testPhp() {
		testExecute(phpCode, "<html><head><title>test</title></head><body><echo scr echo ipt>alert(\"XSS\")</echo></body></html>" );
	}
	
	public void testJsInclude() {
		testExecute(jsInclude, "<html><head><title>test</title></head><body><br/></body></html>" );
	}
}
