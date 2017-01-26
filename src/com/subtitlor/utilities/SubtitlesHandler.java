package com.subtitlor.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubtitlesHandler {
	private ArrayList<String> originalSubtitles = null;
	private List<String> allSubtitles = null;

	public SubtitlesHandler(String fileName) {
		originalSubtitles = new ArrayList<String>();
		allSubtitles = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			boolean retrieve = false;
			while ((line = br.readLine()) != null) {
				allSubtitles.add(line);
				if (line.contains("-->")) {
					retrieve = true;
					continue;
				}
				if ("".equals(line)) {
					retrieve = false;
					continue;
				}
				if (retrieve) {
					originalSubtitles.add(line);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getSubtitles() {
		return originalSubtitles;
	}
	
	public List<String> getAllSubtitles() {
		return allSubtitles;
	}
	
}
