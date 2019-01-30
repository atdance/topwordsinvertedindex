package words.invertedindex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TopTest {

	List<Word> expectedTenWords;

	List<Word> extraWords;

	List<Word> moreThanTenWordswords = new ArrayList<>();

	@Test
	public void shouldReturnTheTenMostFrequentWordsWhenMoreThanTenWordsAreParsed() {

		Words<Word> frequencyTable = new InvertedIndex<Word>().getWords(moreThanTenWordswords);

		Map<Integer, HashSet<Word>> firstTen = Top.getTopTen(frequencyTable, 10);

		Set<Entry<Integer, HashSet<Word>>> entries = firstTen.entrySet();

		for (Entry<Integer, HashSet<Word>> found : entries) {
			assertTrue(expectedTenWords.containsAll(found.getValue()));
		}
	}

	@Test
	public void shouldFailWihEntriesNotInExpectedSet() {

		Words<Word> frequencyTable = new InvertedIndex<Word>().getWords(moreThanTenWordswords);

		Map<Integer, HashSet<Word>> gotFirstTen = Top.getTopTen(frequencyTable, 10);

		addEntityForVerification(gotFirstTen);

		HashSet<Word> set = gotFirstTen.get(new Integer(515));

		assertFalse(expectedTenWords.contains(set));

	}

	private void addEntityForVerification(Map<Integer, HashSet<Word>> gotFirstTen) {
		HashSet<Word> set = new HashSet<>();
		set.add(new Word("testword"));
		gotFirstTen.put(new Integer(515), set);
	}

	@Before
	public void setUp() {
		final Word a = new Word("a");
		final Word b = new Word("b");
		final Word c = new Word("c");
		final Word d = new Word("d");
		final Word e = new Word("e");
		final Word f = new Word("f");
		final Word g = new Word("g");
		final Word h = new Word("h");
		final Word i = new Word("i");
		final Word m = new Word("m");

		/*
		 * Only ten words are expected to be found when more than ten words are
		 * submitted to the program
		 **/
		expectedTenWords = Arrays.asList(a, b, g, c, d, e, f, h, i, m);

		/*
		 * These ten words are present 3 times
		 **/
		for (int j = 0; j < 3; j++) {
			moreThanTenWordswords.addAll(expectedTenWords);

		}
		/*
		 * These words are present only one time
		 **/
		extraWords = Arrays.asList(new Word("z"), new Word("u"), new Word("v"), new Word("t"), new Word("x"),
				new Word("y"));

		moreThanTenWordswords.addAll(extraWords);

		Collections.shuffle(moreThanTenWordswords);
	}

}