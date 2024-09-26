package com.uj.aitokenization.factory;

import com.uj.aitokenization.service.Tokenizer;

public interface TokenizerFactory {

	Tokenizer getTokenizerService(String TokenizerName);
	
	
}
