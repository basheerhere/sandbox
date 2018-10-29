package my.playground;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Find the longest matching dominoes.
 * 
 * @author Sharmi
 *
 */
public class TopTaleR2Task1LongestMatchingDominoes {

	public static void main(String[] args) {
		caller("6-3");
		caller("4-3,5-1,2-2,1-3,4-4");
		caller("1-1,3-5,5-2,2-3,2-4");
		caller("2-4,4-1");

		caller("3-2,2-1,1-4,4-4,5-4,4-2,2-1");
		caller("5-5,5-5,4-4,5-5,5-5,5-5,5-5,5-5,5-5,5-5");
		caller("1-1,3-5,5-5,5-4,4-2,1-3");
	}

	public static void caller(String s) {
		System.out.printf("Longest matching tiles count for Dominoes %s: %s\n", s, solution(s));
	}

	public static int solution(String S) {
		if (StringUtils.isBlank(S)) {
			return 0;
		}
		List<String> dominoes = Arrays.asList(S.split(","));
		int matchingCount = 1;
		int longestMatch = 1;
		for (int i = 0; i < dominoes.size() - 1; i++) {
			if (dominoes.get(i).charAt(2) == dominoes.get(i + 1).charAt(0)) {
				matchingCount++;
			} else {
				longestMatch = Math.max(matchingCount, longestMatch);
				matchingCount = 1;
			}
		}
		longestMatch = Math.max(matchingCount, longestMatch);

		return longestMatch;
	}
}
