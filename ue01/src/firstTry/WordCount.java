package firstTry;

public class WordCount {

    enum State {
        NOWORD {
            @Override
            State handleChar(char c, WordCount context) {
                if (Character.isLetter(c)) {
                    counter++;
                    return INWORD;
                }
                if (c == '<') {
                    return INHTML;
                } else {
                    return this;
                }
            }
        },
        INWORD {
            @Override
            State handleChar(char c, WordCount context) {
                if (Character.isLetter(c)) {
                    return this;
                }
                if (c == '<') {
                    return INHTML;
                } else {
                    return NOWORD;
                }
            }
        },
        INHTML {
            @Override
            State handleChar(char c, WordCount context) {
                if (c == '>') {
                    return NOWORD;
                }
                if (c == '"') {
                    return INSTRING;
                } else {
                    return this;
                }
            }
        },
        INSTRING {
            @Override
            State handleChar(char c, WordCount context) {
                if (c == '"') {
                    return INHTML;
                }
                if (c == '\\') {
                    return INESCAPE;
                } else {
                    return this;
                }
            }
        },
        INESCAPE {
            State handleChar(char c, WordCount context) {
                return INSTRING;
            }
        };

        abstract State handleChar(char c, WordCount context);
    }

    public static int counter;
    public int count(String data) {
        State state = State.NOWORD;
        counter = 0;
        for (char c : data.toCharArray()) {
            state = state.handleChar(c, this);
        }
        return counter;
    }
}
