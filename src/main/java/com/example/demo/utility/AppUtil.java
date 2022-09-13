package com.example.demo.utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import org.springframework.http.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class AppUtil {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	public static String reverseWord(String str) {

		StringBuilder result = new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(str, " ");

		while (tokenizer.hasMoreTokens()) {
			StringBuilder sb = new StringBuilder();
			sb.append(tokenizer.nextToken());
			sb.reverse();
			result.append(sb);
			result.append(" ");
		}
		return result.toString().trim();
	}

	public static String sendEmail() {
		return "success";
	}

	@SuppressWarnings("unused")
	private String receivedEmail() {
		return "yes";
	}
}
