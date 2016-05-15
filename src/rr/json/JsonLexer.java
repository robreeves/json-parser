package rr.json;

import java.util.InputMismatchException;

/*
Lexer rules:
LCURL: '{' ;
RCURL: '}' ;
NUMBER: [0-9]* '.' [0-9]* ;
 */


public class JsonLexer {
    private final String input;
    private int lookAheadIndex = -1;
    private int lookAhead;
    private static final int EOF = -1;

    public JsonLexer(String input) {
        this.input = input;
        consume();
    }

    public JsonToken getNext() {
        while (lookAhead != EOF) {
            switch (lookAhead) {
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    //Ignore formatting characters
                    consume();
                    break;
                case '{':
                    matchChar('{');
                    return new JsonToken(null, JsonTokenType.LCURL);
                case '}':
                    matchChar('}');
                    return new JsonToken(null, JsonTokenType.RCURL);
                default:
                    throw new InputMismatchException(String.format("Character: %c at look ahead index %d does not match anything in language", lookAhead, lookAheadIndex));
            }
        }

        return new JsonToken(null, JsonTokenType.EOF);
    }

    /**
     * Consumes a character and sets the next look ahead character.
     */
    private void consume() {
        //Consume character
        ++lookAheadIndex;

        //Set new lookahead character
        if (lookAheadIndex < input.length()) {
            lookAhead = input.charAt(lookAheadIndex);
        }
        else {
            lookAhead = EOF;
        }
    }

    /**
     * Asserts that the look ahead matches the expected character and if so consumes a character.
     * @param expected The expected look ahead character
     */
    private void matchChar(int expected) {
        if (lookAhead == expected) {
            consume();
        }
        else {
            throw new InputMismatchException(String.format("Expected character: %c, Actual character: %c, Look ahead index: %d", lookAhead, expected, lookAheadIndex));
        }
    }
}
