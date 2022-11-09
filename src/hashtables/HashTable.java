package hashtables;

public class HashTable {

	private static final int TABLE_SIZE = 10;
	private HashItem[] hashTable;
	
	public HashTable() {
		hashTable = new HashItem[TABLE_SIZE];
	}
	
	public int get(int key) {
		int index = hash(key);
		
		if(hashTable[index] == null) {
			return -1;
		}
		
		HashItem item = hashTable[index];
		while(item != null && item.getKey() != key) {
			item = item.getNextItem();
		}
		
		if(item == null) {
			return -1;
		}
		
		return item.getValue();
	}
	
	public void put(int key, int value) {
		int index = hash(key);
		
		if(hashTable[index] == null) {
			System.out.println("No collision - simple insertion...");
			hashTable[index] = new HashItem(key, value);
		} else {
			System.out.println("Collision when inserting with key " + key);
			HashItem item = hashTable[index];
			
			while(item.getNextItem() != null) {
				System.out.println("Considering the next item in the linked list " + item.getValue());
				item = item.getNextItem();
			}
			
			System.out.println("Finally have found the place to insert...");
			item.setNextItem(new HashItem(key, value));
		}
	}
	
	private int hash(int key) {
		return key % TABLE_SIZE;
	}
}
