package chat_bot.knowlages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adobrianskiy on 08.09.15.
 */
public class KnowlageManager {
    public static final KnowlageManager INSTANCE = new KnowlageManager();

    private KnowlageManager(){
        initGreetings();
        initJokes();
    }

    private void initJokes() {
        try {
            InputStream is = new FileInputStream("jokes.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            try {
                String joke = "";
                while((line = br.readLine()) != null){
                    if(!line.equals("") && !line.equals("\n")) {
                        if(!joke.equals("")) {
                            joke += "\n\t\t";
                        }
                        joke += line;
                    } else {
                        jokes.add(joke);
                        joke = "";
                    }

                }
                jokes.add(joke);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //System.out.println("Jokes size: " + jokes.size());
    }

    private List<String> greetings = new ArrayList<String>();
    private List<String> jokes = new ArrayList<String>();
    private Random random = new Random();



    public String getGreeting(){
        return greetings.get(random.nextInt(greetings.size()));
    }


    private void initGreetings() {
        try {
            InputStream is = new FileInputStream("greetings.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            try {
                while((line = br.readLine()) != null){
                    greetings.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println("Job done! Greetings added");
    }

    public String getJoke () {
        return jokes.get(random.nextInt(jokes.size()));
    }
    public List<String> getReplies(List<String> key_words) {
        List<String> res = new ArrayList<String>();

        return res;
    }
}
