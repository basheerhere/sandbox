package my.playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Precedence rules are given as a string array. Find the word when the rules
 * are followed.
 * 
 * @author Sharmi
 *
 */
public class TopTaleR2Task2FindWord {

	public static void main(String[] args) {
		caller(new String[] { "P>E", "E>R", "R>U" });
		caller(new String[] { "I>N", "A>I", "P>A", "S>P" });
		caller(new String[] { "e>t", "m>a", "h>y", "d>r", "y>d", "r>o", "o>m", "a>g", "g>n", "n>e", "t>i", "i>c",
				"c>s" });
	}

	public static void caller(String[] s) {
		System.out.printf("The word for input array %s is: %s\n", Arrays.asList(s), findWord(s));
	}

	public static String solution2(String[] S) {
		if (ArrayUtils.isEmpty(S)) {
			return null;
		}
		List<String> rules = Arrays.asList(S);
		StringBuffer wordBfr = new StringBuffer();
		List<String> resultStr = new ArrayList<String>();
		for (int i = 0; i < rules.size() - 1; i++) {
			char nextLetter = rules.get(i).charAt(0);
			for (String rule : rules) {
				if (StringUtils.contains(rule, nextLetter)) {
					if (rule.charAt(2) == nextLetter) {
						resultStr.add(rule);
					} else {
						resultStr.add(rules.get(i));
					}
				}
			}
		}
		return StringUtils.join(resultStr);

	}

	/***
	 * {"P>E","E>R","R>U"});
	 * 
	 * {"I>N","A>I","P>A","S>P"});
	 * 
	 * @param s
	 * @return
	 */

	public static String findWord1(String[] s) {
		StringBuffer result = new StringBuffer(s[0].replace(">", ""));
		for (int i = 1; i < s.length; i++) {
			char charToAdd = s[i].charAt(0);
			char charToFind = s[i].charAt(2);
			int nextIndex = StringUtils.indexOf(result, charToFind);
			if (nextIndex >= 0) {
				if (!StringUtils.contains(result, charToAdd)) {
					result.insert(nextIndex, charToAdd);
				}
			} else {
				if (!StringUtils.contains(result, charToAdd)) {
					result.append(charToAdd);
				}
				if (!StringUtils.contains(result, charToFind)) {
					result.append(charToFind);
				}
			}
		}
		return result.toString();
	}

	/**
	 * Working Solution.
	 * 
	 * @param s
	 * @return
	 */
	// caller(new String[] { "e>t", "m>a", "h>y", "d>r", "y>d", "r>o", "o>m", "a>g",
	// "g>n", "n>e", "t>i", "i>c", "c>s"});

	public static String findWord(String[] s) {
		List<String> rules = Arrays.asList(s);

		String firstString = rules.get(0), prevStr = null;
		String prevLetterToFind = ">" + firstString.charAt(0);
		while ((prevStr = findString(rules, prevLetterToFind)) != null) {
			prevLetterToFind = ">" + prevStr.charAt(0);
			firstString = prevStr;
		}
		StringBuffer result = new StringBuffer(firstString.replace(">", ""));

		String nextLetterToFind = result.charAt(1) + ">";
		String nextStr = null;
		while ((nextStr = findString(rules, nextLetterToFind)) != null) {
			result.append(nextStr.charAt(2));
			nextLetterToFind = nextStr.charAt(2) + ">";
		}

		return result.toString();
	}

	public static String findString(List<String> rules, String stringToFind) {
		Optional<String> foundElement = rules.stream().filter(x -> x.contains(stringToFind)).findFirst();
		return foundElement.isPresent() ? foundElement.get() : null;
	}
}
