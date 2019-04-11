package be.unb.cic.metrics.posnett;

import br.unb.cic.metrics.posnett.Parser;
import br.unb.cic.metrics.posnett.PosnettMetrics;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.junit.Assert;
import org.junit.Test;

public class PosnettMetricsTest {


    private final static String foo = "public int foo() {\n " +
                                      "  int x = 0;\n " +
                                      "  int y = 5;\n" +
                                      "  int z = x + y;\n" +
                                      "  System.out.println(z);\n" +
                                      "}";
    @Test
    public void testMethodSize() {
        MethodDeclaration method = Parser.parseMethodDeclaration(foo);
        Assert.assertNotNull(method);

        int lines = PosnettMetrics.lines(method);
        Assert.assertEquals(6, lines);
    }

    @Test
    public void testMethodLength() {
        MethodDeclaration method = Parser.parseMethodDeclaration(foo);
        Assert.assertNotNull(method);

        int length = PosnettMetrics.length(method);
        // we are expecting the following operatores: [=, =, =, +, println]
        // we are expecting the following operands: [x, 0, y, 5, z, x, y, System, out, z]
        Assert.assertEquals(15, length);
    }

    @Test
    public void testMethodVocabulary() {
        MethodDeclaration method = Parser.parseMethodDeclaration(foo);
        Assert.assertNotNull(method);

        int vocabulary = PosnettMetrics.vocabulary(method);
        // we are expecting the following operatores: [=, +, println]
        // we are expecting the following operands: [x, 0, y, 5, z, System, out]
        Assert.assertEquals(10, vocabulary);
    }

    @Test
    public void testMethodVolume() {
        MethodDeclaration method = Parser.parseMethodDeclaration(foo);
        Assert.assertNotNull(method);

        double volume = PosnettMetrics.volume(method);
        //it should be 15 * log 10 na base 2
        Assert.assertEquals(49.82892, volume, 0.0001);
    }

    @Test
    public void testMethodEntropy() {
        MethodDeclaration method = Parser.parseMethodDeclaration(foo);
        Assert.assertNotNull(method);

        double entropy = PosnettMetrics.entropy(method);

        Assert.assertEquals(3.91956, entropy, 0.0001);
    }


}
