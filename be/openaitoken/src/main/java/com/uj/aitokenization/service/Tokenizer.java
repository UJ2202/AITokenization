package com.uj.aitokenization.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface Tokenizer {

	public Integer calculateTokens(String inputPrompt,String encodingType) throws UnsupportedEncodingException;
	public String getName();
	public List<String> listAvailableEncodings();
}
