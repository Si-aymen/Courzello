package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.FileDB;
import com.pidev.backend.Entity.Reponse;
import com.pidev.backend.Repository.FileDBRepository;
import com.pidev.backend.Repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Service
public class FileStorageService {
	Long idf;
  @Autowired
  private FileDBRepository fileDBRepo;

  @Autowired
  ReponseRepository reponserepo;

	public List<String> store(List<MultipartFile> files) throws IOException {
		List<String> ids = new ArrayList<>();
		for (MultipartFile file : files) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			FileDB fileEntity = new FileDB(fileName, file.getContentType(), file.getBytes());
			FileDB savedFile = fileDBRepo.save(fileEntity);
			ids.add(savedFile.getId());
		}
		return ids;
	}

  public void deletefile(String idfile) {

	  fileDBRepo.deleteById(idfile);
  }
  public FileDB getFile(String id) {
    return fileDBRepo.findById(id).orElse(null);
  }
  
  //renvoi sous forme de strem
  public Stream<FileDB> getAllFiles() {
    return fileDBRepo.findAll().stream();
  }

  public List<FileDB> getFileByReponse(String id) {
	  Reponse t =reponserepo.findById(id).orElse(null);
	    return t.getFiles();
	  }
  public void affecterFileToReponse(List<String> idFiles, String idReponse) {
		Reponse t=reponserepo.findById(idReponse).orElse(null);

		for(String s: idFiles){
			FileDB f=fileDBRepo.findById(s).orElse(null);
			if(t.getFiles()==null){//si la liste des f associe a la reponse est vide
				List<FileDB> lfs=new ArrayList<FileDB>(); //cree une nouvelle liste de f
				lfs.add(f);
				t.setFiles(lfs);
				reponserepo.save(t);
			}else{
				t.getFiles().add(f);
				reponserepo.save(t);
			}


		}


	}


 
}