package com.wipro.toonime.repos;

import com.wipro.toonime.models.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepo extends MongoRepository<Video, String>{

}