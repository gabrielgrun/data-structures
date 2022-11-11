package substring.search.rabinkarp;

public class RabinKarp {

	// size of the alphabet
	private int d = 26;
	// prime number for modulo operator
	private int q = 31;

	public int search(String text, String pattern) {
		int patternLength = pattern.length();
		int textLength = text.length();

		int hashText = 0;
		int hashPattern = 0;
		// largest polynomial term in fingerprint function
		int h = 1;

		for (int i = 0; i < patternLength - 1; ++i) {
			h = (h * d) % q;
		}

		for (int i = 0; i < patternLength; ++i) {
			hashPattern = (d*hashPattern + pattern.charAt(i)) % q;
			hashText = (d*hashText + text.charAt(i)) % q; 
		}

		for (int i = 0; i <= textLength - patternLength; ++i) {
			if (hashPattern == hashText) {
				int j = 0;
				for (j = 0; j < patternLength; ++j) {
					if (text.charAt(i + j) != pattern.charAt(j)) {
						break;
					}
				}

				if (j == patternLength) {
					return i;
				}
			}

			if (i < textLength - patternLength) {
				hashText = ((hashText - text.charAt(i) * h) * d + text.charAt(i + patternLength)) % q;
				//might be negative
				if(hashText < 0) {
					hashText +=q;
				}
			}
		}
		
		return -1;
	}
}
