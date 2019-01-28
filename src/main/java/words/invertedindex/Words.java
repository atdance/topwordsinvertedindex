package words.invertedindex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Words<V> {

	final private Map<Integer, HashSet<V>> frequencyTable;

	public Words() {
		frequencyTable = new HashMap<>();
	}

	public HashSet<V> get(int from) {
		return frequencyTable.get(from);
	}

	public void put(Integer integer, HashSet<V> words) {
		frequencyTable.put(integer, words);

	}

	public Map<Integer, HashSet<V>> getAll() {
		return frequencyTable;
	}

}
