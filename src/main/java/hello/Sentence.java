package hello;

public class Sentence {

    private Integer id;
    private Integer word_id;
    private String sent_cont;
    private Integer sent_id;
    private String sent_type;
    private String sent_corr;

    public Sentence(Integer id, Integer word_id, String sent_cont, Integer sent_id, String sent_type, String sent_corr) {
        this.id = id;
        this.word_id = word_id;
        this.sent_cont = sent_cont;
        this.sent_id = sent_id;
        this.sent_type = sent_type;
        this.sent_corr = sent_corr;
    }

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

    public String getSent_cont() {
        return sent_cont;
    }

    public void setSent_cont(String sent_cont) {
        this.sent_cont = sent_cont;
    }

    public Integer getSent_id() {
        return sent_id;
    }

    public void setSent_id(Integer sent_id) {
        this.sent_id = sent_id;
    }

    public String getSent_type() {
        return sent_type;
    }

    public void setSent_type(String sent_type) {
        this.sent_type = sent_type;
    }

    public String getSent_corr() {
        return sent_corr;
    }

    public void setSent_corr(String sent_corr) {
        this.sent_corr = sent_corr;
    }
}
