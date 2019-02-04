package words.invertedindex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class TopTest {

	@Test
	public void shouldReturnTheTenMostFrequentWordsWhenMoreThanTenWordsAreParsed() {

		Words<Word> frequencyTable = new InvertedIndex<>(ThirtyWords.get(ExpectedTenWords.get(0))).getWords();

		Map<Integer, HashSet<Word>> firstTen = Top.getTopTen(frequencyTable, 10);

		Set<Entry<Integer, HashSet<Word>>> entries = firstTen.entrySet();

		for (Entry<Integer, HashSet<Word>> found : entries) {
			assertTrue(ExpectedTenWords.get(0).containsAll(found.getValue()));
		}
	}

	@Test
	public void shouldFailWihEntriesNotInExpectedSet() {

		Words<Word> frequencyTable = new InvertedIndex<>(ThirtyWords.get(ExpectedTenWords.get(9))).getWords();

		Map<Integer, HashSet<Word>> gotFirstTen = Top.getTopTen(frequencyTable, 10);

		addEntityForVerification(gotFirstTen);

		HashSet<Word> set = gotFirstTen.get(new Integer(515));

		assertFalse(ExpectedTenWords.get(0).contains(set));

	}

	private void addEntityForVerification(Map<Integer, HashSet<Word>> gotFirstTen) {
		HashSet<Word> set = new HashSet<>();
		set.add(new Word("testword"));
		gotFirstTen.put(new Integer(515), set);
	}

}