package hello;

public class Asentence {
    private final int id;
    private final String acontent;
    private final String corr;

    public Asentence(){
        this.id = 0;
        this.acontent = "Test acontent 1";
        this.corr = "true";
    }

    public int getId() {
        return id;
    }

    public String getAcontent() {
        return acontent;
    }

    public String getCorr() {
        return corr;
    }
}
