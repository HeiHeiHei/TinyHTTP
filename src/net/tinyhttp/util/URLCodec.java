package net.tinyhttp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

/**
 * Utility for URL encoding and decoding.
 * 
 * @author Hei
 */
public class URLCodec {

	/**
	 * Decodes a string using default UTF-8 encoding.
	 * 
	 * @param content
	 *            String to be decoded.
	 * 
	 * @return Decoded content string.
	 */
	public static String decode(String content) {
		try {
			return URLDecoder.decode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return content;
		}
	}

	/**
	 * Encodes a string using default UTF-8 encoding.
	 * 
	 * @param content
	 *            String to be encoded.
	 * 
	 * @return Encoded content string.
	 */
	public static String encode(String content) {
		try {
			return URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return content;
		}
	}

	/**
	 * Adds parameters from the query into a {@link Map}, with both parameter
	 * key and parameter value being a {@link String}.
	 * 
	 * @param query
	 *            HTTP query string.
	 * 
	 * @return Map containing mapped out parameters.
	 */
	public static Map<String, String> parseParameters(String query) {
		Map<String, String> parameters = Collections.emptyMap();
		if (query.contains("?")) {
			query = query.split("\\?", 2)[1];
		}
		String[] queryParameters = query.split("&");
		for (String parameter : queryParameters) {
			if (parameter.contains("=")) {
				String[] pair = parameter.split("=", 2);
				parameters.put(decode(pair[0]), decode(pair[1]));
			} else {
				/*
				 * If parameter doesn't contain equals sign, it means that
				 * parameter itself is a key and it's value is empty.
				 */
				parameters.put(decode(parameter), "");
			}
		}
		return parameters;
	}

}