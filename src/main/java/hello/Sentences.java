package hello;

public class Sentences {

    private final int id;
    private final String content;
    private final String corr;

    public Sentences(int id){
        this.id = id;
        this.content = "Test";
        this.corr = "true";
    }

    public String getContent() {
        return content;
    }

    public String getCorr() {
        return corr;
    }

}

