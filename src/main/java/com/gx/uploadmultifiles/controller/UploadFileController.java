package com.gx.uploadmultifiles.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gx.uploadmultifiles.filestorage.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件转发层
 */
@Controller
public class UploadFileController {
	
	@Autowired
	FileStorage fileStorage;
	
    @GetMapping("/")
    public String index() {
        return "uploadform";
    }
    
    @PostMapping("/")
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model model) {
    	List<String> fileNames = null;
    	
		try {
	        fileNames = Arrays.asList(files)
	                .stream()
	                .map(file -> {
	                	fileStorage.store(file); // 把文件拷贝到指定位置
	                	return file.getOriginalFilename(); // 返回文件的名字
	                })
	                .collect(Collectors.toList());
			
			model.addAttribute("message", "文件上传成功!");
			model.addAttribute("files", fileNames);
		} catch (Exception e) {
			model.addAttribute("message", "Fail!");
			model.addAttribute("files", fileNames);
		}
		
        return "uploadform";
    }
    
}