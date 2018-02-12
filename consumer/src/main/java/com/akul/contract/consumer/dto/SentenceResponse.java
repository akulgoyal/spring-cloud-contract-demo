package com.akul.contract.consumer.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SentenceResponse {

    String id;

    String sentence;
}
