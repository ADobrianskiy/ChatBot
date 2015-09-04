import chat_bot.ChatBot;

/**
 * Created by adobrianskiy on 04.09.15.
 */
public class Main {

    public static void main(String[] args){
        System.out.println("Creating bot....");
        ChatBot bot = new ChatBot();

        System.out.println(bot.getReply("Я иду в лес по грибы"));
        System.out.println("That`s all");
    }

}
