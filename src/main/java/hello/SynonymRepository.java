package hello;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dictionaryRepository
// CRUD refers Create, Read, Update, Delete

public interface SynonymRepository extends CrudRepository<Synonym, Long> {

    Synonym findByWordId(Integer word_id);

}
