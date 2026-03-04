package Part5;

public class QuickTest {
//In theorry should return inputs cleanly
    public static void main(String[] args) {

        String[] tests = {"A", "AB", "ABC", "ABCD", "Parkour", "INNOUT", "TRITIPSANDWICH"};

        for (String s : tests) {

            short[] packed = BitOperationsIV.preprocess(s);
            byte[] unpacked = BitOperationsIV.postprocess(packed);

            String rebuilt = new String(unpacked);

            System.out.println("Input:  " + s);
            System.out.println("Output: " + rebuilt);
            System.out.println();
        }
    }
}