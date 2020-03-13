/*
 * FindBugs - Find Bugs in Java programs
 * Copyright (C) 2005, University of Maryland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package edu.umd.cs.findbugs.visitclass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author pugh
 */

public class GetNumberArgumentsTest {

    static String[] simpleTypes = "I J B C D F S Z Ljava/lang/String;".split(" ");

    @Test
    public void testSimpleWithVoidReturnType() {
        for (String s : simpleTypes) {
            assertEquals(1, PreorderVisitor.getNumberArguments("(" + s + ")V"));
        }
    }

    @Test
    public void testArrays() {
	assertEquals(1, PreorderVisitor.getNumberArguments("([I)V"));
	assertEquals(1, PreorderVisitor.getNumberArguments("([[Ljava/lang/String;)I"));        
    }

    @Test
    public void testMultipleArguments() {
	assertEquals(2, PreorderVisitor.getNumberArguments("([Ljava/lang/String;S)V"));
	assertEquals(2, PreorderVisitor.getNumberArguments("([[FLjava/lang/String;)I"));        
    }
}
