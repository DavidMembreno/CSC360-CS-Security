package Part2;

public class CustomTest {

    public static String int2binary(int b, int nbits) {
        int q = b;
        String result = "";
        for (int i = 0; i < nbits; ++i) {
            int r = q & 0x01;
            result = r + result;
            q = q >>> 1;
        }
        return result;
    }

    public static void main(String[] args) {

        byte b = (byte) 0b11011001; 
        byte exp = BitOperations.expander(b);
        System.out.println("expander:");
        System.out.println("  in : " + int2binary(b & 0xFF, 8) + "  (xx011001)");
        System.out.println("  out: " + int2binary(exp & 0xFF, 8) + "  (expected 01010101)");
        System.out.println();


        short key = (short) 0b0110100101101001;

        int[] poses = {2, 5, 10};
        int[] expected = {
                0b10100101,
                0b00101101,
                0b10100101
        };

        System.out.println("keyextractor:");
        System.out.println("  key: " + int2binary(key & 0xFFFF, 16));

        for (int i = 0; i < poses.length; i++) {
            int pos = poses[i];
            byte out = BitOperations.keyextractor(key, pos);
            System.out.println("  pos " + pos + ": " + int2binary(out & 0xFF, 8)
                    + "  (expected " + int2binary(expected[i], 8) + ")");
        }
    }
}
