package com.gx.uploadmultifiles;

import javax.annotation.Resource;

import com.gx.uploadmultifiles.filestorage.FileStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootUploadMultiFileToFileSystemApplication implements CommandLineRunner {

	@Resource
	FileStorage fileStorage;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUploadMultiFileToFileSystemApplication.class, args);
	}

	@Override
	public void run(String... args) {
		fileStorage.deleteAll();
		fileStorage.init();
	}
}
