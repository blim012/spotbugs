package edu.umd.cs.findbugs.ba;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class SignatureParserTest {

    @Test
    public void testNoParams() {
        SignatureParser sut = new SignatureParser("()V");
        Iterator<String> i = sut.parameterSignatureIterator();
        Assert.assertFalse(i.hasNext());        
    }

    @Test
    public void testManyParams() {
        SignatureParser sut = new SignatureParser("(Ljava/lang/String;B[S)Ljava/lang/Object;");
        Iterator<String> i = sut.parameterSignatureIterator();
        Assert.assertTrue(i.hasNext());
        Assert.assertEquals(i.next(), "Ljava/lang/String;");
        Assert.assertTrue(i.hasNext());
        Assert.assertEquals(i.next(), "B");
        Assert.assertTrue(i.hasNext());
	Assert.assertEquals(i.next(), "[S");
	Assert.assertFalse(i.hasNext());
    }
    
    @Test
    public void testInvalid()
    {
	SignatureParser sut1 = new SignatureParser("(Ljava/lang/String)V");
	SignatureParser sut2 = new SignatureParser("(V)V");
	SignatureParser sut3 = new SignatureParser("(");
	Iterator<String> i1 = sut1.parameterSignatureIterator();
	Iterator<String> i2 = sut2.parameterSignatureIterator();
	Iterator<String> i3 = sut3.parameterSignatureIterator();

	try
	{
	    i1.next();
	}
	catch(IllegalStateException e)
	{
	    Assert.assertEquals(e.getMessage(), "Invalid method signature: (Ljava/lang/String)V");
	}

	try
	{
	    i2.next();
	}
	catch(IllegalStateException e)
	{
	    Assert.assertEquals(e.getMessage(), "Invalid method signature: (V)V");
	}

	try
	{
	    i3.next();
	}
	catch(NoSuchElementException e)
	{
	    Assert.assertEquals(e.getMessage(), null);
	}
    }

    @Test
    public void testNoRet()
    {
	//A method signature without a return type is still accepted, which should not be the case
	//Method signatures should be in the format (parameters)returnVal

	SignatureParser sut = new SignatureParser("()");
	String s = sut.getReturnTypeSignature();
	Assert.assertEquals(s, "");
    }
}
