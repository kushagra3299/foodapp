package com.learning.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {
	
	public byte[] readFile(File file) throws IOException {
		byte[] allBytes = new byte[(int)file.length()];
		FileInputStream fileInputStream = new FileInputStream(file);
		fileInputStream.read(allBytes);
		return allBytes;
	}
	
	public String writeFile(byte[] allBytes, String fileName) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(fileName);
			fileOutputStream.write(allBytes);
			return "Success";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Fail";
		}
	}

}