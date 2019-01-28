package words.invertedindex;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility that retrieves top elements of a list of text words.
 */
public class Top {
	/**
	 * @param <V>
	 * @param frequencyTable
	 *            athe data to be filtered
	 * @param the
	 *            highest index used for keys in frequencyTable
	 * 
	 * @see words.invertedindex.InvertedIndex
	 * 
	 */
	final static public <V> Map<Integer, HashSet<V>> getTopTen(Words<V> frequencyTable, int cursor) {

		Words<V> res = new Words<>();

		int counter = 0;

		final int SIZE = cursor + 1;

		for (int i = SIZE; i > 0; i--) {
			if (counter == 10) {
				break;
			}
			HashSet<V> words = frequencyTable.get(i);
			if (words.size() > 0) {
				counter++;
				res.put(Integer.valueOf(i), words);
			}
		}

		Map<Integer, HashSet<V>> all = res.getAll();

		TreeMap<Integer, HashSet<V>> treeMap = new TreeMap<>(all);

		return treeMap.descendingMap();
	}
}