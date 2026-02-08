package Week02;

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

}
