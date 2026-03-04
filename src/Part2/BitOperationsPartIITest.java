package Part2;

public class BitOperationsPartIITest {
	
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
		byte b = (byte)0b11011001;
		System.out.println("expander: ");
		System.out.println("\t" + int2binary(BitOperations.expander(b), 8));
		
		short key = 0b0110100101101001;
		System.out.println("key extractor: ");
		for (int pos = 0; pos <= 15; ++pos) {
			System.out.println("\t" + int2binary(BitOperations.keyextractor(key, pos), 8));
		}
	}
}
