package hello;

public class Nsentence {
    private final int id;
    private final String ncontent;
    private final String corr;

    public Nsentence(){
        this.id = 3;
        this.ncontent = "Test ncontent 3";
        this.corr = "true";
    }

    public int getId() {
        return id;
    }

    public String getNcontent() {
        return ncontent;
    }

    public String getCorr() {
        return corr;
    }
}
