package com.example.demo.utility;

import java.util.StringTokenizer;

public class AppUtil {

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
