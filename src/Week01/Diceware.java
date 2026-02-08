package Week01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Diceware implements DicewareInterface
{

    private final Map<String,String> diceMap = new HashMap<>(); //Like dict... simpler

    public Diceware() throws FileNotFoundException
    {
        loadEFFList();
    }

    private void loadEFFList() throws FileNotFoundException
    {
        File file = new File(DicewareInterface.wordlist);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
        {
            String line = sc.nextLine().trim(); //Returns a string whose value is this string, with all leading and trailing space removed

            if(line.isEmpty()) continue;
            String[] sections = line.split("\\s+"); //split on whitespace/spaces...tabs
            String key = sections[0], word = sections[1];
            diceMap.put(key,word);//We are now working with a map and accesing the file once
        }
        sc.close();// Good habit ig
    }

    @Override
    public String genpassword(int minlength, char separator) throws FileNotFoundException // the exception became uneccessary but its fine
    {
        StringBuilder sb = new StringBuilder();

        while(sb.length() < minlength)
        {
            if(sb.length() > 0){ sb.append(separator); }

            StringBuilder keyBuilder = new StringBuilder();
            for(int i = 0; i < 5; i++)
            {
                int r = DicewareInterface.rn.nextInt(6) + 1;
                keyBuilder.append(r);
            }

            String key = keyBuilder.toString();

            //looking up now to append to sb
            String word = diceMap.get(key);
            sb.append(word);
        }

        return sb.toString();
    }

}
