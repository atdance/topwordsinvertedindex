/**
 *
 */
package words.invertedindex;

import java.util.HashSet;
import java.util.List;

/**
 * Uses the concept of the inverted index to track and rank entries .
 *
 * @param <V>
 */
public class InvertedIndex<V> {
	/**
	 * Tracks the highest index used for keys in frequencyTable
	 */
	private int maxIndexUsed = 0;

	/**
	 * Used as position where to put elements that are placed in the collection for
	 * the very first time
	 * 
	 */
	static final Integer ZERO = new Integer(0);

	final private Words<V> frequencyTable = new Words<>();

	/**
	 * the size used to construct each HashSet used as a value of the entries of the
	 * requencyTable
	 */
	final int maxSize = 1000;

	InvertedIndex() {
		for (int i = 0; i < maxSize; i++) {
			frequencyTable.put(new Integer(i), new HashSet<V>(maxSize));
		}
	}

	final public Words<V> getWords(List<V> words) {
		for (V word : words) {
			handleValue(word);
		}
		return frequencyTable;
	}

	final private void handleValue(final V pValue) {
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

	final private void insertValue(final V token) {
		frequencyTable.get(ZERO).add(token);
	}

	final private void move(final V value, final int from, final int to) {

		frequencyTable.get(from).remove(value);

		frequencyTable.get(to).add(value);
	}

	final private int searchValue(final V value) {
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