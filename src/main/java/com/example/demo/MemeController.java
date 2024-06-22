package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memes")
public class MemeController {

    private final MemeService memeService;

    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createMeme(@RequestBody Meme meme) {
    	if (meme == null) {
    		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    	}
    	
    	if (meme.getName() == null || meme.getName().isEmpty()) {
    		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    	}
    	
    	if (meme.getUrl() == null || meme.getUrl().isEmpty()) {
    		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    	}
    	else if (memeService.findUrl(meme.getUrl()) || memeService.findName(meme.getName()) ) {
    		return ResponseEntity.status(409).body("{\"error\": \"Duplicate\"}");
    	}
    	
    	if (meme.getCaption() == null || meme.getCaption().isEmpty()) {
    		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    	}
    	
        Meme savedMeme = memeService.saveMeme(meme);
        return ResponseEntity.ok().body("{\"id\": \"" + savedMeme.getId() + "\"}");
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Meme>> getLatestMemes() {
        List<Meme> memes = memeService.getLatestMemes();
        return ResponseEntity.ok(memes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemeById(@PathVariable String id) {
        Optional<Meme> meme = memeService.getMemeById(id);
        if (meme.isPresent()) {
            return ResponseEntity.ok(meme.get());
        } else {
            return ResponseEntity.status(404).body("{\"error\": \"Meme not found\"}");
        }
    }
}