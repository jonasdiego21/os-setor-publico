package br.com.jdrmservices.dto;

public class FileDTO {
	
	private String nameFile;
	private String contentType;

	public FileDTO(String nameFile, String contentType) {
		this.nameFile = nameFile;
		this.contentType = contentType;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
