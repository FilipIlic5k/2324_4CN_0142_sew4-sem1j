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
                /*
                if (c == '"'){
                    //TODO: implement Feldbegrenzer
                }
                */
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
                /*
                if (c == '"'){
                    //TODO: implement Feldbegrenzer
                }
                */
                context.fields.set(context.indexCounter, context.fields.get(context.indexCounter) + c);
                return CHAR;
            }
        };
        abstract State handleChar(char c, CSVReader context);
    }
    public ArrayList<String> split(String data) {
        indexCounter = 0;
        fields = new ArrayList<String>();
        fields.add("");

        State state = State.CHAR;

        for (char c : data.toCharArray()) {
            state = state.handleChar(c, this);
        }
        System.out.println(fields);
        return fields;
    }
}
