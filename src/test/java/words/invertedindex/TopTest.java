package words.invertedindex;

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

	List<Word> expected;

	List<Word> expectedBase;

	List<Word> extraWords;

	List<Word> wordsUnderTest = new ArrayList<>();

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

		expected = Arrays.asList(a, b, g, c, d, e, f, h, i, m);

		expectedBase = Arrays.asList(a, a, a, b, b, b, c, c, d, d, e, e, f, f, g, g, g, h, h, i, i, m, m);

		extraWords = Arrays.asList(new Word("zzzz"), new Word("uuuuu"), new Word("vvvvv"), new Word("tttttt"),
				new Word("xxxxxx"), new Word("yyyyy"));

		wordsUnderTest = new ArrayList<>();

		wordsUnderTest.addAll(expectedBase);

		wordsUnderTest.addAll(extraWords);

		Collections.shuffle(wordsUnderTest);
	}

	@Test
	public void shouldReturnTheTenMostFrequentWords() {

		Words<Word> frequencyTable = new InvertedIndex<Word>().getWords(wordsUnderTest);

		Map<Integer, HashSet<Word>> firstTen = Top.getTopTen(frequencyTable, 10);

		Set<Entry<Integer, HashSet<Word>>> entries = firstTen.entrySet();

		for (Entry<Integer, HashSet<Word>> element : entries) {
			assertTrue(expected.containsAll(element.getValue()));
		}
	}

}