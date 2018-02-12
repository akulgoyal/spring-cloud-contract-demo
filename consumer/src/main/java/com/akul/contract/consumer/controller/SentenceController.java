package com.akul.contract.consumer.controller;

import com.akul.contract.consumer.dto.SentenceParams;
import com.akul.contract.consumer.dto.SentenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SentenceController {

    @Value("${words.subjects}")
    private String subjects;

    @Value("${words.verbs}")
    private String verbs;

    @Value("${words.articles}")
    private String articles;

    @Value("${words.adjectives}")
    private String adjectives;

    @Value("${words.nouns}")
    private String nouns;

    private RestTemplate restTemplate;

    @Autowired
    public SentenceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/sentences")
    public String getSentences(@RequestParam("number") Integer number) {

        StringBuilder sentences = new StringBuilder(number);

        for(int index=0; index<number; index++) {
            sentences.append(getSentence());
            sentences.append("<br><br>");
        }

        return sentences.toString();
    }

    private String getSentence() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        HttpEntity<SentenceParams> request = new HttpEntity<>(getSentenceRequest(), httpHeaders);

        ResponseEntity<SentenceResponse> responseEntity = restTemplate.exchange(
                "http://localhost:8020/words",
                HttpMethod.POST,
                request, SentenceResponse.class);

        return (responseEntity.getBody().getSentence());
    }

    private SentenceParams getSentenceRequest() {

        SentenceParams request = new SentenceParams();
        request.setSubject(getSubject());
        request.setVerb(getVerb());
        request.setArticle(getArticle());
        request.setAdjective(getAdjective());
        request.setNoun(getNoun());

        return request;
    }

    private String getNoun() {

        String[] wordArray = nouns.split(",");
        int i = (int)Math.round(Math.random() * (wordArray.length - 1));
        return wordArray[i];
    }

    private String getAdjective() {

        String[] wordArray = adjectives.split(",");
        int i = (int)Math.round(Math.random() * (wordArray.length - 1));
        return wordArray[i];
    }

    private String getArticle() {

        String[] wordArray = articles.split(",");
        int i = (int)Math.round(Math.random() * (wordArray.length - 1));
        return wordArray[i];
    }

    private String getVerb() {

        String[] wordArray = verbs.split(",");
        int i = (int)Math.round(Math.random() * (wordArray.length - 1));
        return wordArray[i];
    }

    private String getSubject() {

        String[] wordArray = subjects.split(",");
        int i = (int)Math.round(Math.random() * (wordArray.length - 1));
        return wordArray[i];
    }
}
