package tech.asmussen.dvi.api;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class News {
	
	public static final String NEWS_SOURCE = "https://nordjyske.dk/rss/nyheder";
	
	private static final ArrayList<String> NEWS_CACHE = new ArrayList<>();
	
	private static final String NEWS_UNKNOWN = "Ukendt.";
	
	public String getNews() {
		
		String news = NEWS_UNKNOWN;
		
		try {
			
			SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(NEWS_SOURCE)));
			
			feed.setEncoding(StandardCharsets.UTF_8.name());
			
			news = new String(feed.getEntries().get(new Random().nextInt(feed.getEntries().size())).getTitle().getBytes());
			
		} catch (FeedException | IOException e) {
			
			e.printStackTrace();
		}
		
		news = formatNews(news);
		
		if (!NEWS_CACHE.contains(news) && !news.isBlank() && !NEWS_UNKNOWN.equalsIgnoreCase(news))
			
			NEWS_CACHE.add(news);
		
		return "Nyheder: " + (news.isBlank() || NEWS_UNKNOWN.equalsIgnoreCase(news) ? (NEWS_CACHE.isEmpty() ? NEWS_UNKNOWN : NEWS_CACHE.get(new Random().nextInt(NEWS_CACHE.size()))) : news);
	}
	
	public String formatNews(String news) {
		
		if (news.endsWith(".") || news.endsWith("?") || news.endsWith("!"))
			
			return news;
		
		return news + ".";
	}
}
