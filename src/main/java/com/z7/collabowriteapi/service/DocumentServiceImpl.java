package com.z7.collabowriteapi.service;

import com.z7.collabowriteapi.entity.Character;
import com.z7.collabowriteapi.entity.Doc;
import com.z7.collabowriteapi.entity.User;
import com.z7.collabowriteapi.repository.DocumentRepository;
import com.z7.collabowriteapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DocumentServiceImpl implements DocumentService{

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String save(Doc document) {
        return documentRepository.save(document).getId();
    }

    @Override
    public Doc getDocumentById(String id) throws Exception {

        Optional<Doc> document = documentRepository.findById(id);
        if(document.isPresent()) return document.get();
        throw new Exception("document not found");
    }

    @Override
    public void deleteCharacterFromDocument(String documentId, String characterId) {
        documentRepository.deleteCharacterFromDocument(documentId, characterId);
    }

    @Override
    public void addCharacterToDocument(String documentId, Character character) {
        Query query = new Query(Criteria.where("id").is(documentId));
        Update update = new Update().push("insertions", character);
        mongoTemplate.updateFirst(query, update, Doc.class);
    }

    @Override
    public List<Doc> getDocumentsAccessibleByUser(String userId) throws Exception {
        User user = userService.findUserById(userId);
        return documentRepository.findByAccessibleUsersId(user.getId());
    }
}
