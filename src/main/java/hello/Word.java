package hello;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class Word {

    private String engForm;
    private String plForm;
    private List<Sentences> sentences;
    private Synon synonyms;
    private String addDate;
    private String modDate;
    private Integer maxID;

//    public Word(String word) {
//        this.engForm = "word";
//        this.plForm = "miły, uprzejmy, sympatyczny";
//        this.sentences = new ArrayList<>();
//        sentences.add(new Sentence());
//        this.synonyms = new Synon();
//        this.addDate = "2017-11-01";
//        this.modDate = "2017-11-03";
//        this.maxID = "2";
//    }

    public String getEngForm() {
        return engForm;
    }

    public String getPlForm() {
        return plForm;
    }

    public Synon getSynonyms() {
        return synonyms;
    }

    public String getAddDate() {
        return addDate;
    }

    public String getModDate() {
        return modDate;
    }

    public Integer getMaxId() {
        return maxID;
    }

    public List<Sentences> getSentences() {
        return sentences;
    }

    public void setEngForm(String engForm) {
        this.engForm = engForm;
    }

    public void setPlForm(String plForm) {
        this.plForm = plForm;
    }

    public void setSentences(List<Sentences> sentences) {
        this.sentences = sentences;
    }

    public void setSynonyms(Synon synonyms) {
        this.synonyms = synonyms;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public void setMaxID(Integer maxID) {
        this.maxID = maxID;
    }
}