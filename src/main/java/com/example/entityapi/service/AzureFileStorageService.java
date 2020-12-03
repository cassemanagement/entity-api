package com.example.entityapi.service;

import com.azure.storage.file.share.ShareDirectoryClient;
import com.azure.storage.file.share.ShareFileClient;
import com.azure.storage.file.share.ShareFileClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class AzureFileStorageService implements FileStorageService
{

    private static final Logger logger = LogManager.getLogger(AzureFileStorageService.class);

    @Value("${azure.storage.entity.images.share.name}")
    private String shareName;

    @Value("${azure.storage.entity.images.directory}")
    private String dirName;

    @Value("${azure.storage.account.name}")
    private String storageAccountName;

    @Value("${azure.storage.account.key}")
    private String storageAccountKey;

    private String createConnectionString()
    {

        return String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
                             storageAccountName,
                             storageAccountKey
                            );
    }

    public void uploadFile(String fileName, byte[] data)
    {

        logger.debug(String.format("Upload file %s.",
                                   fileName
                                  ));

        ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(createConnectionString())
                                                                     .shareName(shareName)
                                                                     .resourcePath(dirName)
                                                                     .buildDirectoryClient();

        ShareFileClient fileClient = dirClient.getFileClient(fileName);
        fileClient.create(data.length);
        fileClient.upload(new ByteArrayInputStream(data),
                          data.length
                         );

        logger.debug(String.format("Upload file %s complete.",
                                   fileName
                                  ));
    }

    public byte[] downloadFile(String fileName)
    {

        logger.debug(String.format("Download file %s.",
                                   fileName
                                  ));

        ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(createConnectionString())
                                                                     .shareName(shareName)
                                                                     .resourcePath(dirName)
                                                                     .buildDirectoryClient();

        ShareFileClient fileClient = dirClient.getFileClient(fileName);
        var stream = new ByteArrayOutputStream();
        fileClient.download(stream);

        logger.debug(String.format("Download file %s complete.",
                                   fileName
                                  ));

        return stream.toByteArray();
    }
}