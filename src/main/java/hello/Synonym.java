package hello;

public class Synonym {

    private final String corr;
    private final String syncontent;

    public Synonym(){
        this.corr = "true";
        this.syncontent = "Treść testowa";
    }

    public String getContent() {
        return syncontent;
    }

    public String getCorr() {
        return corr;
    }

}
