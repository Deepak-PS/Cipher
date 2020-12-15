package com.crypto.cipher;

import java.util.Optional;

public class CipherImpl implements CipherI {

	private static String ASCII = "\\A\\p{ASCII}*\\z";

	private char[] charMessageArray;
	private int start;
	private int end;
	private int position;

	private char[] toCharacterArray(String message) {
		Optional.ofNullable(message).orElseThrow(IllegalArgumentException::new);
		return message.toCharArray();
	}

	public void setCharMessageArray(String message) {
		this.charMessageArray = toCharacterArray(message);
	}

	private void setStartAndEnd(int start, int end) {
		this.start = start;
		this.end = end;
	}

	private void checkCaseAndSetValues(char ch) {
		if (Character.isUpperCase(ch)) {
			// ASCII for upper case starts from 64 (A) and ends at 90 (Z)
			setStartAndEnd(64, 90);
		} else {
			// ASCII for lower case starts from 96 (a) and ends at 90 (Z)
			setStartAndEnd(96, 122);
		}
	}

	private boolean isAlphaAndAscii(char ch) {
		return Character.isAlphabetic(ch) && Character.toString(ch).matches(ASCII);
	}

	public String encode(String message, int rotations) {
		StringBuilder encodedString = new StringBuilder();
		setCharMessageArray(message);
		for (char ch : charMessageArray) {
			if (isAlphaAndAscii(ch)) {
				checkCaseAndSetValues(ch);
				position = ch + rotations;
				if (position >= end) {
					encodedString.append((char) (position % end + start));
				} else {
					encodedString.append((char) position);
				}
			} else
				encodedString.append(ch);
		}
		return encodedString.toString();
	}

	public String decode(String message, int rotations) {
		StringBuilder decodedString = new StringBuilder();
		setCharMessageArray(message);
		for (char ch : charMessageArray) {
			if (isAlphaAndAscii(ch)) {
				checkCaseAndSetValues(ch);
				position = ch - rotations;
				if (position <= start) {
					decodedString.append((char) (end - start % position));
				} else {
					decodedString.append((char) position);
				}
			} else
				decodedString.append(ch);
		}
		return decodedString.toString();
	}
}
