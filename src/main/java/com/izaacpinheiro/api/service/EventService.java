package com.izaacpinheiro.api.service;

import com.izaacpinheiro.api.domain.event.Event;
import com.izaacpinheiro.api.domain.event.EventRequestDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventService {

//    @Value("${aws.bucket.name}")
//    private String bucketName;
//
//    @Autowired
//    private AmazonS3 s3Client;

    public Event createEvent(EventRequestDTO data) {
//        String imgUrl = null;
//
//        if (data.image() != null){
//            imgUrl = this.uploadImage(data.image());
//        }

        // Crição do novo evento com as informações passadas pelo usuário
        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date())); // Conversão do time stamp do front para um Date
//        newEvent.setImgUrl(imgUrl);

        return newEvent;
    }
}
