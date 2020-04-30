package my.amazon.tech.assessment.practice;

import java.util.Arrays;

/**
 * 
 * 
 * 
 * <pre>
You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  
It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  
The letter-logs are ordered lexicographically ignoring identifier, 
with the identifier used in case of ties.  

The digit-logs should be put in their original order.

Return the final order of the logs.

 

Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 

Constraints:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.


my solution takes advantage of what we're guaranteed in the problem:

1. guaranteed to have a word following an identifier (allows me to use indexOf ' ' freely).
2. letter logs need to be ordered lexicographically, so we can use built in compare function when we know we have two.
3. number logs need to be sorted naturally, so we just say they're all "equal" to each other and trust java's built in sort feature to be stable.
4. number logs need to be after letter logs, so once we find out they're different, we return one of the other and short-circuit.
 * </pre>
 *
 */
public class ReorderLogs {
	public String[] reorderLogFiles(String[] logs) {
		Arrays.sort(logs, (s1, s2) -> {
			int idx1 = s1.indexOf(' ');
			int idx2 = s2.indexOf(' ');
			String l1 = s1.substring(idx1 + 1);
			String l2 = s2.substring(idx2 + 1);

			if (l1.charAt(0) <= '9') {
				if (l2.charAt(0) <= '9')
					return 0;
				else
					return 1;
			} else {
				if (l2.charAt(0) <= '9')
					return -1;
				else {
					int cmpContent = l1.compareTo(l2);
					if (cmpContent != 0)
						return cmpContent;
					return s1.substring(0, idx1).compareTo(s2.substring(0, idx2));
				}
			}
		});
		return logs;
	}

	public static void main(String[] args) {
		String[] logs = { "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero" };

		ReorderLogs solver = new ReorderLogs();
		Arrays.asList(solver.reorderLogFiles(logs)).stream().forEach(s -> System.out.println(s));
		Arrays.asList(solver.reorderLogFiles(logs)).stream().forEach(System.out::println);
		System.out.println(Arrays.toString(solver.reorderLogFiles(logs)));
	}
}