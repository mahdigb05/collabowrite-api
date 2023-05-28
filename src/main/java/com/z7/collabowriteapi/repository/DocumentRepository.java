package com.z7.collabowriteapi.repository;

import com.z7.collabowriteapi.entity.Doc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<Doc, String> {
}
