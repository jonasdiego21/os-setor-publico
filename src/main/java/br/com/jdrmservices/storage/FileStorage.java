package br.com.jdrmservices.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
	public String saveTempFiles(MultipartFile[] files);
	public byte[] recoveryTempFile(String file);
	public byte[] recoveryFile(String file);
}
