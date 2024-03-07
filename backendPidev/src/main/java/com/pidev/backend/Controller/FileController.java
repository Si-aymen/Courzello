package com.pidev.backend.Controller;

import com.pidev.backend.Entity.FileDB;
import com.pidev.backend.Repository.ReponseRepository;
import com.pidev.backend.Service.FileStorageService;
import com.pidev.backend.message.ResponseFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@Slf4j
@RestController
@RequestMapping("/File")
public class FileController {
  @Autowired
  private FileStorageService storageService;
  @Autowired
  ReponseRepository reprepo;

  @PostMapping("/uploadf")
  public List<String> uploadFilef(@RequestPart("file") List<MultipartFile> file) throws IOException {
  
      return storageService.store(file);
     
      
   
  }
  	@DeleteMapping("/delete-file/{id-file}")
	@ResponseBody
	public void deletefile( @PathVariable("id-file") String idfile){
		storageService.deletefile(idfile);
  	}
  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/pi/File/files/")
          .path(dbFile.getId().toString())
          .toUriString();
      return new ResponseFile(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(files);
    
  }

  @GetMapping("/file/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    FileDB fileDB = storageService.getFile(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }

  @GetMapping("/filesdetail/{id}")
  public FileDB getFiledetail(@PathVariable String id) {
    return storageService.getFile(id);
    
   
    
  }
    @GetMapping("/filesbyreponse/{id}")
    public List<FileDB> getFilebyreponse(@PathVariable String id) {
        return storageService.getFileByReponse(id);



    }

  @PutMapping("/affecter-fileToReponse/{id-reponse}/{files}")
	@ResponseBody
	public void affecterFilesToarticle(@PathVariable("files") List<String> idfile, @PathVariable("id-reponse") String idreponse) {
	  storageService.affecterFileToReponse(idfile, idreponse);

	}


  
}