package words.invertedindex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility that retrieves top elements of a list of text words.
 */
public class Top {
	/**
	 * @param frequencyTable
	 *            athe data to be filtered
	 * @param the
	 *            highest index used for keys in frequencyTable
	 * 
	 * @see words.invertedindex.InvertedIndex
	 * 
	 */
	final static public Map<Integer, HashSet<Word>> getTopTen(Map<Integer, HashSet<Word>> frequencyTable, int cursor) {

		Map<Integer, HashSet<Word>> res = new HashMap<>();

		int counter = 0;

		final int SIZE = cursor + 1;

		for (int i = SIZE; i > 0; i--) {
			if (counter == 10) {
				break;
			}
			HashSet<Word> words = frequencyTable.get(i);
			if (words.size() > 0) {
				counter++;
				res.put(new Integer(i), words);
			}
		}

		return new TreeMap<>(res).descendingMap();
	}
}