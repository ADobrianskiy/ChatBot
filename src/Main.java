import chat_bot.ChatBot;

import java.io.*;

/**
 * Created by adobrianskiy on 04.09.15.
 */
public class Main {

    public static void main(String[] args){
        System.out.println("Creating bot....");
        ChatBot bot = new ChatBot("Bot name");

        String message;
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(ir);
        while(true){
            System.out.print("Your message: ");
            try {
                message = input.readLine();
                System.out.println(bot.getReply(message));

                if(message.toLowerCase().contains("до встречи")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
