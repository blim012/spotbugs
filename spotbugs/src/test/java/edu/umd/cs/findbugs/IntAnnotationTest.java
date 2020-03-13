/*
 * FindBugs - Find Bugs in Java programs
 * Copyright (C) 2003-2008 University of Maryland
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

package edu.umd.cs.findbugs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author pugh
 */
public class IntAnnotationTest {
    
    
    //@Test
    public void test() { //Many redundant tests that can be removed
        assertEquals("0xffff", IntAnnotation.getShortInteger(0xffff));
        assertEquals("0x1ffff", IntAnnotation.getShortInteger(0x1ffff));
        assertEquals("0x1fffffff", IntAnnotation.getShortInteger(0x1fffffff));
        assertEquals("0x7fffffff", IntAnnotation.getShortInteger(0x7fffffff));
        assertEquals("15", IntAnnotation.getShortInteger(15));
        assertEquals("255", IntAnnotation.getShortInteger(255));
        assertEquals("-1", IntAnnotation.getShortInteger(-1));
        assertEquals("-2", IntAnnotation.getShortInteger(-2));
        assertEquals("0xffffffff", IntAnnotation.getShortInteger(0xffffffffL));
        assertEquals("0x1ffffffff", IntAnnotation.getShortInteger(0x1ffffffffL));
        assertEquals("0xfffffffff", IntAnnotation.getShortInteger(0xfffffffffL));
    }
    
    /* 
     * Correlated Active Clause Coverage Tests
     */

    @Test
    public void getShortIntegerIntParamCACC() 
    {
	assertEquals("0xffff", IntAnnotation.getShortInteger(0xffff));
	assertEquals("19136511", IntAnnotation.getShortInteger(0x123ffff));
	assertEquals("127", IntAnnotation.getShortInteger(0x7f));
	assertEquals("4369", IntAnnotation.getShortInteger(0x1111));
    }

    @Test
    public void getShortIntegerLongParamCACC()
    {
	assertEquals("0xffff", IntAnnotation.getShortInteger(0xffffL));
	assertEquals("19136511", IntAnnotation.getShortInteger(0x123ffffL));
	assertEquals("127", IntAnnotation.getShortInteger(0x7fL));
	assertEquals("4369", IntAnnotation.getShortInteger(0x1111L));
    }
}
