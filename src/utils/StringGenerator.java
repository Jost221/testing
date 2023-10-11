package utils;

import java.util.Random;
public class StringGenerator {
    public String GenerateString(int lenght){
        String result = new String();
        for(int i = 0; i < lenght; i++) result+= (char) (65+new Random().nextInt(25));
        return result;
    }
}
