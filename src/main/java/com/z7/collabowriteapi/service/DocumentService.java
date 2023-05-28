package com.z7.collabowriteapi.service;

import com.z7.collabowriteapi.entity.Character;
import com.z7.collabowriteapi.entity.Doc;

import java.util.List;

public interface DocumentService {

    public String save(Doc document);
    public Doc getDocumentById(String id) throws Exception;

    public void deleteCharacterFromDocument(String documentId, String characterId);
    public void addCharacterToDocument(String documentId, Character character);

    public List<Doc> getDocumentsAccessibleByUser(String userId) throws Exception;

}
