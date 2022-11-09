package generichashtables;

public class App {

	
	public static void main(String[] args) {
		HashTable<String, Integer> hashTable = new HashTable<>();
		hashTable.put("Gabriel", 23);
		hashTable.put("Everton", 24);
		hashTable.put("Junior", 27);
		
		System.out.println(hashTable.get("Gabriel"));
	}
	
	
}
