package be.unb.cic.metrics.posnett;

import br.unb.cic.metrics.posnett.Parser;
import br.unb.cic.metrics.posnett.PosnettMetrics;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.junit.Assert;
import org.junit.Test;

public class T1027 {

    public static final String T1027B =
            "static List<ResourceMethod> transform(final Resource parent, final List<Data> list) {\n" +
            "  return Lists.transform(list, new Function<Data, ResourceMethod>() {\n" +
            "        @Override\n" +
            "        public ResourceMethod apply(Data data) {\n" +
            "            return (data == null) ? null : new ResourceMethod(parent, data);\n" +
            "        }\n" +
            "    });\n" +
            "}";

    public static final String T1027A =
                    "static List<ResourceMethod> transform(final Resource parent, final List<Data> list) {\n" +
                    "    return list.stream()\n" +
                    "               .map(data1 -> (data1 == null) ? null : new ResourceMethod(parent, data1))\n" +
                    "               .collect(Collectors.toList());\n" +
                    "}";

    @Test
    public void testSimpleReadability() {
        MethodDeclaration method1 = Parser.parseMethodDeclaration(T1027B);
        MethodDeclaration method2 = Parser.parseMethodDeclaration(T1027A);

        Assert.assertEquals(0.06452, PosnettMetrics.simpleReadability(method1), 0.001);
        Assert.assertEquals(0.05361, PosnettMetrics.simpleReadability(method2), 0.001);
    }
}
