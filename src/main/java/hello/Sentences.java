package hello;

public class Sentences {

    private Integer no;
    private Asentence asentence;
    private Qsentence qsentence;
    private Nsentence nsentence;


    public Sentences(Integer no, Asentence asentence, Qsentence qsentence, Nsentence nsentence) {
        this.no = no;
        this.asentence = asentence;
        this.qsentence = qsentence;
        this.nsentence = nsentence;
    }

    public Asentence getAsentence() {
        return asentence;
    }

    public void setAsentence(Asentence asentence) {
        this.asentence = asentence;
    }

    public Qsentence getQsentence() {
        return qsentence;
    }

    public void setQsentence(Qsentence qsentence) {
        this.qsentence = qsentence;
    }

    public Nsentence getNsentence() {
        return nsentence;
    }

    public void setNsentence(Nsentence nsentence) {
        this.nsentence = nsentence;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}

