package Part2;

public class BitOperations
{
    public static byte left(byte _byte)
    {
       // System.out.println("Original Byte Value: " + BitOperationsTest.int2binary(_byte & 0xFF, 8));
        _byte = (byte)((_byte & 0b11110000) >>> 4);
       // System.out.println("new Byte Value: " + BitOperationsTest.int2binary(_byte & 0xFF, 8));
        return _byte;
    }
    public static byte right (byte _byte)
    {
        //System.out.println("Original Byte Value: " + BitOperationsTest.int2binary(_byte & 0xFF, 8));
        _byte = (byte)((_byte & 0b00001111));
        //System.out.println("new Byte Value: " + BitOperationsTest.int2binary(_byte & 0xFF, 8));
        return _byte;
    }
    public static byte sixbits(byte _byte)
    {
        _byte = (byte)((_byte >>> 2) & 0b00111111);
        return _byte;
    }
    public static short lrswap(short _in)
    {
        _in = (short)(((_in & 0x00FF) << 8) | ((_in >>> 8) & 0x00FF));
        return _in;
    }

    public static byte expander(byte _byte)
    {
        int x = _byte & 0x3F;
        int[] src = {0, 1, 3, 2, 3, 2, 4, 5}; // src[dst] = which input bit
        int out = 0;

        for (int dst = 0; dst < 8; dst++) {
            out |= ((x >>> src[dst]) & 1) << dst;
        }
        return (byte) out;
    }

    public static byte keyextractor(short _key, int _pos)
    {
        int key = _key & 0xFFFF;
        int pos = _pos & 0x0F;
        int out = 0;

        for (int i = 0; i < 8; i++) {
            int logicalIndex = (pos + i) % 16;
            int bitIndex = 15 - logicalIndex;
            int bit = (key >>> bitIndex) & 1;

            out |= bit << (7 - i);
        }

        return (byte) out;
    }

}
