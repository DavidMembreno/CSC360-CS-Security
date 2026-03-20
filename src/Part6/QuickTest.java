package Part6;

public class QuickTest {

    public static void main(String[] args) {

        short key = (short)(0b011001011);
        String[] tests = {"A", "AB", "ABC", "ABCD", "Parkour", "INNOUT", "TRITIPSANDWICH"};

        for (String s : tests) {

            byte[] encrypted = BitOperationsV.encrypt(s, key);
            String decrypted = BitOperationsV.decrypt(encrypted, key);

            System.out.println("Input:     " + s);
            System.out.println("Decrypted: " + decrypted);
            System.out.println("Match:     " + s.equals(decrypted.trim()));
            System.out.println();
        }
    }
}