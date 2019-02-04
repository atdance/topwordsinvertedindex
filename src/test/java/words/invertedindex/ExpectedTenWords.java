package words.invertedindex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExpectedTenWords {

	static List<Word> get(int pFrom) {

		List<Word> expectedTenWords = new ArrayList<>();
		/*
		 * Only ten words are expected to be found when more than ten words are
		 * submitted to the program
		 **/
		int to = pFrom + 11;
		for (int j = pFrom; j < to; j++) {
			expectedTenWords.add(new Word(String.valueOf(j)));
		}
		return expectedTenWords;
	}

	public static void containsAll(List<Word> expectedTenWords, Map<Integer, HashSet<Word>> firstTen) {
		List<AtomicBoolean> results = new ArrayList<>();

		Set<Entry<Integer, HashSet<Word>>> foundEntries = firstTen.entrySet();

		for (Entry<Integer, HashSet<Word>> found : foundEntries) {
			results.add(new AtomicBoolean(ExpectedTenWords.get(0).containsAll(found.getValue())));
		}
	}

}