package Part5;

import static Part5.BinaryTools.int2binary;
import static Part5.BitOperationsIV.postprocess;
import static Part5.BitOperationsIV.preprocess;



public class MainTester {

    public static void main (String[] args) {
        short[] data12;
        byte[] data8;
        data12 = preprocess("A");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();
        data12 = preprocess("AB");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();
        data12 = preprocess("ABC");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();
        data12 = preprocess("ABCD");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();
        data12 = preprocess("ABCDEF");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();
    }
}
