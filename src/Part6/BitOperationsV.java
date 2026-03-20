package Part6;

public class BitOperationsV
{
    //2D Arrays for the tables
    private static final byte[][] S1_TABLE = {
            {5, 2, 1, 6, 3, 4, 7, 0},
            {1, 4, 6, 2, 0, 7, 5, 3}
    };

    private static final byte[][] S2_TABLE = {
            {4, 0, 6, 5, 7, 1, 3, 2},
            {5, 3, 0, 7, 6, 2, 1, 4}
    };
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
    public static byte S1(byte _byte)
    {
        int val = _byte & 0x0F;
        int row = (val >>> 3) & 1;
        int column = val & 0b111;
        return S1_TABLE[row][column];

    }
    public static byte S2(byte _byte)
    {
        int val = _byte & 0x0F;
        int row = (val >>> 3) & 1;
        int column = val & 0b111;
        return S2_TABLE[row][column];
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
        int key = _key & 0x1FF;
        int pos = _pos % 9;
        int out = 0;

        for (int i = 0; i < 8; i++) {
            int logicalIndex = (pos + i) % 9;
            int bitIndex = 8 - logicalIndex;
            int bit = (key >>> bitIndex) & 1;

            out |= bit << (7 - i);
        }

        return (byte) out;
    }

    public static byte feistel(byte R, byte K)
    {

        byte x = (byte)(expander(R) ^ K);

        byte s1 = S1(left(x));
        byte s2 = S2(right(x));

        return (byte)(((s1 & 0x07) << 3) | (s2 & 0x07));
    }

    //2nd Newest
    public static short[] preprocess(byte[] data)
    {
        int pad = (3 - (data.length % 3)) % 3;
        byte[] padded = new byte[data.length + pad];

        for (int i = 0; i < data.length; i++)
        {
            padded[i] = data[i];
        }

        int groups = padded.length / 3;
        short[] out = new short[groups * 2];

        int k = 0;
        for (int i = 0; i < padded.length; i += 3)
        {
            int X = padded[i]     & 0xFF;
            int Y = padded[i + 1] & 0xFF;
            int Z = padded[i + 2] & 0xFF;

            int s1 = ((X << 4) | (Y >>> 4)) & 0x0FFF;
            int s2 = (((Y & 0x0F) << 8) | Z) & 0x0FFF;

            out[k++] = (short)s1;
            out[k++] = (short)s2;
        }
        return out;
    }
    public static byte[] postprocess(short[] s)
    {
        byte[] out = new byte[(s.length / 2) * 3];

        int k = 0;
        for (int i = 0; i < s.length; i += 2)
        {
            int s1 = s[i]     & 0x0FFF;
            int s2 = s[i + 1] & 0x0FFF;


            int X = (s1 >>> 4) & 0xFF;

            int Y = ((s1 & 0x0F) << 4) | ((s2 >>> 8) & 0x0F);

            int Z = s2 & 0xFF;

            out[k++] = (byte)X;
            out[k++] = (byte)Y;
            out[k++] = (byte)Z;
        }
        return out;
    }
    public static short encode12(short plain, int round, short key9)
    {
        byte key = keyextractor(key9, round);

        int plainVal = plain & 0x0FFF;
        int L = (plainVal >>> 6) & 0x3F;
        int R = plainVal & 0x3F;

        int feistelResult = feistel((byte) R, key) & 0x3F;
        int newL = feistelResult ^ L;

        return (short) ((R << 6) | newL);
    }

    public static short decode12(short cipher, int round, short key9)
    {
        byte key = keyextractor(key9, round);

        int cipherVal = cipher & 0x0FFF;
        int L = (cipherVal >>> 6) & 0x3F;
        int R = cipherVal & 0x3F;

        int swapped = (R << 6) | L;

        int swappedL = (swapped >>> 6) & 0x3F;
        int swappedR = swapped & 0x3F;

        int feistelResult = feistel((byte) swappedR, key) & 0x3F;
        int newL = feistelResult ^ swappedL;

        int combined = (swappedR << 6) | newL;

        int combinedL = (combined >>> 6) & 0x3F;
        int combinedR = combined & 0x3F;

        return (short) ((combinedR << 6) | combinedL);
    }
    //Newest
    public static byte[] encrypt(String plaintext, short key9)
    {
        short[] blocks = preprocess(plaintext.getBytes());

        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = encode12(blocks[i], 1, key9);
        }

        return postprocess(blocks);
    }

    public static String decrypt(byte[] ciphertext, short key9)
    {
        short[] blocks = preprocess(ciphertext);

        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = decode12(blocks[i], 1, key9);
        }

        return new String(postprocess(blocks));
    }
}
