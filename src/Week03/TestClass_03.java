package Week03;

import static Week03.BinaryTools.int2binary;
import static Week03.BitOperations.S1;
import static Week03.BitOperations.S2;

public class TestClass_03
{
    public static void main(String[] args) {
        System.out.println("S1\t\tS2");
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 8; ++j) {
                System.out.println(
                        int2binary((byte)(((i << 3) | j) & 0x0F), 4) + ": " +
                                int2binary(S1((byte)(((i << 3) | j) & 0x0F)), 3) + "\t" +
                                int2binary(S2((byte)(((i << 3) | j) & 0x0F)), 3));
            }
        }
    }
}
