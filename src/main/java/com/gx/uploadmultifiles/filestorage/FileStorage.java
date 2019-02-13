package com.gx.uploadmultifiles.filestorage;

import java.nio.file.Path;
import java.util.stream.Stream;
 
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存储服务
 */
public interface FileStorage {
	/**
	 * 把文件上传到指定位置
	 * @param file
	 */
	void store(MultipartFile file);

	/**
	 * 加载文件资源
	 * @param filename
	 * @return
	 */
	Resource loadFile(String filename);

	/**
	 * 删除目录及目录下所有的文件
	 */
	void deleteAll();

	/**
	 * 初始化存储空间
	 */
	void init();

	/**
	 *	获取所有文件的信息
	 * @return
	 */
	Stream<Path> loadFiles();
}
