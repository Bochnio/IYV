package hello;

import org.springframework.data.repository.CrudRepository;

import hello.Sentence;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dictionaryRepository
// CRUD refers Create, Read, Update, Delete

public interface SentenceRepository extends CrudRepository<Sentence, Long> {

}
