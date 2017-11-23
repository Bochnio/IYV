package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


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

    private List<Word> prepareWordList (){
        Iterable <Dictionary> dictionaryList;
        List <Sentence> wordSentences;
        Synonym synonym;
        List<Word> wordList = new ArrayList<>();

        dictionaryList = dictionaryRepository.findAll();

        for (Iterator<Dictionary> i = dictionaryList.iterator(); i.hasNext();){
            Word word = new Word();
            List <Sentences> sentencesList = new ArrayList<>();
            Synon synon = new Synon();
            Dictionary dict;
            dict = i.next();
            word.setEngForm(dict.getWord());
            word.setPlForm(dict.getTranslation());

            //Zliczanie ile słowo ma zdań
            wordSentences = sentenceRepository.findAllByWordId(dict.getId());
            int sentenceCounter = wordSentences.size();

            //Petla w której tworzone są kolejne obiekty typu Sentences
            for(int k = 1; k <= sentenceCounter/3; k++){

                Asentence asentence = new Asentence();
                Qsentence qsentence = new Qsentence();
                Nsentence nsentence = new Nsentence();
                Sentence sentence;
                String aSenCorr, qSenCorr, nSenCorr;

                sentence = sentenceRepository.findByWordIdAndSentTypeAndNo(dict.getId(), "AFF" ,k);

                asentence.setId(sentence.getSentId());
                asentence.setAcontent(sentence.getSentCont());
                aSenCorr = sentence.getSentCorr();
                //Dla przeglądarek "" == !TRUE
                if(aSenCorr == null){aSenCorr = "null";}
                if(aSenCorr != null && aSenCorr.equals("false")){aSenCorr = null;}
                asentence.setCorr(aSenCorr);

                sentence = sentenceRepository.findByWordIdAndSentTypeAndNo(dict.getId(), "QUE" ,k);

                qsentence.setId(sentence.getSentId());
                qsentence.setQcontent(sentence.getSentCont());
                qSenCorr = sentence.getSentCorr();
                //Dla przeglądarek "" == !TRUE
                if(qSenCorr == null){qSenCorr = "null";}
                if(qSenCorr != null && qSenCorr.equals("false")){qSenCorr = null;}
                qsentence.setCorr(qSenCorr);

                sentence = sentenceRepository.findByWordIdAndSentTypeAndNo(dict.getId(), "NEG" ,k);

                nsentence.setId(sentence.getSentId());
                nsentence.setNcontent(sentence.getSentCont());
                nSenCorr = sentence.getSentCorr();
                //Dla przeglądarek "" == !TRUE
                if(nSenCorr == null){nSenCorr = "null";}
                if(nSenCorr != null && nSenCorr.equals("false")){nSenCorr = null;}
                nsentence.setCorr(nSenCorr);

                Sentences sentences = new Sentences(k, asentence, qsentence, nsentence);

                sentencesList.add(sentences);

            }

            word.setSentences(sentencesList);
            //Ustawianie synonimu
            synonym = synonymRepository.findByWordId(dict.getId());
            synon.setSyncontent(synonym.getSynCont());
            String synCorr;
            //Dla przeglądarek "" == !TRUE
            synCorr = synonym.getSynCorr();
            if(synCorr == null){synCorr = "null";}
            if(synCorr != null && synCorr.equals("false")){synCorr = null;}
            synon.setCorr(synCorr);
            word.setSynonyms(synon);

            //Ustawiania dat
            word.setAddDate(dict.getAddDate());
            word.setModDate(dict.getModDate());

            //Ustawianie maxID
            word.setMaxID(dict.getMaxId());

            //Dodanie słowa do listy
            wordList.add(word);
        }

        return wordList;
    }

    @GetMapping(path="/addNewWord")
    public @ResponseBody void addNewWord (HttpServletResponse response, String word, String translation, String addDate, String modDate) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        Dictionary dict = new Dictionary();
        dict.setWord(word);
        dict.setTranslation(translation);
        dict.setAddDate(addDate);
        dict.setModDate(modDate);
        dict.setMaxId(2);

        dictionaryRepository.save(dict);

        Sentence sentAff = new Sentence();
        sentAff.setWordId(dict.getId());
        sentAff.setSentCont("");
        sentAff.setSentId(0);
        sentAff.setSentType("AFF");
        sentAff.setNo(1);
        sentenceRepository.save(sentAff);

        Sentence sentQue = new Sentence();
        sentQue.setWordId(dict.getId());
        sentQue.setSentCont("");
        sentQue.setSentId(1);
        sentQue.setSentType("QUE");
        sentQue.setNo(1);
        sentenceRepository.save(sentQue);

        Sentence sentNeg = new Sentence();
        sentNeg.setWordId(dict.getId());
        sentNeg.setSentCont("");
        sentNeg.setSentId(2);
        sentNeg.setSentType("NEG");
        sentNeg.setNo(1);
        sentenceRepository.save(sentNeg);

        Synonym synonym = new Synonym();
        synonym.setWordId(dict.getId());
        synonym.setSynCont("");
        synonymRepository.save(synonym);

    }

    @GetMapping(path="/updateSentenceContent") // Map ONLY GET Requests
    public @ResponseBody void updateSentenceContent (HttpServletResponse response, String word, String sent_cont, Integer sent_id) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        Sentence sentence;
        sentence = sentenceRepository.findByWordIdAndSentId(dictionary.getId(), sent_id);
        sentence.setSentCont(sent_cont);
        sentenceRepository.save(sentence);
    }

    @GetMapping(path="/updateSentenceCorrectness")
    public @ResponseBody void updateSentenceCorrectness (HttpServletResponse response, String word, String sentCorr, Integer sentId) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        Sentence sentence;
        sentence = sentenceRepository.findByWordIdAndSentId(dictionary.getId(), sentId);
        sentence.setSentCorr(sentCorr);

        sentenceRepository.save(sentence);
    }

    @GetMapping(path="/deleteWord")
    public @ResponseBody void deleteWord (HttpServletResponse response, String word) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        List<Sentence> sentenceList;
        sentenceList = sentenceRepository.findAllByWordId(dictionary.getId());
        sentenceRepository.delete(sentenceList);

        Synonym synonym;
        synonym = synonymRepository.findByWordId(dictionary.getId());
        synonymRepository.delete(synonym);

        dictionaryRepository.delete(dictionary);

    }

    @Controller
    @RequestMapping("/sendAllWords")
    public class HelloWorldController {
        @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public @ResponseBody List<Word> sendAllWords(HttpServletResponse response) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            List<Word> wordList;

            wordList = prepareWordList();

            return wordList;
            }
        }

    @GetMapping(path="/addNewPackage")
    public @ResponseBody void addNewPackage (HttpServletResponse response, String word) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Dictionary dict;
        dict = dictionaryRepository.findByWord(word);
        List <Sentence> wordSentences;
        wordSentences = sentenceRepository.findAllByWordId(dict.getId());
        int sentenceCounter = wordSentences.size();

        Sentence sentAff = new Sentence();
        sentAff.setWordId(dict.getId());
        sentAff.setSentCont("");
        sentAff.setSentId(dict.getMaxId()+1);
        sentAff.setSentType("AFF");
        sentAff.setNo(sentenceCounter/3+1);
        sentenceRepository.save(sentAff);

        Sentence sentQue = new Sentence();
        sentQue.setWordId(dict.getId());
        sentQue.setSentCont("");
        sentQue.setSentId(dict.getMaxId()+2);
        sentQue.setSentType("QUE");
        sentQue.setNo(sentenceCounter/3+1);
        sentenceRepository.save(sentQue);

        Sentence sentNeg = new Sentence();
        sentNeg.setWordId(dict.getId());
        sentNeg.setSentCont("");
        sentNeg.setSentId(dict.getMaxId()+3);
        sentNeg.setSentType("NEG");
        sentNeg.setNo(sentenceCounter/3+1);
        sentenceRepository.save(sentNeg);

        dict.setMaxId(dict.getMaxId()+3);
        dictionaryRepository.save(dict);

    }

    @GetMapping(path="/updateTranslation")
    public @ResponseBody void deleteWord (HttpServletResponse response, String word, String translation) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        dictionary.setTranslation(translation);

        dictionaryRepository.save(dictionary);

    }

    @GetMapping(path="/updateSynonymContent")
    public @ResponseBody void updateSynonymContent (HttpServletResponse response, String word, String synCont) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        Synonym synonym;
        synonym = synonymRepository.findByWordId(dictionary.getId());
        synonym.setSynCont(synCont);
        synonymRepository.save(synonym);

    }

    @GetMapping(path="/updateSynonymCorrectness")
    public @ResponseBody void updateSynonymCorrectness (HttpServletResponse response, String word, String synCorr) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Dictionary dictionary;
        dictionary = dictionaryRepository.findByWord(word);

        Synonym synonym;
        synonym = synonymRepository.findByWordId(dictionary.getId());
        synonym.setSynCorr(synCorr);
        synonymRepository.save(synonym);

    }

    //Metoda testowa do wyszukiwania ID
    @GetMapping(path="/search")
    public @ResponseBody List<Word> search (HttpServletResponse response, String findword, Integer onlywrong, Integer onlyok, Integer notvalid, String adddate, String moddate) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        System.out.println("> findword: " + findword + " onlywrong: " + onlywrong + " onlyok: " + onlyok + " notvalid: " + notvalid + " adddate: " + adddate + " moddate: " + moddate + " <");

        List<Word> wordList;
        wordList = prepareWordList();

        for (Iterator<Word> i = wordList.iterator(); i.hasNext();) {
            Word word;
            word = i.next();

            System.out.println("Opracowuję słowo: " + word.getEngForm() + " <--");

            //Fitlracja po słowie
            if (findword != null){
                System.out.println("Wszedłem: findword != null");
                if (!Objects.equals(word.getEngForm(), findword)){
                    System.out.println("findWord -> usuwam!");
                    i.remove();
                }
            }

            //Filtracja słów, które mają choć jeden błąd
            if (onlywrong != 0){
                System.out.println("Wszedłem: onlywrong != 0 " + word.getEngForm());
                List<Sentences> sentencesList;
                sentencesList = word.getSentences();
                int falseCounter = 0;
                //Pętla po wszystkich zdaniach w ramach paczki
                for (Iterator<Sentences> s = sentencesList.iterator(); s.hasNext();) {
                    Sentences sentences = s.next();
                    System.out.println("Wszedłem: onlywrong sentencje != 0");
                    if (sentences.getAsentence().getCorr() == null || sentences.getQsentence().getCorr() == null ||  sentences.getNsentence().getCorr() == null) {
                        System.out.println("1: " + sentences.getAsentence().getCorr() + " 2: " + sentences.getQsentence().getCorr() + " 3: " + sentences.getNsentence().getCorr());
                        falseCounter++;
                    }
                }
                if (word.getSynonyms().getCorr() == null){
                    falseCounter++;
                }
                if (falseCounter==0){
                    System.out.println("onlyWrong -> usuwam!");
                    i.remove();
                }
            }

            //Filtracja słów, które mają wszystkie zdania poprawne
            if (onlyok != 0){
                System.out.println("Wszedłem: onlyok != 0 " + word.getEngForm());
                List<Sentences> sentencesList;
                sentencesList = word.getSentences();
                int falseCounter = 0;
                //Pętla po wszystkich zdaniach w ramach paczki
                for (Iterator<Sentences> s = sentencesList.iterator(); s.hasNext();) {
                    Sentences sentences = s.next();
                    System.out.println("Wszedłem: onlyok sentencje != 0");
                    if (sentences.getAsentence().getCorr() == null || Objects.equals(sentences.getAsentence().getCorr(),"null") ||  sentences.getQsentence().getCorr() == null || Objects.equals(sentences.getQsentence().getCorr(),"null") || sentences.getNsentence().getCorr() == null || Objects.equals(sentences.getNsentence().getCorr(),"null")) {
                        System.out.println("1: " + sentences.getAsentence().getCorr() + " 2: " + sentences.getQsentence().getCorr() + " 3: " + sentences.getNsentence().getCorr());
                        falseCounter++;
                    }
                }
                if (word.getSynonyms().getCorr() == null || Objects.equals(word.getSynonyms().getCorr(),"null")){
                    falseCounter++;
                }
                if (falseCounter!=0){
                    i.remove();
                }
            }

            //Filtracja słów, które mają choć jeden niesprawdzony
            if (notvalid!= 0){
                System.out.println("Wszedłem: notvalid != 0 " + word.getEngForm());
                List<Sentences> sentencesList;
                sentencesList = word.getSentences();
                int falseCounter = 0;
                //Pętla po wszystkich zdaniach w ramach paczki
                for (Iterator<Sentences> s = sentencesList.iterator(); s.hasNext();) {
                    Sentences sentences = s.next();
                    System.out.println("Wszedłem: onotvalid sentencje != 0");
                    if (Objects.equals(sentences.getAsentence().getCorr(),"null") || Objects.equals(sentences.getQsentence().getCorr(),"null") ||  Objects.equals(sentences.getNsentence().getCorr(),"null")) {
                        System.out.println("1: " + sentences.getAsentence().getCorr() + " 2: " + sentences.getQsentence().getCorr() + " 3: " + sentences.getNsentence().getCorr());
                        falseCounter++;
                    }
                }
                if (Objects.equals(word.getSynonyms().getCorr(), "null")){
                    falseCounter++;
                }
                if (falseCounter==0){
                    System.out.println("onlyWrong -> usuwam!");
                    i.remove();
                }
            }

            //Fitlracja po dacie dodania
            if (adddate != null){
                System.out.println("adddate != null");
                if (!Objects.equals(word.getAddDate(), adddate)){
                    System.out.println("adddate -> usuwam!");
                    i.remove();
                }
            }

            //Fitlracja po słowie
            if (moddate != null){
                System.out.println("Wszedłem: moddate != null");
                if (!Objects.equals(word.getModDate(), moddate)){
                    System.out.println("moddate -> usuwam!");
                    i.remove();
                }
            }

        }

        return wordList;

    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Sentence> getAllWords() {
        // This returns a JSON or XML with the users
        return sentenceRepository.findAll();
    }

    //Metoda testowa do wyszukiwania ID
    @GetMapping(path="/selectId") // Map ONLY GET Requests
    public @ResponseBody List<Word> selectWord () {

        List<Word> wordList;

        wordList = prepareWordList();

        return wordList;

    }

}