package tech.asmussen.dvi.core;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class News {
	
	public static final String NEWS_SOURCE = "https://nordjyske.dk/rss/nyheder";
	
	public String get() {
		
		String news = "Intet.";
		
		SyndFeedInput input = new SyndFeedInput();
		
		try {
			
			SyndFeed feed = input.build(new XmlReader(new URL(NEWS_SOURCE)));
			
			feed.setEncoding(StandardCharsets.UTF_8.name());
			
			news = new String(feed.getEntries().get(new Random().nextInt(feed.getEntries().size())).getTitle().getBytes());
			
		} catch (FeedException | IOException e) {
			
			e.printStackTrace();
		}
		
		return "Nyheder: " + (news.isBlank() ? "Intet." : news);
	}
}
