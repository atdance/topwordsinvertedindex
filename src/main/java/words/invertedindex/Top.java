package words.invertedindex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
	final static public List<HashSet<Word>> getTopTen(Map<Integer, HashSet<Word>> frequencyTable, int cursor) {

		List<HashSet<Word>> res = new ArrayList<>();

		int counter = 0;

		final int SIZE = cursor + 1;

		for (int i = SIZE; i > 0; i--) {
			if (counter == 10) {
				break;
			}
			HashSet<Word> words = frequencyTable.get(i);
			if (words.size() > 0) {
				counter++;
				System.out.println("" + (i + 1) + " " + words);

				res.add(frequencyTable.get(i));
			}
		}
		return res;
	}

}