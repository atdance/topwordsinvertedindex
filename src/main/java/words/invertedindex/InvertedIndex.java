/**
 *
 */
package words.invertedindex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class InvertedIndex<V> {
	/**
	 * Tracks the highest index used for keys in frequencyTable
	 */
	int maxIndexUsed = 0;

	/**
	 * Used as position where to put elements that are placed in the collection for
	 * the very first time
	 * 
	 */
	static final Integer ZERO = new Integer(0);

	final public Map<Integer, HashSet<V>> frequencyTable = new HashMap<>();

	final int maxSize = 1000;

	InvertedIndex() {
		for (int i = 0; i < maxSize; i++) {
			frequencyTable.put(new Integer(i), new HashSet<V>(maxSize));
		}
	}

	@SuppressWarnings("unchecked")
	final public Map<Integer, HashSet<V>> getWords(List<Word> words) {
		for (Word word : words) {
			handleValue((V) word);
		}
		return frequencyTable;
	}

	final protected void handleValue(final V pValue) {
		int offset = searchValue(pValue);
		if (offset != -1) {
			move(pValue, offset, ++offset);

			if (offset > maxIndexUsed) {
				maxIndexUsed = offset;
			}
		} else {
			insertValue(pValue);
		}

	}

	final public void insertValue(final V token) {
		frequencyTable.get(ZERO).add(token);
	}

	final public void move(final V value, final int from, final int to) {

		frequencyTable.get(from).remove(value);

		frequencyTable.get(to).add(value);
	}

	final public int searchValue(final V value) {
		int res = -1;

		for (int offset = 0; offset < maxIndexUsed + 1; offset++) {
			if (frequencyTable.get(offset).contains(value)) {
				res = offset;
			}
		}

		return res;
	}

	public int getMaxIndexUsed() {
		return maxIndexUsed;
	}

}