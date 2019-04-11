package br.unb.cic.metrics.posnett;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class Parser {
    public static MethodDeclaration parseMethodDeclaration(String str) {
        ParserConfiguration config = new ParserConfiguration();
        config.setLexicalPreservationEnabled(true);
        config.setStoreTokens(true);
        config.setLexicalPreservationEnabled(true);

        JavaParser parser = new JavaParser(config);

        BodyDeclaration declaration = parser.parseBodyDeclaration(str);

        return declaration.asMethodDeclaration();
    }
}
