package chat_bot;


import chat_bot.knowlages.KnowlageManager;
import chat_bot.stopwords.StopWords;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.russianStemmer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String getAnswer(String text){
        String res = "";
        Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher reMatcher = re.matcher(text);
        while (reMatcher.find()) {
            String sentence = reMatcher.group();
            String reply = getReply(sentence);
            if(reply != null && !reply.equals("")){
                res += reply;
            }
        }

        if(res.equals("")) {
            res = KnowlageManager.INSTANCE.getJoke();
        }

        if(replies.size() < 1){
            String greetings = KnowlageManager.INSTANCE.getGreeting();
            res = greetings + res;
        }

        replies.add(res);
        return name + ": " + res;
    }

    private String getReply(String text){
        List<String> words = getMainWords(text.replaceAll("[^а-яa-Я ]", ""));
        SnowballStemmer stemmer = new russianStemmer();
        List<String> stemmed = new ArrayList<String>();

        for(String word: words){
            stemmer.setCurrent(word);
            if(stemmer.stem()){
                stemmed.add(stemmer.getCurrent());
            }
        }

        List<String> possible_replies = KnowlageManager.INSTANCE.getReplies(stemmed);
        String reply = chooseReply(possible_replies);

        return reply;
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
