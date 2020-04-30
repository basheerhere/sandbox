package my.amazon.tech.assessment.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * <pre>
Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and 
the second integer represents a value. 

Your task is to find an element from a and an element form b such that the sum of their values is 
less or equal to target and as close to target as possible. 

Return a list of ids of selected elements. If no pair is possible, return an empty list.

Example 1:

Input:
a = [[1, 2], [2, 4], [3, 6]]
b = [[1, 2]]
target = 7

Output: [[2, 1]]

Explanation:
There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
Example 2:

Input:
a = [[1, 3], [2, 5], [3, 7], [4, 10]]
b = [[1, 2], [2, 3], [3, 4], [4, 5]]
target = 10

Output: [[2, 4], [3, 2]]

Explanation:
There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
Example 3:

Input:
a = [[1, 8], [2, 7], [3, 14]]
b = [[1, 5], [2, 10], [3, 14]]
target = 20

Output: [[3, 1]]
Example 4:

Input:
a = [[1, 8], [2, 15], [3, 9]]
b = [[1, 8], [2, 11], [3, 12]]
target = 20

Output: [[1, 3], [3, 2]]
 * </pre>
 *
 */
public class OptimalUtilization {

	private List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
		// Sort both list based on their values
		Collections.sort(a, (i, j) -> i[1] - j[1]);
		Collections.sort(b, (i, j) -> i[1] - j[1]);

		List<int[]> result = new ArrayList<>();

		// Follow two pointers approach
		int max = Integer.MIN_VALUE;
		int m = a.size();
		int n = b.size();
		int i = 0;
		int j = n - 1;
		while (i < m && j >= 0) {

			// Add A's shortest value (with index i starting from head of A at 0) to
			// B's largest value (with index j starting from tail of B at B.size-1)
			int sum = a.get(i)[1] + b.get(j)[1];
			if (sum > target) {
				--j;
			} else {
				if (max <= sum) {
					if (max < sum) {
						max = sum;

						// We found a new maximum. So lets clear the previous results and add newer
						// results.
						result.clear();
					}
					result.add(new int[] { a.get(i)[0], b.get(j)[0] });

					// Handle any duplicates
					// Basically, if target =20, List A has [1,8], List B has [2,12] and [3,12],
					// after adding [1,3], it also adds [1,2], since they both have 12.
					int index = j - 1;
					while (index >= 0 && b.get(index)[1] == b.get(index + 1)[1]) {
						result.add(new int[] { a.get(i)[0], b.get(index)[0] });
						index--;
					}
				}
				++i;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] a1 = { { 1, 3 }, { 2, 5 }, { 3, 7 }, { 4, 10 } };
		int[][] b1 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
		int target = 10;

		OptimalUtilization solver = new OptimalUtilization();

		List<int[]> a = Arrays.asList(a1);
		List<int[]> b = Arrays.asList(b1);
		System.out.println("Optimal Utilization: ");
		solver.getPairs(a, b, target).stream().forEach(s -> System.out.println(Arrays.toString(s)));
	}
}