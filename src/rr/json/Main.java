package rr.json;

public class Main {

    public static void main(String[] args) {
	    if (args.length == 1) {
            JsonLexer lexer = new JsonLexer(args[0]);
        }
        else {
            System.err.println("***Invalid input***");
            printUsage();
        }
    }

    private static void printUsage() {
        //TODO
    }
}