package firstTry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordCount {
    public static int count(String data){
        if(data.isEmpty()){
            return 0;
        }

        System.out.println("Before: " + data);
        data = data.replaceAll("<(\"[^\"]*\"|'[^']*'|[^'\">])*>", " ");
        data = data.replaceAll("<[^>]*$", " ");
        data = data.replaceAll("[^a-zA-Z]", " ");
        data = data.trim();
        System.out.println("After: " + data);

        List<String> words = new ArrayList<>(
                Arrays.asList(data.split("\\s+"))
        );

        words.removeIf(String::isEmpty);
        words.removeIf(s -> s.matches("[^a-zA-Z]"));

        System.out.println(words);


        return words.size();
    }

}
