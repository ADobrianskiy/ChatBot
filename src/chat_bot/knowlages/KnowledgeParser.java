package chat_bot.knowlages;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Dima on 10.09.2015.
 */
public class KnowledgeParser {

    private File knowledgeFile;

    private static String currentString;

    private static InputStream inputStream;

    private static InputStreamReader inputStreamReader;

    private static BufferedReader bufferedReader;

    public KnowledgeParser(File inputFile){
        knowledgeFile = inputFile;
        try {
            inputStream = new FileInputStream(knowledgeFile);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean hasNext(){
        try {
            currentString = bufferedReader.readLine();
            if(currentString == null) {
                return false;
            }else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getWord(){
        while(currentString != null && currentString.charAt(0) != '|'){
            try {
                currentString = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(currentString == null) return null;
        return (String) currentString.subSequence(1,currentString.length()-1);
    }

    public void getPhrases(ArrayList<String> answers, char state) throws IOException {


        while(currentString.charAt(0) != state){
            currentString = bufferedReader.readLine();
        }

        currentString = bufferedReader.readLine();

        while(currentString.charAt(0) != state){
            answers.add(currentString);
            currentString = bufferedReader.readLine();

        }
    }

    public void stop(){
        try {
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
