package com.uj.aitokenization.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;
import com.knuddels.jtokkit.api.ModelType;
import com.uj.aitokenization.service.Tokenizer;

@Service("jtokkittokenizer")
public class JtokkiTokenizer implements Tokenizer {
	final static String TOKENIZERNAME="jtokkit";

	public Encoding getRegistry(String encodingType) throws UnsupportedEncodingException {
		EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
		try {
			Encoding enc = registry.getEncodingForModel(ModelType.fromName(encodingType).get());
			return enc;
		} catch (Exception e) {
			throw new UnsupportedEncodingException("Unsupported Encoding Type");
		}

	}

	@Override
	public Integer calculateTokens(String inputPrompt, String encodingType) throws UnsupportedEncodingException {
		Encoding enc = getRegistry(encodingType);
		int totalcount = enc.countTokensOrdinary(inputPrompt);
		return totalcount;

	}

	@Override
	public String getName() {
		return TOKENIZERNAME;
	}

	@Override
	public List<String> listAvailableEncodings() {
		List<String>modelNames =new ArrayList<String>();
		for (ModelType modelType : ModelType.values()) {
            modelNames.add(modelType.getName());
        }
       
		return modelNames;
	}

}
