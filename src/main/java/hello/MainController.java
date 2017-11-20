package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Console;
import java.util.List;

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
        sentAff.setSentType("AFF");

        sentenceRepository.save(sentAff);

        Sentence sentQue = new Sentence();
        sentQue.setWordId(dict.getId());
        sentQue.setSentCont("");
        sentQue.setSentId(1);
        sentQue.setSentType("QUE");
        sentenceRepository.save(sentQue);

        Sentence sentNeg = new Sentence();
        sentNeg.setWordId(dict.getId());
        sentNeg.setSentCont("");
        sentNeg.setSentId(2);
        sentNeg.setSentType("NEG");
        sentenceRepository.save(sentNeg);

        Synonym synonym = new Synonym();
        synonym.setWordId(dict.getId());
        synonym.setSynCont("");
        synonymRepository.save(synonym);

    }

    //Metoda testowa do wyszukiwania ID
    @GetMapping(path="/selectId") // Map ONLY GET Requests
    public @ResponseBody String selectWord () {

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

    @GetMapping(path="/updateSentenceCorrectness")
    public @ResponseBody void updateSentenceCorrectness (@RequestParam String word, @RequestParam String sentCorr, @RequestParam Integer sentId) {

        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        Sentence sentence;
        sentence = sentenceRepository.findByWordIdAndSentId(dictionary.getId(), sentId);
        sentence.setSentCorr(sentCorr);

        System.out.println("sentCorr zdania ->" + sentence.getSentCorr() + "<- ID zdania: ->" + sentence.getId() + "<-");

        sentenceRepository.save(sentence);
    }

    @GetMapping(path="/deleteWord")
    public @ResponseBody void deleteWord (@RequestParam String word) {

        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        List<Sentence> sentence;
        sentence = sentenceRepository.findAllByWordId(dictionary.getId());
        sentenceRepository.delete(sentence);

        Synonym synonym;
        synonym = synonymRepository.findByWordId(dictionary.getId());
        synonymRepository.delete(synonym);

        dictionaryRepository.delete(dictionary);

    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Sentence> getAllWords() {
        // This returns a JSON or XML with the users
        return sentenceRepository.findAll();
    }
}