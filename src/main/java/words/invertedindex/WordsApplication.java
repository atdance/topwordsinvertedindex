package words.invertedindex;

import java.io.IOException;
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

		Top.getTopTen(frequencyTable, cursor);
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

}