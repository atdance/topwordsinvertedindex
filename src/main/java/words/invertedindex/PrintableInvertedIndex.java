package words.invertedindex;

import java.util.HashSet;
import java.util.Map;

public class PrintableInvertedIndex {

	private Words<Word> frequencyTable;
	private int cursor;
	private final static int ONE = 1;

	public PrintableInvertedIndex(Words<Word> pFrequencyTable, int pCursor) {
		frequencyTable = pFrequencyTable;
		cursor = pCursor;
	}

	public void print() {
		Map<Integer, HashSet<Word>> firstTen = Top.getTopTen(frequencyTable, cursor);
		firstTen.entrySet().forEach(a -> System.out.println(a.getKey() + ONE + "(" + a.getValue().toString() + ")"));

	}

}
