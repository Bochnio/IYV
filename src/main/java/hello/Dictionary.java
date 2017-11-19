package hello;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Dictionary {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String word;
    private String translation;
    private Date addDate;
    private Date modDate;
    private Integer max_id;

    public Dictionary(String word, String translation, Date addDate, Date modDate, Integer max_id) {
        this.word = word;
        this.translation = translation;
        this.addDate = addDate;
        this.modDate = modDate;
        this.max_id = max_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Integer getMax_id() {
        return max_id;
    }

    public void setMax_id(Integer max_id) {
        this.max_id = max_id;
    }
}
