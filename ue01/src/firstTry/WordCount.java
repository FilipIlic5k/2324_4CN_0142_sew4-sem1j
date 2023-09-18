package firstTry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordCount {
    public static int count(String data){
        data = data.trim();
        if(data.isEmpty()){
            return 0;
        }

        //System.out.println("Before: " + data);
        data = data.replaceAll("[^a-zA-Z]", " ");
        //System.out.println("After: " + data);

        String[] wordsRaw = data.split("\\s+");


        List<String> words = new ArrayList<>(
                Arrays.asList(wordsRaw)
        );

        words.removeIf(String::isEmpty);
        words.removeIf(s -> s.matches("[^a-zA-Z]"));

        //System.out.println(words);


        return words.size();
    }
}
