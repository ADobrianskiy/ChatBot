package chat_bot;


import chat_bot.knowlages.KnowlageManager;
import chat_bot.stopwords.StopWords;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.russianStemmer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by adobrianskiy on 04.09.15.
 */
public class ChatBot {
    private List<String> replies = new ArrayList<String>();
    private String name;

    public ChatBot(String name){
        this.name = name;
        System.out.println("Chat bot created");
    }

    public String getReply(String text){
        List<String> words = getMainWords(text.replaceAll("[^а-яa-Я ]", ""));
        SnowballStemmer stemmer = new russianStemmer();
        List<String> stemmed = new ArrayList<String>();

        for(String word: words){
            stemmer.setCurrent(word);
            if(stemmer.stem()){
                stemmed.add(stemmer.getCurrent());
            }
        }
        String reply;

        KnowlageManager.INSTANCE.setStemmedWords((ArrayList<String>) stemmed);

        List<String> possible_replies = KnowlageManager.INSTANCE.getReplies(stemmed);

        reply = chooseReply(possible_replies);

        if(reply != null) {
            replies.add(reply);
        }

        if(replies.size() <= 1){
            String greetings = KnowlageManager.INSTANCE.getGreeting();
            reply = greetings + reply;
        }

        return name + ": " + reply;
    }

    private String chooseReply(List<String> possible_replies) {
        if(possible_replies == null || possible_replies.size() == 0) {
            return null;
        }

        Random r = new Random();
        return possible_replies.get(r.nextInt(possible_replies.size()));
    }

    private List<String> getMainWords(String text){
        StringTokenizer tz = new StringTokenizer(text);
        List<String> tokens = new ArrayList<String>();

        while(tz.hasMoreTokens()) {
            tokens.add(tz.nextToken().toLowerCase());
        }

        return StopWords.removeStopWords(tokens);
    }
}
