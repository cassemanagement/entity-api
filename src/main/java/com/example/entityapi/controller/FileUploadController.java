package com.example.entityapi.controller;

import com.example.entityapi.service.FileStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;

@RestController
public class FileUploadController
{

	private final static Logger logger = LogManager.getLogger(FileUploadController.class);

	private final FileStorageService storageService;

	@Autowired
	public FileUploadController(FileStorageService storageService)
	{
		this.storageService = storageService;
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<byte[]> serveFile(@PathVariable String filename)
	{

		logger.debug(String.format("Serving %s.",
								   filename
								  ));

		var data = storageService.downloadFile(filename);
		// Set headers
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(filename)));

		var entity = new ResponseEntity<byte[]>(data,
												headers,
												HttpStatus.OK
		);

		logger.debug(String.format("Serving %s complete.",
								   filename
								  ));

		return entity;
	}

	@PostMapping("/files/")
	public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) throws IOException
	{

		logger.debug(String.format("Upload %s.",
								   file
								  ));

		storageService.uploadFile(file.getOriginalFilename(),
								  file.getBytes()
								 );
		var entity = new ResponseEntity<Void>(HttpStatus.CREATED);

		logger.debug(String.format("Upload %s complete.",
								   file
								  ));

		return entity;
	}

}