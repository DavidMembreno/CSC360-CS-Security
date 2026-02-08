package Week01;

import java.io.FileNotFoundException;

public class DicewareMain {
    public static void main(String[]args) throws FileNotFoundException {

        Diceware d = new Diceware();

        for (int i =0; i <=25;i++)
        {
            System.out.println(d.genpassword(i,'@'));

        }
    }
}
