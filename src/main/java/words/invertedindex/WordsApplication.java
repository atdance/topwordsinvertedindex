package words.invertedindex;

import java.io.IOException;
import java.util.List;

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

		InvertedIndex<Word> index = new InvertedIndex<>(words);

		Words<Word> frequencyTable = index.getWords();

		int cursor = index.getMaxIndexUsed();

		new PrintableInvertedIndex(frequencyTable, cursor).print();
	}

}