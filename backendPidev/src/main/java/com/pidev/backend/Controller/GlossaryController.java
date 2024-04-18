package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Glossary;
import com.pidev.backend.Service.GlossaryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )

@RestController
@AllArgsConstructor
@RequestMapping("/glossary")
public class GlossaryController {
    @Autowired
    private GlossaryService glossaryService;

    @GetMapping("/get_all")
    public List<Glossary> getAllTermes() {
        return glossaryService.getAllTermes();
    }
    @PostMapping("/add")
    public Glossary addGlossary(@RequestBody Glossary glossary) {
        return glossaryService.addGlossary(glossary);
    }

    @PutMapping("/update/{id}")
    public Glossary updateGlossary(@PathVariable String id, @RequestBody Glossary glossary) {
        return glossaryService.updateGlossary(id, glossary);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGlossary(@PathVariable String id) {
        glossaryService.deleteGlossary(id);
        return ResponseEntity.ok().build();
    }
   /* @GetMapping("/recherche-temps-reel")
    public Flux<Glossary> rechercheEnTempsReel(@RequestParam(name = "term") String term) {
        return glossaryService.rechercheEnTempsReel(term);
    }*/
}
