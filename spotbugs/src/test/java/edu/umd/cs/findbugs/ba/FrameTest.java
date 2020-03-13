package edu.umd.cs.findbugs.ba;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FrameTest {

    @Test
    public void testToString() {
        Frame<String> frame = new Frame<String>(1) {
            @Override
            public String getValue(int n) {
                return "value";
            }
        };
        assertThat(frame.toString(), is(equalTo("[value]")));
    }

    @Test
    public void testPush() 
    {
	Frame<String> frame = new Frame<String>(1){};

	frame.pushValue("");
	try
	{
	    assertThat(frame.getTopValue(), is(""));
	}
	catch(DataflowAnalysisException e){}

	frame.setBottom();
	try
	{
	    frame.pushValue("");
	}
	catch(IllegalStateException e)
	{
	    assertThat(e.getMessage(), is("accessing top or bottom frame"));
	}

	frame.setTop();
	try
	{
	    frame.pushValue("");
	}
	catch(IllegalStateException e)
	{
	    assertThat(e.getMessage(), is("accessing top or bottom frame"));
	}
    }

    @Test
    public void testPop()
    {
	Frame<String> frame0 = new Frame<String>(0){};
	Frame<String> frame1 = new Frame<String>(1){};	
	frame0.pushValue("a");
	frame1.pushValue("b");	
	
	try
	{
	    frame0.popValue();
	}
	catch(DataflowAnalysisException e)
	{
	    assertThat(e.getMessage(), is("operand stack empty"));
	}

	frame0.setBottom();
	try
	{
	    frame0.popValue();
	}
	catch(DataflowAnalysisException e)
	{
	    assertThat(e.getMessage(), is("accessing top or bottom frame"));
	}

	frame0.setTop();
	try
	{
	    frame0.popValue();
	}
	catch(DataflowAnalysisException e)
	{
	    assertThat(e.getMessage(), is("accessing top or bottom frame"));
	}

	frame0.setValid();
	try
	{
	    frame0.popValue();
	}
	catch(DataflowAnalysisException e)
	{
	    assertThat(e.getMessage(), is("operand stack empty"));
	}

	try
	{
	    assertThat(frame1.popValue(), is("b"));
	}
	catch(DataflowAnalysisException e){}
    }

    @Test
    public void testGetTopValue()
    {
	Frame<String> frameEmpty = new Frame<String>(1){};
	Frame<String> frameInvalid = new Frame<String>(1){};
	frameInvalid.pushValue("");

	try
	{
	    frameEmpty.getTopValue();
	}
	catch(DataflowAnalysisException e)
	{
	    assertThat(e.getMessage(), is("operand stack is empty"));
	}

	frameInvalid.setTop();
	try
	{
	    frameInvalid.getTopValue();
	}
	catch(DataflowAnalysisException e)
	{
	    assertThat(e.getMessage(), is("accessing top or bottom frame"));
	}

	frameInvalid.setBottom();
	try
	{
	    frameInvalid.getTopValue();
	}
	catch(DataflowAnalysisException e)
	{
	    assertThat(e.getMessage(), is("accessing top or bottom frame"));
	}
    }
}
