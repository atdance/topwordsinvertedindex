package words.invertedindex;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class WordsApplication {

	/**
	 * A command-line program that counts unique words from a text file and lists
	 * the top 10 occurrences
	 * 
	 *
	 * Limitations: It has an untested behavior with files that do not contain text
	 * . It does not work with lists of words larger that Integer.MAX_VALUE.
	 *
	 * @param args
	 *            a file path
	 */

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("please provide a file path as argument");
		}

		String fileName = args[0];

		List<Word> words = null;
		try {
			words = DataLoader.loadDataFromFile(fileName);
		} catch (IllegalArgumentException | IOException e) {
			System.out.println("Could not load file " + fileName);
			return;
		}

		InvertedIndex<Word> index = new InvertedIndex<>();

		Map<Integer, HashSet<Word>> frequencyTable = index.getWords(words);

		int cursor = index.getMaxIndexUsed();

		top(frequencyTable, cursor);

		System.out.println(getSize(frequencyTable));

	}

	final static public void top(Map<Integer, HashSet<Word>> frequencyTable, int cursor) {

		int counter = 0;

		final int SIZE = cursor + 1;

		for (int i = SIZE; i > 0; i--) {
			if (counter == 10) {
				break;
			}

			if (frequencyTable.get(i).size() > 0) {
				counter++;
				System.out.println("" + (i + 1) + " " + frequencyTable.get(i));
			}
		}

	}

	/**
	 * Goes through all the HashSet values of the frequencyTable and counts existing
	 * elements
	 * 
	 * @return size as integer
	 */
	public static int getSize(Map<Integer, HashSet<Word>> frequencyTable) {
		List<Entry<Integer, HashSet<Word>>> collect = frequencyTable.entrySet().stream().collect(Collectors.toList());

		LongAdder size = new LongAdder();

		collect.forEach(a -> {
			if (!a.getValue().isEmpty()) {
				size.add(a.getValue().size());
			}
		});

		return size.intValue();
	}

	final static public void topNew(Map<Integer, HashSet<Object>> frequencyTable, int cursor) {

		// frequencyTable.entrySet().stream().forEach(a -> {
		// if (!a.getValue().isEmpty()) {
		// System.out.println(a.getKey() + " " + a.getValue());
		// }
		// });

		List<Entry<Integer, HashSet<Object>>> collect = frequencyTable.entrySet().stream().collect(Collectors.toList());

		Collections.reverse(collect);

		collect.subList(100, 816).forEach(a -> {
			if (!a.getValue().isEmpty()) {
				System.out.println(a.getKey() + " " + a.getValue());
			}
		});
	}

}
