package br.com.jdrmservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.jdrmservices.dto.FileDTO;
import br.com.jdrmservices.storage.FileStorage;
import br.com.jdrmservices.storage.thread.FileStorageRunnable;

import static br.com.jdrmservices.util.Constants.VIEW_UPLOAD_FILE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class FilesController {
	
	@Autowired
	private FileStorage fileStorage;
	
	@GetMapping("/new")
	public ModelAndView upload() {
		ModelAndView mv = new ModelAndView(VIEW_UPLOAD_FILE);
		
		return mv;
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<FileDTO> fileDTO = new DeferredResult<>();
		
		Map<String, Object> result = new HashMap<>();
		result.put("file", fileDTO);
		result.put("message", new String("File upload success!"));
		
		Thread fileThread = new Thread(new FileStorageRunnable(files, fileDTO, fileStorage));
		fileThread.start();
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/temp/{file:.*}")
	public @ResponseBody ResponseEntity<?> recoveryTempFile(@PathVariable String file) {
		return ResponseEntity.ok(fileStorage.recoveryTempFile(file));
	}
	
	@GetMapping("/{file:.*}")
	public @ResponseBody ResponseEntity<?> recoveryile(@PathVariable String file) {
		return ResponseEntity.ok(fileStorage.recoveryFile(file));
	}
}