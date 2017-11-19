package hello;

public class Sentences {

    private final Asentence asentence;
    private final Qsentence qsentence;
    private final Nsentence nsentence;

    public Sentences(){
        this.asentence = new Asentence();
        this.qsentence = new Qsentence();
        this.nsentence = new Nsentence();
    }

    public Asentence getAsentence() {
        return asentence;
    }

    public Qsentence getQsentence() {
        return qsentence;
    }

    public Nsentence getNsentence() {
        return nsentence;
    }

}

