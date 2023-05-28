package com.z7.collabowriteapi.service;

import com.z7.collabowriteapi.entity.Doc;

public interface DocumentService {

    public String save(Doc document);
    public Doc getDocumentById(String id) throws Exception;

}
