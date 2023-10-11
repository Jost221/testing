import java.util.Random;

public class Main {
    private static String generateNumber(int count){
        String result = new String();
        for(int i = 0; i < count; i++) result+= String.valueOf(new Random().nextInt(10));
        return result;
    }

    public static void main(String[] args) {
        String[] base_mail = {"@mail.ru", "@gmail.com", "@yandex.ru", "@elixirsd.com"};
        String numbers = "0123456789";
        String alphavit = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM.";

        // email
        String email = new String();
        int len = new Random().nextInt(20);
        for(int i = 0; i < len; i++){
            email += new Random().nextInt(2) == 0 ?
                    alphavit.charAt(new Random().nextInt(alphavit.length())) : numbers.charAt(new Random().nextInt(numbers.length()));
        }
        email+= base_mail[new Random().nextInt(base_mail.length)];

        // phone
        String phone = String.format(
                "+7 (%s)-%s-%s-%s",
                generateNumber(3),
                generateNumber(3),
                generateNumber(2),
                generateNumber(2)
                );

        System.out.println(email);
        System.out.println(phone);
    }
}