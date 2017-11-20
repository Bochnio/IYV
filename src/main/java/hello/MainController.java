package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Console;

import hello.Dictionary;
import hello.DictionaryRepository;
import hello.Sentence;
import hello.SentenceRepository;



@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private SentenceRepository sentenceRepository;
    @Autowired
    private SynonymRepository synonymRepository;


    @GetMapping(path="/addNewWord") // Map ONLY GET Requests
    public @ResponseBody void addNewWord (@RequestParam String word, @RequestParam String translation, @RequestParam String addDate, @RequestParam String modDate) {

        Dictionary dict = new Dictionary();
        dict.setWord(word);
        dict.setTranslation(translation);
        dict.setAddDate(addDate);
        dict.setModDate(modDate);
        dict.setMax_id(2);

        dictionaryRepository.save(dict);

        Sentence sentAff = new Sentence();
        sentAff.setWordId(dict.getId());
        sentAff.setSentCont("");
        sentAff.setSentId(0);
        sentAff.setSent_type("AFF");

        sentenceRepository.save(sentAff);

        Sentence sentQue = new Sentence();
        sentQue.setWordId(dict.getId());
        sentQue.setSentCont("");
        sentQue.setSentId(1);
        sentQue.setSent_type("QUE");
        sentenceRepository.save(sentQue);

        Sentence sentNeg = new Sentence();
        sentNeg.setWordId(dict.getId());
        sentNeg.setSentCont("");
        sentNeg.setSentId(2);
        sentNeg.setSent_type("NEG");
        sentenceRepository.save(sentNeg);

        Synonym synonym = new Synonym();
        synonym.setWord_id(dict.getId());
        synonym.setSyn_cont("");
        synonymRepository.save(synonym);

        //Kod do listowania
        //For po findAll Dictionaryi i budować po kolei obiekty word

        //dictionaryRepository.findOne(123);

        //Update robi się przez save
        // dictionaryRepository.findOne()

        // Select id... -> findByWord() -> spring data

        // Utworzenie obiektu z kilku tabelek

        // Notatki: Spring data

        //JPA/Hibernate one-to-many relationship

        //Utworzyć sentenceRepository

    }

    @GetMapping(path="/selectId") // Map ONLY GET Requests
    public @ResponseBody String selectWord () {

        //Dictionary dictionary = dictionaryRepository.findByWord(word);
        //int id = dict.findByWord(String word);
        //int id = dict.findByWord;

        Dictionary result;
        result = dictionaryRepository.findByWord("pleasant");
        result.setWord("Fajny");
        dictionaryRepository.save(result);
        return result.getTranslation();

    }

    @GetMapping(path="/updateSentenceContent") // Map ONLY GET Requests
    public @ResponseBody void updateSentenceContent (@RequestParam String word, @RequestParam String sent_cont, @RequestParam Integer sent_id) {

        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        Sentence sentence;
        sentence = sentenceRepository.findByWordIdAndSentId(dictionary.getId(), sent_id);
        sentence.setSentCont(sent_cont);
        sentenceRepository.save(sentence);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Sentence> getAllWords() {
        // This returns a JSON or XML with the users
        return sentenceRepository.findAll();
    }
}