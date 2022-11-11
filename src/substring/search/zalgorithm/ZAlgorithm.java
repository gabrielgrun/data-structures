package substring.search.zalgorithm;

public class ZAlgorithm {

	private String text;
	private String pattern;
	private int[] zTable;
	
	public ZAlgorithm(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		this.zTable = new int[text.length() + pattern.length()];
	}
	
	public void search() {
		constructTable();
		
		for (int i = 0; i < zTable.length; i++) {
			if(zTable[i] >= pattern.length()) {
				System.out.println("Match found at index " + (i-pattern.length()));
			}
		}
	}

	private void constructTable() {
		String patternText = pattern + text;
		int patternTextLength = patternText.length();
		int left = 0;
		int right = 0;
		
		for (int k = 1; k < patternTextLength; k++) {
			if(k > right) {
				
				int matchCounter = 0;
				while(k+matchCounter < patternTextLength && patternText.charAt(matchCounter) == patternText.charAt(k+matchCounter)) {
					matchCounter++;
				}
				zTable[k] = matchCounter;
				
				if(matchCounter > 0) {
					left = k;
					right = k + matchCounter -1; 
				}
			} else {
				int p = k - left;
				
				if(zTable[p]<right-k+1) {
					zTable[k] = zTable[p];
				} else {
					int i = right + 1;
					while(i<patternTextLength && patternText.charAt(i) == patternText.charAt(i-k)) {
						i++;
					}
					
					zTable[k] = i-k;
					left = k;
					right = i-1;
				}
			}
		}
	}
}
