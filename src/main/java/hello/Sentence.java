package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Sentence {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer wordId;
    private String sentCont;
    private Integer sentId;
    private String sentType;
    private String sentCorr;
    private Integer no;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer word_id) {
        this.wordId = word_id;
    }

    public String getSentCont() {
        return sentCont;
    }

    public void setSentCont(String sent_cont) {
        this.sentCont = sent_cont;
    }

    public Integer getSentId() {
        return sentId;
    }

    public void setSentId(Integer sent_id) {
        this.sentId = sent_id;
    }

    public String getSentType() {
        return sentType;
    }

    public void setSentType(String sentType) {
        this.sentType = sentType;
    }

    public String getSentCorr() {
        return sentCorr;
    }

    public void setSentCorr(String sentCorr) {
        this.sentCorr = sentCorr;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}
