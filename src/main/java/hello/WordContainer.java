package hello;

public class WordContainer {

    private final Word[] Words;

    public WordContainer() {
        this.Words = new Word[1];
        this.Words[0] = new Word();
    }

    public Word[] GetWords() {
        return Words;
    }
}
