package br.unb.cic.metrics;

import br.unb.cic.metrics.posnett.Parser;
import br.unb.cic.metrics.posnett.PosnettMetrics;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main program of the project.
 */
public class Main {

    public static void main(String[] args) {
        if(args.length == 1) {
            System.out.println(PosnettMetrics.entropy(Parser.parseMethodDeclaration(args[0])));
        }
        else if(args.length == 2 && args[0].equals("--file")) {
            try {
                String contents = new String(Files.readAllBytes(Paths.get(args[1])));
                System.out.println(contents);
                System.out.println(PosnettMetrics.simpleReadability(Parser.parseMethodDeclaration(contents)));
            }
            catch(Exception e) {
                System.err.print(e.getMessage());
            }
        }
        else {
            System.err.println("Invalid program args.");
            System.err.println("Please try: ");
            System.err.println("java -jar comprehension.jar <method_body>");
            System.err.println("java -jar comprehension.jar --file <file-path>");
        }
    }
}
