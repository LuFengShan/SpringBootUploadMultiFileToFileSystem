package com.gx.uploadmultifiles.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.gx.uploadmultifiles.filestorage.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.gx.uploadmultifiles.model.FileInfo;

@Controller
public class DownloadFileController {

	@Autowired
	FileStorage fileStorage;

	/*
	 * 浏览所有文件信息
	 */
	@GetMapping("/files")
	public String getListFiles(Model model) {
		List<FileInfo> fileInfos = fileStorage.loadFiles()
				.map(path -> {
					String filename = path.getFileName().toString();
					String url = MvcUriComponentsBuilder.fromMethodName(DownloadFileController.class,
							"downloadFile", path.getFileName().toString()).build().toString();
					return new FileInfo(filename, url);
				})
				.collect(Collectors.toList());

		model.addAttribute("files", fileInfos);
		return "listfiles";
	}

	/*
	 * 下载文件
	 */
	@GetMapping("/files/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		Resource file = fileStorage.loadFile(filename); // 获取文件的资源
		// 下载到指定文件
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}