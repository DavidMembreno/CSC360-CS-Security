package Part4;


public class BitOperationsPartIVTest {

	public static String int2binary(long b, int bits)
	{
		long r = 0;
		String result = "";
		for (int i = 0; i < bits; ++i) {
			r = b & 0x01; // -- isolate least significant bit
			result = r + result; // -- string concatenation
			b = b >>> 1;  // -- shift for next least significant bit
		}
		return result;
	}

	public static void main(String[] args) {

		// -- set the key
		byte K = (byte)(0b10101010 & 0xFF);
		System.out.println("\t S Feistel");
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 8; ++j) {
				// -- set up index to S boxes from i and
				byte Sindex = (byte)(((i << 3) | j) & 0x0F);
				// -- combine 3 bits from S1 and S2 into a 6 bits
				byte R = (byte)((BitOperationsIV.S1(Sindex) << 3) | BitOperationsIV.S2((byte)(Sindex)));
				// -- send S values to Feistel function with the key
				byte f = BitOperationsIV.feistel(R, K);
				System.out.println(
						int2binary((byte)(((i << 3) | j) & 0x0F), 4) +
	                    ": " +
	                    int2binary(R, 6) + " " +
	                    int2binary(f, 6)
	                    );
			}
		}
	}

}
