package firstTry;

import java.util.Stack;

public class WordCount {

    enum State {
        NOWORD {
            @Override
            State handleChar(char c, WordCount context) {
                System.out.println("NOWORD: " + c);  // Debugging
                if (context.escapedQuote) {
                    context.escapedQuote = false;
                    return this;
                }

                switch (c) {
                    case '<' -> {
                        return INHTML;
                    }

                    case '\\' -> {
                        context.escapedQuote = true;
                        return this;
                    }
                    default -> {
                        if (Character.isLetter(c)) {
                            counter++;
                            System.out.println("NOWORD Counter ++: " + c + " Counter: " + counter);  // Debugging
                            return INWORD;
                        }
                        return NOWORD;
                    }
                }
            }
        },
        INWORD {
            @Override
            State handleChar(char c, WordCount context) {
                System.out.println("INWORD: " + c + " Counter: " + counter);  // Debugging
                return switch (c) {
                    case '<' -> INHTML;
                    case '\\' -> {
                        context.escapedQuote = false;
                        yield this;
                    }
                    default -> {
                        if (!Character.isLetter(c)) {
                            yield NOWORD;
                        }
                        yield INWORD;
                    }
                };
            }
        },
        INHTML {
            @Override
            State handleChar(char c, WordCount context) {
                System.out.printf("INHTML: %s, EscapedQuote: %s, In Quote: %s, Counter: %d, Stack %s%n", c, context.escapedQuote, context.inQuote, counter, context.htmlTagStack);
                if (context.escapedQuote && c == '"') {
                    context.inQuote = !context.inQuote;  // Toggle inQuote state
                    context.escapedQuote = false;
                    return this;
                }

                if (!context.inQuote) {
                    switch (c) {
                        case '<' -> context.htmlTagStack.push(c);
                        case '>' -> {
                            if (!context.htmlTagStack.isEmpty()) {
                                context.htmlTagStack.pop();
                            }
                            if (context.htmlTagStack.isEmpty()) {
                                return NOWORD;
                            }
                        }
                    }
                }
                if (c == '\\') {
                    context.escapedQuote = true;
                }
                return this;
            }
        };
        abstract State handleChar(char c, WordCount context);
    }

    public static int counter;
    private boolean escapedQuote;  // Track if we're inside a quoted string
    private boolean inQuote;
    private Stack<Character> htmlTagStack = new Stack<>();

    public int count(String data) {
        System.out.println("\n\n");
        System.out.println("Counting: \"" + data + "\"");  // Debugging
        State state = State.NOWORD;
        counter = 0;
        escapedQuote = false;
        inQuote = false;
        htmlTagStack.clear();
        for (char c : data.toCharArray()) {
            state = state.handleChar(c, this);
        }
        return counter;
    }
}
