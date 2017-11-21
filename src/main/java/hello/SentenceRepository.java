package hello;

import org.springframework.data.repository.CrudRepository;

import hello.Sentence;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dictionaryRepository
// CRUD refers Create, Read, Update, Delete

public interface SentenceRepository extends CrudRepository<Sentence, Long> {

    Sentence findByWordIdAndSentId(Integer word_id, Integer sent_id);
    Sentence findByWordIdAndSentTypeAndNo(Integer wordId, String sentTyp, Integer no);
    List<Sentence> findAllByWordId (Integer word_id);

}
