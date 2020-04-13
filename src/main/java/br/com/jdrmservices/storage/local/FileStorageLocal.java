package br.com.jdrmservices.storage.local;

import static br.com.jdrmservices.util.Constants.ERROR_SAVE_TEMP_FILE;
import static br.com.jdrmservices.util.Constants.ERROR_RECOVERY_FILE;
import static br.com.jdrmservices.util.Constants.ERROR_RECOVERY_TEMP_FILE;
import static br.com.jdrmservices.util.Constants.ERROR_MOVE_FILE_DEFINITIVE_LOCAL;
import static br.com.jdrmservices.util.Constants.CREATE_DIRECTORY_SUCCESS;
import static br.com.jdrmservices.util.Constants.DEFAULT_DIRECTORY;
import static br.com.jdrmservices.util.Constants.TEMP_DIRECTORY;
import static br.com.jdrmservices.util.Constants.ERROR_CREATE_DIRECTORY_SAVE_FILE;
import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.jdrmservices.storage.FileStorage;

public class FileStorageLocal implements FileStorage {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageLocal.class);
	
	private Path localPath;
	private Path tempPath;
	
	public FileStorageLocal() {
		this(getDefault().getPath("uploads", "files"));
	}
	
	public FileStorageLocal(Path path) {
		this.localPath = path;
		
		createDirectory();
	}

	@Override
	public String saveTempFiles(MultipartFile[] files) {
		String newName = null;
		
		if(files != null && files.length > 0) {
			MultipartFile file = files[0];
			newName = renameFile(file.getOriginalFilename());
			
			try {
				file.transferTo(new File(this.tempPath.toAbsolutePath().toString() + getDefault().getSeparator() + newName));
			} catch (Exception e) {
				throw new RuntimeException(ERROR_SAVE_TEMP_FILE, e);
			}
		}
		
		return newName;
	}
	
	public String forceFileUTF8(String file) throws UnsupportedEncodingException {
		byte[] bytes = file.getBytes("UTF-8");
		String newFileUtf8 = new String(bytes, "UTF-8");
		return newFileUtf8;
	}

	@Override
	public byte[] recoveryTempFile(String file) {
		try {
			String newFileUtf8 = forceFileUTF8(file);
			
			return Files.readAllBytes(this.tempPath.resolve(newFileUtf8));
		} catch (IOException e) {
			throw new RuntimeException(ERROR_RECOVERY_TEMP_FILE, e);
		}
	}

	@Override
	public byte[] recoveryFile(String file) {
		try {
			String newFileUtf8 = forceFileUTF8(file);
			
			return Files.readAllBytes(this.localPath.resolve(newFileUtf8));
		} catch (IOException e) {
			throw new RuntimeException(ERROR_RECOVERY_FILE, e);
		}
	}
	
	public void saveFile(String file) {
		try {
			String newFileUtf8 = forceFileUTF8(file);
			
			Files.move(this.tempPath.resolve(file), this.localPath.resolve(newFileUtf8));
		} catch (IOException e) {
			throw new RuntimeException(ERROR_MOVE_FILE_DEFINITIVE_LOCAL, e);
		}
	}
	
	public void createDirectory() {
		try {
			Files.createDirectories(this.localPath);
			this.tempPath = getDefault().getPath(this.localPath.toString(), "temp");
			Files.createDirectories(this.tempPath);
			
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug(CREATE_DIRECTORY_SUCCESS);
				LOGGER.debug(DEFAULT_DIRECTORY + this.localPath.toAbsolutePath());
				LOGGER.debug(TEMP_DIRECTORY + this.tempPath.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException(ERROR_CREATE_DIRECTORY_SAVE_FILE, e);
		}
	}
	
	public String renameFile(String originalName) {
		String newName = UUID.randomUUID().toString() + "_" + originalName;
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Original name:  %s, New name: %s", originalName, newName));
		}
		
		return newName;
	}
}
