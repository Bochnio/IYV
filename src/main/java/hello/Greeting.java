package hello;
import java.lang.*;

public class Greeting {

    private final String engForm;
    private final String plForm;
    private final String sentences;
    private final String synonyms;
    private final String addDate;
    private final String modDate;
    private final String maxID;

    public Greeting() {
        this.engForm = "pleasant";
        this.plForm = "miły, uprzejmy, sympatyczny";
        this.sentences = "asentence:{id: 0, acontent: 'Affirmative sentece 2.1!', corr: ''}," +
                "qsentence: {id: 1, qcontent: 'Question sentece 2.1!', corr: ''}," +
                "nsentence: {id: 2, ncontent: 'Negative sentece 2.1!', corr: ''}";
        this.synonyms = "{syncontent: 'Example of synonym 2!', corr: true}";
        this.addDate = "2017-11-01";
        this.modDate = "data_2";
        this.maxID = "2017-11-07";
    }

    public String getEngForm() {
        return engForm;
    }

    public String getPlForm() {
        return plForm;
    }

    public String getSentences() {
        return sentences;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public String getAddDate() {
        return addDate;
    }

    public String getModDate() {
        return modDate;
    }

    public String getMaxId() {
        return maxID;
    }

}