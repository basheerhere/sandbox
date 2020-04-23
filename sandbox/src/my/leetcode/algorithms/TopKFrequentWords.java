package my.leetcode.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 * </pre>
 * 
 * @author macadmin2
 *
 */
public class TopKFrequentWords {
	public static List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		for (String word : words) {
			wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
		}

		List<String> candidates = new ArrayList<String>(wordCount.keySet());
		candidates.sort((w1, w2) -> wordCount.get(w1).equals(wordCount.get(w2)) ? w1.compareTo(w2)
				: wordCount.get(w2) - wordCount.get(w1));

		return candidates.subList(0, k);
	}

	public static void main(String[] args) throws Exception {
		String[] words = { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" };
		int k = 4;
		caller(words, k);
	}

	public static void caller(String[] words, int k) {
		System.out.format("Top %d most frequently used words: %s", k, topKFrequent(words, k));
	}
}
