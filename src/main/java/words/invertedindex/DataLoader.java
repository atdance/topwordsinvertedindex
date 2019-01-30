package words.invertedindex;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Utility class for loading text data from file. This class cannot be
 * instantiated
 *
 */
public class DataLoader {
	/**
	 * for pattern matching
	 */
	private static final Pattern PATTERN = Pattern.compile("[^\\w'-]+");

	private DataLoader() {
	}

	static List<Word> loadDataFromFile(String pFileName)
			throws MalformedInputException, IllegalArgumentException, IOException {
		List<Word> words = new ArrayList<>();

		try {
			Path path = Paths.get(pFileName);

			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

			if (lines.size() == 0) {
				throw new IllegalArgumentException("file is empty");
			}

			for (String line : lines) {

				String[] wordsInLine = PATTERN.split(line.toLowerCase());
				for (String text : wordsInLine) {
					words.add(new Word(text));
				}

			}
		} catch (IOException e) {
			throw e;
		}
		return words;
	}
}