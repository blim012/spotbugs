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

package edu.umd.cs.findbugs.classfile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author andrei
 */
public class TestClassDescriptor {

    @Test
    public void testSimpleName() {
        ClassDescriptor s = DescriptorFactory.createClassDescriptor("com");
	assertEquals("com", s.getClassName());
	assertEquals("com", s.getDottedClassName());
	assertEquals("Lcom;", s.getSignature());
	assertEquals("", s.getPackageName());
	assertEquals("com", s.getSimpleName());

	ClassDescriptor p = DescriptorFactory.createClassDescriptor("com/bla/Parent");
        assertEquals("com/bla/Parent", p.getClassName());
        assertEquals("com.bla.Parent", p.getDottedClassName());
        assertEquals("Lcom/bla/Parent;", p.getSignature());
        assertEquals("com.bla", p.getPackageName());
        assertEquals("Parent", p.getSimpleName());

        ClassDescriptor c = DescriptorFactory.createClassDescriptor("com/bla/Parent$Child");
        assertEquals("com/bla/Parent$Child", c.getClassName());
        assertEquals("com.bla.Parent$Child", c.getDottedClassName());
        assertEquals("Lcom/bla/Parent$Child;", c.getSignature());
        assertEquals("com.bla", c.getPackageName());
        assertEquals("Child", c.getSimpleName());

        ClassDescriptor a = DescriptorFactory.createClassDescriptor("com/bla/Parent$Child$1");
        assertEquals("com/bla/Parent$Child$1", a.getClassName());
        assertEquals("com.bla.Parent$Child$1", a.getDottedClassName());
        assertEquals("Lcom/bla/Parent$Child$1;", a.getSignature());
        assertEquals("com.bla", a.getPackageName());
        assertEquals("1", a.getSimpleName());
    }

    @Test
    public void testInvalidName()
    {
	try
	{
	    ClassDescriptor a = new ClassDescriptor("com.bla.Parent");
	}
	catch(IllegalArgumentException e)
	{
	    assertEquals(e.getMessage(), "Class name com.bla.Parent not in VM format");
	}

	try
	{
	    ClassDescriptor a = DescriptorFactory.createClassDescriptor("com.bla.Parent");
	}
	catch(AssertionError e)
	{
	    assertEquals(e.getMessage(), null);
	}

	try
	{
	    ClassDescriptor b = new ClassDescriptor("(com/bla/Parent)");
	}
	catch(IllegalArgumentException e)
	{
	    assertEquals(e.getMessage(), "Invalid class name (com/bla/Parent)");
	}

	try
	{
	    ClassDescriptor b = DescriptorFactory.createClassDescriptor("(com/bla/Parent)");	
	}
	catch(IllegalArgumentException e)
	{
	    assertEquals(e.getMessage(), "Invalid class name (com/bla/Parent)");
	}	
    }

    @Test
    public void testShouldFail()
    {
	//Invalid class name is still able to pass through constructor checks
	//as well as getClassDescriptor checks.

	ClassDescriptor p = DescriptorFactory.createClassDescriptor("com/bla/$/$/$");
	assertEquals("com/bla/$/$/$", p.getClassName());

	//Class names cannot be empty, so this should not be accepted as well.
	//A consequence of this is that if you attempt to getSignature on a 
	//className that is an empty string, you get a StringIndexOutofBoundsException
        ClassDescriptor e = DescriptorFactory.createClassDescriptor("");
	assertEquals("", e.getClassName());
	assertEquals("", e.getDottedClassName());
        assertEquals("", e.getPackageName());
        assertEquals("", e.getSimpleName());

    }

}
