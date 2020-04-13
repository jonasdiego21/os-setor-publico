package br.com.jdrmservices.storage.thread;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.jdrmservices.dto.FileDTO;
import br.com.jdrmservices.storage.FileStorage;

public class FileStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<FileDTO> result;
	private FileStorage fileStorage;

	public FileStorageRunnable(MultipartFile[] files, DeferredResult<FileDTO> result, FileStorage fileStorage) {
		this.files = files;
		this.result = result;
		this.fileStorage = fileStorage;
	}
	
	@Override
	public void run() {
		String nameFile = this.fileStorage.saveTempFiles(files);
		String contentType = files[0].getContentType();
		
		result.setResult(new FileDTO(nameFile, contentType));
	}
}
