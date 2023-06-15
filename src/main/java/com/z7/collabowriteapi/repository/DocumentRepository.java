package com.z7.collabowriteapi.repository;

import com.z7.collabowriteapi.entity.Doc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends MongoRepository<Doc, String> {
    @Query(value = "{ 'id' : ?0, 'deletions.globalId' : ?1 }",
            delete = true)
    void deleteCharacterFromDocument(String documentId, String characterId);

    List<Doc> findByAccessibleUsersId(String userId);
}
