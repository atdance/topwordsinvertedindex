package words.invertedindex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThirtyWords {

	static List<Word> get(List<Word> expectedTenWords) {

		List<Word> moreThanTenWordswords = new ArrayList<>();

		/*
		 * These ten words are present 3 times
		 **/
		for (int j = 0; j < 3; j++) {
			moreThanTenWordswords.addAll(ExpectedTenWords.get(0));
		}
		/*
		 * These words are present only one time
		 **/
		List<Word> extraWords = Arrays.asList(new Word("z"), new Word("u"), new Word("v"), new Word("t"), new Word("x"),
				new Word("y"));

		moreThanTenWordswords.addAll(extraWords);

		Collections.shuffle(moreThanTenWordswords);

		return expectedTenWords;
	}

}