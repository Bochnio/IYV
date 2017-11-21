package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Synonym {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer wordId;
    private String synCont;
    private String synCorr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getSynCont() {
        return synCont;
    }

    public void setSynCont(String syn_cont) {
        this.synCont = syn_cont;
    }

    public String getSynCorr() {
        return synCorr;
    }

    public void setSynCorr(String syn_corr) {
        this.synCorr = syn_corr;
    }
}
