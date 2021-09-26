package controller.ranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringJoiner;

public class Ranking {

	private Map<String, Integer> rankingMap;
	private File rankingFile;

	public Ranking() throws ClassNotFoundException, IOException {
		this.rankingMap = new HashMap<>();
		System.out.println("path loading");
		this.rankingFile = new File("Rank.txt");
		System.out.println("path loaded");
		this.loadMapFromFile();
	}

	@SuppressWarnings("unchecked")
	public void loadMapFromFile() throws IOException, ClassNotFoundException {
		if (!this.rankingFile.exists()) {
			System.out.println("File not exists");
			this.rankingFile.createNewFile();
			this.saveMapToFile();
			System.out.println("File createdd 12");
		} else {
			Properties properties = new Properties();
			properties.load(new FileInputStream(this.rankingFile));
			this.rankingMap.clear();
			for (String key : properties.stringPropertyNames()) {
			   this.rankingMap.put(key, Integer.valueOf(properties.get(key).toString()));
			}
		}
	}

	private void saveMapToFile() throws IOException {
		Properties properties = new Properties();
		this.rankingMap = this.reverseSortMap(this.rankingMap);
		for (Map.Entry<String, Integer> entry : this.rankingMap.entrySet()) {
			properties.put(entry.getKey(), entry.getValue().toString());
		}
		properties.store(new FileOutputStream(this.rankingFile), null);
		System.out.println("map saved on file");
	}

	public void addToMap(String name, Integer points) throws IOException {
		this.rankingMap.put(name, points);
		this.saveMapToFile();
	}
	
	/**
	 * Reverse sort a Map<String,Integer> by value.
	 * ex: Map{(a,5), (b,1), (c,10)} -> Map{(c,10), (a,5), (b,1)}
	 * @param Map<String,Integer>
	 * @return Map<String,Integer>
	 */
	private Map<String,Integer> reverseSortMap(Map<String,Integer> unsortedMap){
		Map<String,Integer> sortedMap = new HashMap<>(unsortedMap);
		this.rankingMap.entrySet().stream()
	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
	    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		return sortedMap;
	}
	
	public Map<String,Integer> getRankingMap () {
		return this.rankingMap;
	}
	
	public String getMapToString() {
		StringJoiner s = new StringJoiner("");
		for (Map.Entry<String, Integer> entry : this.rankingMap.entrySet()) {
			s.add(entry.getKey()+":\t");
			s.add(entry.getValue().toString()+"\n");
		}
		return s.toString();
	}

}