package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import hello.Dictionary;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dictionaryRepository
// CRUD refers Create, Read, Update, Delete

public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

    Dictionary findByWord(String word);

    //Tutaj się dodaje metody zgodne z konwencją
    //Dictionary findByWord (String word);

    //Dodać metodę finByWordId tylko nie w tym repository

}

