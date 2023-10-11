import java.util.Random;

public class Main {


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
        String phone = "+7 (";
        while (phone.length() < 18) {
            switch (phone.length()){
                case 7:
                    phone+=")";
                case 12:
                    phone+="-";
                    break;
                case 15:
                    phone+="-";
            }
            phone+=String.valueOf(new Random().nextInt(10));
        }

        //
    }
}