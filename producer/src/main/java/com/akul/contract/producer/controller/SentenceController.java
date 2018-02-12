package com.akul.contract.producer.controller;

import com.akul.contract.producer.dto.SentenceParams;
import com.akul.contract.producer.dto.SentenceResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SentenceController {

    @PostMapping(path = "/words", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SentenceResponse createWord(@RequestBody SentenceParams sentenceParams) {

        SentenceResponse response = new SentenceResponse();

        response.setId(UUID.randomUUID().toString());
        response.setSentence(sentenceParams.getSubject() + " "
                + sentenceParams.getVerb() + " "
                + sentenceParams.getArticle() + " "
                + sentenceParams.getAdjective() + " "
                + sentenceParams.getNoun());

        return response;
    }
}