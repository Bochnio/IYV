package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/hello-world")
public class HelloWorldController {
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Word> sayHello(HttpServletResponse response) {
        //TO-DO: tutaj powinna być tylko metoda, która zwraca listę obiektów word
        //Parametrami
        response.setHeader("Access-Control-Allow-Origin", "*");

        Word greeting1 = new Word();
        Word greeting2 = new Word();
        List<Word> list = new ArrayList<>();
        list.add(greeting1);
        list.add(greeting2);
        return list;
    }
}