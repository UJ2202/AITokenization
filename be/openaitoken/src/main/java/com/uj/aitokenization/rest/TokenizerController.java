package com.uj.aitokenization.rest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uj.aitokenization.dto.PromptPayload;
import com.uj.aitokenization.factory.TokenizerFactory;
import com.uj.aitokenization.service.Tokenizer;

@RestController
@RequestMapping("/aitokenizer")
@CrossOrigin
public class TokenizerController {

	@Autowired
	TokenizerFactory tokenizerFactory;
	
	@Autowired
	List<Tokenizer> tokenizerList;

	@PostMapping("/validateTokenCount")
	public ResponseEntity<String> validateTokenCount(@RequestBody PromptPayload payload  ) {
		Tokenizer tokenizerService = null;
		try {
			tokenizerService = tokenizerFactory.getTokenizerService( payload.getTokenizerName()+ "tokenizer");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Unsupported Tokenizer Type");
		}
		try {
			Integer tokenCount = tokenizerService.calculateTokens(payload.getUserPrompt(), payload.getEncodingType());
			return ResponseEntity.status(200).body(tokenCount.toString());
		} catch (UnsupportedEncodingException e) {
			return ResponseEntity.status(500).body("Encoding Type Not Supported");
		}

	}

	@GetMapping("list/tokenizers")
	public ResponseEntity<String> listAvailableTokenizers(){
		HashMap<String,List<String>> availabletokenizers= new HashMap<String,List<String>>();
		tokenizerList.forEach(ts->{availabletokenizers.put( ts.getName(),ts.listAvailableEncodings()); });
		ObjectMapper objectMapper = new ObjectMapper();
		 try {
	            String jsonObject = objectMapper.writeValueAsString(availabletokenizers);
	           
	            return ResponseEntity.status(200).body(jsonObject);		
	            
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("Error while parsing JSON");
	        }
		
	}
	

	}
