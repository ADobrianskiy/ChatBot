package chat_bot.stopwords;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adobrianskiy on 04.09.15.
 */
public class StopWords {
    private static List<String> words = new ArrayList<String>();
    static {
        try {
            InputStream is = new FileInputStream("stopwords_ru.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            try {
                while((line = br.readLine()) != null){
                    words.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println("Job done! StopWords added");
    }

    public static List<String> removeStopWords(List<String> tokens){
        List<String> res = new ArrayList<String>();
        for(String token: tokens){
            if(words.contains(token)){
                //System.out.println("Stop word: " + token);
            } else {
                res.add(token);
            }
        }
        return res;
    }
}
