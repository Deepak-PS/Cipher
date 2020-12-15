package com.crypto.cipher;

public interface CipherI {
	public String encode(String message, int rotations);

	public String decode(String message, int rotations);
}
