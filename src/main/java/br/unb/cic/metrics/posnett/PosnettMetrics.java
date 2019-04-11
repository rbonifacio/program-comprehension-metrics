package br.unb.cic.metrics.posnett;

import com.github.javaparser.JavaToken;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.*;

public class PosnettMetrics {

    public static double comprehensionDegree(MethodDeclaration method) {
        return 8.87 -0.003 * volume(method) + 0.40 * lines(method) - 1.5 * entropy(method);
    }

    public static int lines(MethodDeclaration method) {
        int begin = method.getBegin().get().line;
        int end = method.getEnd().get().line;
        return  (end - begin) + 1;
    }

    public static double volume(MethodDeclaration method) {
        return length(method) * log2(vocabulary(method));
    }


    public static double entropy(MethodDeclaration method) {
        HashMap<String, Double> occurences = new HashMap<>();
        Iterator<JavaToken> it = method.getBody().get().getTokenRange().get().iterator();

        while(it.hasNext()) {
            JavaToken token = it.next();

            if(token.getText().equals(" ")) {
                continue;
            }

            double value = 1;
            String key = token.getText();
            if(occurences.containsKey(key)) {
                value = occurences.get(key) + 1;
            }
            occurences.put(key, value);
        }

        double total = occurences.values().stream().reduce(Double::sum).get();

        double sum = 0;

        for(String x : occurences.keySet()) {
            double pX = occurences.get(x) / total;
            sum += pX * log2(pX);
        }

        return -sum;
    }

    public static int length(MethodDeclaration method) {
        List<String> operators = new ArrayList<>();
        List<String> operands = new ArrayList<>();

        collectTokens(method, operators, operands);

        return operators.size() + operands.size();
    }

    public static int vocabulary(MethodDeclaration  method) {
        Set<String> operators = new HashSet<>();
        Set<String> operands = new HashSet<>();

        collectTokens(method, operators, operands);

        return operators.size() + operands.size();
    }

    private static void collectTokens(MethodDeclaration method, Collection<String> operators, Collection<String> operands) {
        Iterator<JavaToken> it = method.getBody().get().getTokenRange().get().iterator();

        while(it.hasNext()) {
            JavaToken token = it.next();

            switch(token.getCategory()) {
                case OPERATOR: { operators.add(token.getText()); break; }
                case LITERAL: { operands.add(token.getText()); break; }
                case IDENTIFIER: {
                    if(token.getNextToken().isPresent() && token.getNextToken().get().getText().equals("(")) {
                        operators.add(token.getText());
                    }
                    else {
                        operands.add(token.getText());
                    }
                    break;
                }
                default: {} // do nothing!
            }
        }
    }

    private static double log2(double v) {
       return Math.log10(v) / Math.log10(2);
    }
}
