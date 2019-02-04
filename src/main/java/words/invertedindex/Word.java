package words.invertedindex;

/**
 * represents a string , constraint are applied on its constructor.
 *
 */
public class Word implements Comparable<Word> {

	final private String word;

	public Word(String pWord) {
		if (pWord == null) {
			throw new IllegalArgumentException("null as input parameter to constrctor of Word class");
		}
		if (pWord.length() > 30) {
			throw new IllegalArgumentException(
					"too large input parameter to constrctor of Word class, max 30 characters string is allowed");
		}

		word = pWord;
	}

	@Override
	public String toString() {
		return word;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Word other = (Word) obj;
		if (word == null) {
			if (other.word != null) {
				return false;
			}
		} else if (!word.equals(other.word)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Word arg0) {
		return this.word.compareTo(arg0.word);
	}

}
