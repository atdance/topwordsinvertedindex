package words.invertedindex;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWithNullParameterAsInput() throws IllegalArgumentException {
		@SuppressWarnings("unused")
		final Word word = new Word(null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWithTooLongParameterAsInput() throws IllegalArgumentException {
		@SuppressWarnings("unused")
		final Word word = new Word("01234567890123456789012345678901");
	}

	@Test
	public void shouldNotThrowExceptionWithCorrectParameterAsInput() {
		@SuppressWarnings("unused")
		final Word word = new Word("012345678901234567890");

		assertTrue(word.toString().equals("012345678901234567890"));
	}

}
