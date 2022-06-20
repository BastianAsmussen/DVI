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

/**
 * Used to get the latest news from a given RSS feed.
 *
 * @author Bastian A. W. Asmussen (BastianA)
 * @version 1.0.0
 * @see #getNews()
 * @see #formatNews(String)
 * @see #NEWS_SOURCE
 * @see #NEWS_CACHE
 * @see #NEWS_UNKNOWN
 */
public class News {
	
	/**
	 * Where to get the news from.
	 */
	public static final String NEWS_SOURCE = "https://nordjyske.dk/rss/nyheder";
	
	/**
	 * When there is no internet connection, a random element from the cache will be returned.
	 */
	private static final ArrayList<String> NEWS_CACHE = new ArrayList<>();
	
	/**
	 * If the news is unavailable and the {@link #NEWS_CACHE} is empty, this will be used as a fallback message.
	 */
	private static final String NEWS_UNKNOWN = "Ukendt.";
	
	/**
	 * A link to the current article. By default, this is set to {@link #NEWS_UNKNOWN}.
	 */
	public static String currentLink = NEWS_UNKNOWN;
	
	/**
	 * Get the latest news from the {@link #NEWS_SOURCE} and add it to the {@link #NEWS_CACHE} if it isn't already there.
	 *
	 * @return The latest news.
	 * @see #formatNews(String)
	 * @see #getCachedNews()
	 * @see #currentLink
	 * @see #NEWS_UNKNOWN
	 * @see #NEWS_CACHE
	 */
	public String getNews() {
		
		String news = NEWS_UNKNOWN; // The news gets set to NEWS_UNKNOWN by default.
		
		try {
			
			SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(NEWS_SOURCE))); // Get the news from the news source.
			
			feed.setEncoding(StandardCharsets.UTF_8.name()); // Set the encoding to UTF-8.
			
			int elementPosition = new Random().nextInt(feed.getEntries().size()); // Get a random element from the news.
			
			news = new String(feed.getEntries().get(elementPosition).getTitle().getBytes()); // Get a random news article from all the articles.
			currentLink = new String(feed.getEntries().get(elementPosition).getLink().getBytes()); // Get the link to the current article.
			
		} catch (FeedException | IOException e) {
			
			e.printStackTrace(); // If something goes wrong, print the stack trace.
		}
		
		news = formatNews(news); // Format the news.
		
		if (!NEWS_CACHE.contains(news) && !news.isBlank() && !NEWS_UNKNOWN.equalsIgnoreCase(news))
			
			NEWS_CACHE.add(news); // Add the news to the cache if it isn't already there.
		
		return "Nyheder: " + (news.isBlank() || NEWS_UNKNOWN.equalsIgnoreCase(news) ? getCachedNews() : news); // If the news is blank or NEWS_UNKNOWN, use the cache instead. If the cache is empty, use NEWS_UNKNOWN instead.
	}
	
	/**
	 * Get the link of the current article.
	 *
	 * @return The link to the current article.
	 * @see #currentLink
	 */
	public String getLink() {
		
		return currentLink; // Get the last news from the cache.
	}
	
	/**
	 * Grab a random element from {@link #NEWS_CACHE} and return it.
	 *
	 * @return If {@link #NEWS_CACHE} is empty, return {@link #NEWS_UNKNOWN}, otherwise return a random element from {@link #NEWS_CACHE}.
	 * @see #NEWS_CACHE
	 * @see #NEWS_UNKNOWN
	 */
	private String getCachedNews() {
		
		return NEWS_CACHE.isEmpty() ? NEWS_UNKNOWN : NEWS_CACHE.get(new Random().nextInt(NEWS_CACHE.size()));
	}
	
	/**
	 * Format the news to be more readable.
	 *
	 * @param news The news to format.
	 * @return The formatted news.
	 */
	public String formatNews(String news) {
		
		if (news.endsWith(".") || news.endsWith("?") || news.endsWith("!"))
			
			return news; // If the news ends with a punctuation mark, question mark or exclamation mark return the news.
		
		return news + "."; // Otherwise, add a period to the end of the news and then return it.
	}
}
