/**
 *
 */
package words.invertedindex;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Uses the concept of the inverted index to track and rank entries .
 *
 * @param <V>
 */
public class InvertedIndex<V> {
	/**
	 * Tracks the highest index used for keys in frequencyTable
	 */
	// private int maxIndexUsed = 0;
	private AtomicInteger maxIndexUsed = new AtomicInteger();

	/**
	 * Used as position where to put elements that are placed in the collection for
	 * the very first time
	 * 
	 */
	final static Integer ZERO = Integer.valueOf(0);

	final private Words<V> frequencyTable = new Words<>();

	/**
	 * the size used to construct the frequencyTable
	 */
	final static int constructionSize = 1000;

	InvertedIndex(List<V> words) {
		for (int i = 0; i < constructionSize; i++) {
			frequencyTable.put(Integer.valueOf(i), new HashSet<V>(constructionSize));
		}

		for (V word : words) {
			int offset = searchValue(word);
			if (offset != -1) {
				move(word, offset, ++offset);

				if (offset > maxIndexUsed.intValue()) {
					maxIndexUsed.set(offset);
				}
			} else {
				insertValue(word);
			}
		}
	}

	final public Words<V> getWords() {

		return frequencyTable;
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

		for (int offset = 0; offset < maxIndexUsed.get() + 1; offset++) {
			if (frequencyTable.get(offset).contains(value)) {
				res = offset;
			}
		}

		return res;
	}

	public int getMaxIndexUsed() {
		return maxIndexUsed.get();
	}

}