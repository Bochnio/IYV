package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Synonym {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer id;
    private Integer word_id;
    private String syn_cont;
    private String syn_corr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWord_id() {
        return word_id;
    }

    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
    }

    public String getSyn_cont() {
        return syn_cont;
    }

    public void setSyn_cont(String syn_cont) {
        this.syn_cont = syn_cont;
    }

    public String getSyn_corr() {
        return syn_corr;
    }

    public void setSyn_corr(String syn_corr) {
        this.syn_corr = syn_corr;
    }
}
