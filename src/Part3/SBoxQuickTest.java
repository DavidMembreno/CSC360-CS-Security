package Part3;

import static Part3.BinaryTools.int2binary;

public class SBoxQuickTest {
    public static void main(String[] args) {

        // Expected values from the PDF tables (3-bit outputs stored as ints 0..7)
        int[][] S1_EXPECTED = {
                {5, 2, 1, 6, 3, 4, 7, 0},
                {1, 4, 6, 2, 0, 7, 5, 3}
        };

        int[][] S2_EXPECTED = {
                {4, 0, 6, 5, 7, 1, 3, 2},
                {5, 3, 0, 7, 6, 2, 1, 4}
        };

        System.out.println("in    S1(out) ok?   S2(out) ok?");
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 8; col++) {

                byte in = (byte)(((row << 3) | col) & 0x0F);

                int s1 = BitOperations.S1(in) & 0xFF;
                int s2 = BitOperations.S2(in) & 0xFF;

                int e1 = S1_EXPECTED[row][col];
                int e2 = S2_EXPECTED[row][col];

                boolean ok1 = (s1 == e1);
                boolean ok2 = (s2 == e2);

                System.out.println(
                        int2binary(in & 0x0F, 4) + "  " +
                                int2binary(s1, 3) + "   " + ok1 + "    " +
                                int2binary(s2, 3) + "   " + ok2
                );
            }
        }
    }
}
