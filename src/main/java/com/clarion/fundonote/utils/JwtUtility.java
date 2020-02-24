package com.clarion.fundonote.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtility {
	private JwtUtility() {
	}

	private static final String secretKey = "secret123";

	public static String generateToken(Long userid) {
		try {
			String token = JWT.create().withClaim("userId", userid)//create()->Returns a Json Web Token builder used to create and sign tokens,,withClaim()->return the parameter of name and value 
					.sign(Algorithm.HMAC512(secretKey));//sign->Creates a new JWT and signs is with the given algorithm 
			return token;//Tokens specify this as "HS512" algorithm
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Long validateToken(String token) {
		if (token != null) {
			// parse the token.
			return JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token).getClaim("userId").asLong();
		}
		return null;
	}
}
