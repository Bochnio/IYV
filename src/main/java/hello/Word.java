package hello;
import java.lang.*;

public class Word {

    private final String engForm;
    private final String plForm;
    private final Sentences[] sentences;
    private final Synonym synonyms;
    private final String addDate;
    private final String modDate;
    private final String maxID;

    public Word(String word) {
        this.engForm = word;
        this.plForm = "miły, uprzejmy, sympatyczny";
        this.sentences = new Sentences[1];
        this.sentences[0] = new Sentences();
        this.synonyms = new Synonym();
        this.addDate = "2017-11-01";
        this.modDate = "2017-11-03";
        this.maxID = "2";
    }

    public String getEngForm() {
        return engForm;
    }

    public String getPlForm() {
        return plForm;
    }

    public Synonym getSynonyms() {
        return synonyms;
    }

    public String getAddDate() {
        return addDate;
    }

    public String getModDate() {
        return modDate;
    }

    public String getMaxId() {
        return maxID;
    }

    public Sentences[] getSentences() {
        return sentences;
    }

}