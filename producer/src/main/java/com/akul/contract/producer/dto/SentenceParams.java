package com.akul.contract.producer.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SentenceParams {

    private String subject;

    private String verb;

    private String article;

    private String adjective;

    private String noun;
}
