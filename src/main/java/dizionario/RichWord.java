package dizionario;

public class RichWord {

	private String word;
	private boolean correct;
	
	public RichWord(String word) {
		this.word = word;
		this.correct = false;
	}

	public String getWord() {
		return word;
	}

	public boolean isCorrect() {
		return correct;
	}
	
	public void setCorrect() {
		this.correct = true;
	}
}
