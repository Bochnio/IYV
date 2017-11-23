package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="/stats")
public class StatisticsController {

    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private SentenceRepository sentenceRepository;

    @GetMapping(path="/getWordsNumber") // Map ONLY GET Requests
    public @ResponseBody Long getWordsNumber (HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Long wordNumber;
        wordNumber = dictionaryRepository.count();

        return wordNumber;
    }

    @GetMapping(path="/getSentencesNumber") // Map ONLY GET Requests
    public @ResponseBody Long getSentencesNumber (HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Long sentencesNumber;
        sentencesNumber = sentenceRepository.count();

        return sentencesNumber;
    }
}
