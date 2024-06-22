package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MemeService {
	private final MemeRepository memeRepository;
	
	public MemeService(MemeRepository memeRepository) {
		this.memeRepository = memeRepository;
	}
	
	public Meme saveMeme(Meme meme) {
		return memeRepository.save(meme);
	}
	
	public List<Meme> getLatestMemes() {
        return memeRepository.findAll(PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }
	
	public Optional<Meme> getMemeById(String id) {
        return memeRepository.findById(id);
    }
	
	public boolean findUrl(String url) {
		Optional<Meme> meme = memeRepository.findByUrl(url);
		if (meme.isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean findName(String name) {
		Optional<Meme> meme = memeRepository.findByName(name);
		if (meme.isPresent()) {
			return true;
		}
		return false;
	}
}
