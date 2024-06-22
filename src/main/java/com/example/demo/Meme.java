package com.example.demo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "memes")
@Data
public class Meme {
	private String id;
    private String name;
    private String url;
    private String caption;
}
