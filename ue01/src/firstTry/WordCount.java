package firstTry;


/** A simple Buzzer class that detects html tags in plaintext and counts the words.
 * <br>
 * Provides functionality to count the number of words in a given string,
 * ignoring words inside HTML tags and dealing with escape sequences within strings.
 *
 * @author Filip Ilic
 * @version 4.0
 * @since 01.10.2023
 */
public class WordCount {

    /**
     * Represents the states that can be encountered during word counting,
     * such as being inside or outside a word, being inside an HTML tag or string, etc.
     */
    enum State {
        /**
         * State representing that the current character is not inside any word.
         */
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
        /**
         * State representing that the current character is inside a word.
         */
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
        /**
         * State representing that the current character is inside an HTML tag.
         */
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
        /**
         * State representing that the current character is inside a double-quoted string.
         */
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
        /**
         * State representing that the current character is following a backslash inside a string,
         * indicating an escape sequence.
         */
        INESCAPE {
            State handleChar(char c, WordCount context) {
                return INSTRING;
            }
        };
                /**
         * Handles the provided character based on the current state and returns the next state.
         *
         * @param c        The character to handle.
         * @param context  The WordCount instance.
         * @return         The next state.
         */
        abstract State handleChar(char c, WordCount context);
    }
    /**
     * Counter to keep track of the number of words detected.
     */
    public static int counter;

    /**
     * Counts the number of words in the provided data string, ignoring words inside HTML tags
     * and handling escape sequences within strings.
     *
     * @param data The string data to count words from.
     * @return     The number of words found in the data.
     */
    public int count(String data) {
        State state = State.NOWORD;
        counter = 0;
        for (char c : data.toCharArray()) {
            state = state.handleChar(c, this);
        }
        return counter;
    }
}
