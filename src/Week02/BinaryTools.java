package Week02;

public class BinaryTools
{
    public static String int2binary(int b, int n)
    {
        //Where n is the numbers of bits we want to print
        int q = b; // -- note that variables are initialized
        int r = 0;
        String result = "";
        for (int i = 0; i < n; ++i) {
            r = q & 0x01; // -- isolate least significant bit
            result = r + result; // -- string concatenation
            q = q >>> 1; // -- shift for next least significant bit
        }
        return result;
    }
    public static String int2binary(int b)
    {
        int q = b; // -- note that variables are initialized
        int r = 0;
        String result = "";
        for (int i = 0; i < 32; ++i) {
            r = q & 0x01; // -- isolate least significant bit
            result = r + result; // -- string concatenation
            q = q >>> 1; // -- shift for next least significant bit
        }
        return result;
    }
}
