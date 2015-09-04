package chat_bot;


import chat_bot.stopwords.StopWords;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.russianStemmer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by adobrianskiy on 04.09.15.
 */
public class ChatBot {

    public ChatBot(){
        System.out.println("Chat bot created");
    }

    public String getReply(String text){
        System.out.println("Human: text");
        List<String> words = getMainWords(text);
        SnowballStemmer stemmer = new russianStemmer();
        List<String> stemmed = new ArrayList<String>();

        for(String word: words){
            stemmer.setCurrent(word);
            if(stemmer.stem()){
                stemmed.add(stemmer.getCurrent());
            }
        }

        System.out.println("Stemmed: " + stemmed);

        return "Bot: Answer";
    }

    private List<String> getMainWords(String text){
        StringTokenizer tz = new StringTokenizer(text);
        List<String> tokens = new ArrayList<String>();
        while(tz.hasMoreTokens()) {
            tokens.add(tz.nextToken());
        }
        return StopWords.removeStopWords(tokens);
    }
}
