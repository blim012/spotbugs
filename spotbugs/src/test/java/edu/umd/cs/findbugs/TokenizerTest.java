package edu.umd.cs.findbugs;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import org.junit.Test;
import org.junit.Assert;

public class TokenizerTest {
    //initial test to see if we can add tests
    @Test
    public void integerDeclaration() {
        String s = "int a = 9;";
        StringReader sr = new StringReader(s);
        PushbackReader reader = new PushbackReader(sr);
        Tokenizer t = new Tokenizer(reader);

        try {
            Token intString = t.next();
            Assert.assertEquals(intString.getKind(), Token.WORD);
            Assert.assertEquals(intString.getLexeme(), "int");

            Token aString = t.next();
            Assert.assertEquals(aString.getKind(), Token.WORD);
            Assert.assertEquals(aString.getLexeme(), "a");

            Token eqString = t.next();
            Assert.assertEquals(eqString.getKind(), Token.SINGLE);
            Assert.assertEquals(eqString.getLexeme(), "=");

            Token string9 = t.next();
            Assert.assertEquals(string9.getKind(), Token.WORD);
            Assert.assertEquals(string9.getLexeme(), "9");

            Token semiString = t.next();
            Assert.assertEquals(semiString.getKind(), Token.SINGLE);
            Assert.assertEquals(semiString.getLexeme(), ";");
        } catch (IOException e) {

        }

    }
}
