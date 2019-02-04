package words.invertedindex;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Words<V> {

	final private Map<Integer, HashSet<V>> frequencyTable;

	public Words() {
		frequencyTable = new ConcurrentHashMap<>();
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