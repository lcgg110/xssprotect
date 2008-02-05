/**
 * Copyright 2007 Gerard Toonstra
 * 
 * Licensed under the terms of the Apache Software License v2
 *
 * This file is part of the XSS Protect library
 */

package com.blogspot.radialmind.html;

import java.io.IOException;
import java.io.Writer;

public interface IHTMLVisitor {
	abstract void writeAll( Writer writer, IHTMLFilter filter, boolean convertIntoValidXML, boolean filterText ) throws IOException;
	abstract void setPrevNode( Node node );
	abstract Node getPrevNode();
}
