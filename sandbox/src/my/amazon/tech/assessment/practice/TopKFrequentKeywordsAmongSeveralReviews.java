package my.amazon.tech.assessment.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * <pre>
Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to least frequently mentioned.

The comparison of strings is case-insensitive. If keywords are mentioned an equal number of times in reviews, sort alphabetically.

Example 1:

Input:
k = 2
keywords = ["anacell", "cetracular", "betacellular"]
reviews = [
  "Anacell provides the best services in the city",
  "betacellular has awesome services",
  "Best services provided by anacell, everyone should use anacell",
]

Output:
["anacell", "betacellular"]

Explanation:
"anacell" is occuring in 2 different reviews and "betacellular" is only occuring in 1 review.

Example 2:

Input:
k = 2
keywords = ["anacell", "betacellular", "cetracular", "deltacellular", "eurocell"]
reviews = [
  "I love anacell Best services; Best services provided by anacell",
  "betacellular has great services",
  "deltacellular provides much better services than betacellular",
  "cetracular is worse than anacell",
  "Betacellular is better than deltacellular.",
]

Output:
["betacellular", "anacell"]

Explanation:
"betacellular" is occuring in 3 different reviews. "anacell" and "deltacellular" are occuring in 2 reviews, but "anacell" is lexicographically smaller.

IMPORTANT NOTE: Although 'anacell' is occurring 3 times, it only appears in 2 reviews. So, make sure to count the keyword only once per review.
 * 
 * </pre>
 * 
 * @author macadmin2
 *
 */
public class TopKFrequentKeywordsAmongSeveralReviews {

	private static List<String> solution(String[] reviews, String[] keywords, int k) {
		Set<String> keywordsSet = new HashSet<String>(
				Arrays.asList(keywords).stream().map(String::toLowerCase).collect(Collectors.toList()));
		Map<String, Integer> keywordCountsMap = new HashMap<String, Integer>(keywords.length);

		for (String review : reviews) {
			String[] wordsInReview = review.split("\\W");

			Set<String> wordsAlreadyAccountedForInReview = new HashSet<String>(keywords.length);

			for (String wordInReview : wordsInReview) {
				wordInReview = wordInReview.toLowerCase();
				if (keywordsSet.contains(wordInReview) && !wordsAlreadyAccountedForInReview.contains(wordInReview)) {
					keywordCountsMap.put(wordInReview, keywordCountsMap.getOrDefault(wordInReview, 0) + 1);
					wordsAlreadyAccountedForInReview.add(wordInReview);
				}
			}
		}

		List<String> candidateKeywords = new ArrayList<String>(keywordCountsMap.keySet());
		candidateKeywords.sort((w1, w2) -> keywordCountsMap.get(w1).equals(keywordCountsMap.get(w2)) ? w1.compareTo(w2)
				: keywordCountsMap.get(w2) - keywordCountsMap.get(w1));

		return candidateKeywords.subList(0, k);
	}

	public static void main(String[] args) {
		int k1 = 2;
		String[] keywords1 = { "anacell", "cetracular", "betacellular" };
		String[] reviews1 = { "Anacell provides the best services in the city", "betacellular has awesome services",
				"Best services provided by anacell, everyone should use anacell", };
		int k2 = 2;
		String[] keywords2 = { "anacell", "betacellular", "cetracular", "deltacellular", "eurocell" };
		String[] reviews2 = { "I love anacell Best services; Best services provided by anacell",
				"betacellular has great services", "deltacellular provides much better services than betacellular",
				"cetracular is worse than anacell", "Betacellular is better than deltacellular.", };

		System.out.format("\nTop %d keywords from the list of reivews: %s ", k1, solution(reviews1, keywords1, k1));
		System.out.format("\nTop %d keywords from the list of reivews: %s ", k2, solution(reviews2, keywords2, k2));
	}
}
