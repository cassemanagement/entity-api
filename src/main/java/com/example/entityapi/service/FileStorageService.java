package com.example.entityapi.service;

public interface FileStorageService {

    Boolean uploadFile(String fileName, byte[] data);

    byte[] downloadFile(String fileName);
}
