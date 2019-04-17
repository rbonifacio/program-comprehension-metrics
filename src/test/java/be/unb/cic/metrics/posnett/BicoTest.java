package be.unb.cic.metrics.posnett;

import br.unb.cic.metrics.posnett.Parser;
import br.unb.cic.metrics.posnett.PosnettMetrics;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.junit.Assert;
import org.junit.Test;

public class BicoTest {

    private String code1 =
            "int binco(int n, int k) {\n"         +
            "  int[] arr = arrInit(n + 1);\n"     +
            "  for (int i = 0; i <= n; i++) {\n"  +
            "    int temp = arr[0];\n"            +
            "    for (int j = 1; j < i; j++) {\n" +
            "      arr[j] = arr[j] + temp;\n"     +
            "      temp = arr[j] - temp;\n"       +
            "     }\n"                            +
            "  }\n"                               +
            "  return arr[k];\n"                  +
            "}";

    private String code2 =
            "int binco(int n, int k) {\n"         +
            "  int[] arr = arrInit(n + 1);\n"     +
            "  return arr[k];\n"                  +
            "}";



    @Test
    public void testSimpleReadability() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(code1);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(code2);

        Assert.assertEquals(0.1885, PosnettMetrics.simpleReadability(method1), 0.001);
        Assert.assertEquals(0.0209, PosnettMetrics.simpleReadability(method2), 0.001);
    }

    @Test
    public void testDegreeOfComprehension() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(code1);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(code2);

        Assert.assertEquals(1.457, PosnettMetrics.comprehensionDegree(method1), 0.001);
        Assert.assertEquals(3.845, PosnettMetrics.comprehensionDegree(method2), 0.001);
    }

    @Test
    public void testLinesOfCode() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(code1);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(code2);
        Assert.assertEquals(11, PosnettMetrics.lines(method1));
        Assert.assertEquals(4, PosnettMetrics.lines(method2));
    }

    @Test
    public void testLength() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(code1);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(code2);

        Assert.assertEquals(41, PosnettMetrics.length(method1));
        Assert.assertEquals(8, PosnettMetrics.length(method2));
    }

    @Test
    public void testVocabulary() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(code1);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(code2);

        Assert.assertEquals(15, PosnettMetrics.vocabulary(method1));
        Assert.assertEquals(7, PosnettMetrics.vocabulary(method2));
    }

    @Test
    public void testVolume() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(code1);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(code2);

        Assert.assertEquals(160.1825, PosnettMetrics.volume(method1), 0.0001);
        Assert.assertEquals(22.4588, PosnettMetrics.volume(method2), 0.0001);
    }

    @Test
    public void testEntropy() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(code1);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(code2);

        Assert.assertEquals(4.350989, PosnettMetrics.entropy(method1), 0.000001);
        Assert.assertEquals(3.921928, PosnettMetrics.entropy(method2), 0.000001);
    }


    @Test
    public void testPosnett() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(code1);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(code2);
        Double d1 = PosnettMetrics.comprehensionDegree(method1);
        System.out.println(d1);
        System.out.println(1 / (1 + Math.pow(Math.E, -d1)));

        Double d2 = PosnettMetrics.comprehensionDegree(method2);
        System.out.println(d2);
        System.out.println(1 / (1 + Math.pow(Math.E, -d2)));

    }
}
