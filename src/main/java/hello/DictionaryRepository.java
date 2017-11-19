package hello;

import org.springframework.data.repository.CrudRepository;

import hello.Dictionary;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dictionaryRepository
// CRUD refers Create, Read, Update, Delete

public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

}
