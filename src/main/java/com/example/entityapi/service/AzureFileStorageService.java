package com.example.entityapi.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.azure.storage.file.share.ShareDirectoryClient;
import com.azure.storage.file.share.ShareFileClient;
import com.azure.storage.file.share.ShareFileClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AzureFileStorageService implements FileStorageService {

    @Value("${azure.storage.entity.images.share.name}")
    private String shareName;

    @Value("${azure.storage.entity.images.directory}")
    private String dirName;

    @Value("${azure.storage.account.name}")
    private String storageAccountName;

    @Value("${azure.storage.account.key}")
    private String storageAccountKey;

    private String createConnectionString() {

        return String.format(
                "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
                storageAccountName, storageAccountKey);
    }

    public Boolean uploadFile(String fileName, byte[] data) {
        try {
            ShareDirectoryClient dirClient = new ShareFileClientBuilder()
                .connectionString(createConnectionString())
                .shareName(shareName)
                .resourcePath(dirName)
                .buildDirectoryClient();

            ShareFileClient fileClient = dirClient.getFileClient(fileName);
            fileClient.create(data.length);
            var info = fileClient.upload(new ByteArrayInputStream(data), data.length);

            System.out.println(info.getETag());
            return true;
        } catch (Exception e) {
            System.out.println("uploadFile exception: " + e.getMessage());
            return false;
        }
    }

    public byte[] downloadFile(String fileName) {
        try {
            ShareDirectoryClient dirClient = new ShareFileClientBuilder()
                .connectionString(createConnectionString())
                .shareName(shareName)
                .resourcePath(dirName)
                .buildDirectoryClient();

            ShareFileClient fileClient = dirClient.getFileClient(fileName);
            var stream = new ByteArrayOutputStream();
            fileClient.download(stream);

            return stream.toByteArray();
        } catch (Exception e) {
            System.out.println("downloadFile exception: " + e.getMessage());
            throw e;
        }
    }
}