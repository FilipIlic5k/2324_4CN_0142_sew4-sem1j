import java.util.ArrayList;

public class CSVReader {
    ArrayList<String> fields;
    int indexCounter;
    enum State {
        CHAR {
            @Override
            State handleChar(char c, CSVReader context) {
                if (c == ',') {
                    context.fields.add("");
                    context.indexCounter++;
                    return DELIMITER;
                }

                if (c == '"'){
                    return OPENING_FELDBEGRENZER;
                }

                context.fields.set(context.indexCounter, context.fields.get(context.indexCounter) + c);
                return CHAR;
            }
        },
        DELIMITER{
            @Override
            State handleChar(char c, CSVReader context) {
                if (Character.isWhitespace(c)) {
                    return DELIMITER;
                }
                if (c == ',') {
                    context.indexCounter++;
                    context.fields.add("");
                    return DELIMITER;
                }
                if (c == '"'){
                    return OPENING_FELDBEGRENZER;
                }
                context.fields.set(context.indexCounter, context.fields.get(context.indexCounter) + c);
                return CHAR;
            }
        },
        OPENING_FELDBEGRENZER{
            @Override
            State handleChar(char c, CSVReader context) {
                if (c == '"') {
                    context.fields.set(context.indexCounter, context.fields.get(context.indexCounter) + "\"");
                    return CHAR;
                }
                context.fields.set(context.indexCounter, context.fields.get(context.indexCounter) + c);
                return IN_FELDBEGRENZER;
            }
        },
        IN_FELDBEGRENZER{
            @Override
            State handleChar(char c, CSVReader context) {
                if (c == '"') {
                    return CLOSING_FELDBEGRENZER;
                }
                context.fields.set(context.indexCounter, context.fields.get(context.indexCounter) + c);
                return IN_FELDBEGRENZER;
            }
        },CLOSING_FELDBEGRENZER{
            @Override
            State handleChar(char c, CSVReader context) {
                if (c == '"') {
                    context.fields.set(context.indexCounter, context.fields.get(context.indexCounter) + "\"");
                    return OPENING_FELDBEGRENZER;
                }

                if (c != ',') {
                    throw new IllegalArgumentException();
                }

                context.indexCounter++;
                context.fields.add("");
                return CHAR;
            }
        };
        abstract State handleChar(char c, CSVReader context);
    }
    public ArrayList<String> split(String data) throws Exception {
        indexCounter = 0;
        fields = new ArrayList<String>();
        fields.add("");

        State state = State.CHAR;

        data = data.replaceFirst(" *", "");

        for (char c : data.toCharArray()) {
            state = state.handleChar(c, this);
        }

        if (state == State.IN_FELDBEGRENZER) {
            throw new IllegalArgumentException();
        }
        System.out.println(fields);
        return fields;
    }
}
