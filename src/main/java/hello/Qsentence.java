package hello;

public class Qsentence {
    private final int id;
    private final String qcontent;
    private final String corr;

    public Qsentence(){
        this.id = 2;
        this.qcontent = "Test qcontent 2";
        this.corr = "true";
    }

    public int getId() {
        return id;
    }

    public String getQcontent() {
        return qcontent;
    }

    public String getCorr() {
        return corr;
    }
}
