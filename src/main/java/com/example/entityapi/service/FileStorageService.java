package com.example.entityapi.service;

public interface FileStorageService {

    void uploadFile(String fileName, byte[] data);

    byte[] downloadFile(String fileName);
}
