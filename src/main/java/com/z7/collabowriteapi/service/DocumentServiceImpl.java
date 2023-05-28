package com.z7.collabowriteapi.service;

import com.z7.collabowriteapi.entity.Doc;
import com.z7.collabowriteapi.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DocumentServiceImpl implements DocumentService{

    @Autowired
    private DocumentRepository documentRepository;

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
}
