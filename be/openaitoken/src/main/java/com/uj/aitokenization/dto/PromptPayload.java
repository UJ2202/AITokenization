package com.uj.aitokenization.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromptPayload {
	String userPrompt;
	String tokenizerName;
	String encodingType;
}
