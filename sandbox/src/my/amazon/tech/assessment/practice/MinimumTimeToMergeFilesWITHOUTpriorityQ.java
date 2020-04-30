package my.amazon.tech.assessment.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This solution does not use PriorityQueue. Instead, it sorts the list of file
 * sizes on each iteration.
 * 
 * @author macadmin2
 *
 */
public class MinimumTimeToMergeFilesWITHOUTpriorityQ {

	public int minimumTime(int numOfSubFiles, List<Integer> files) {
		int result = 0;
		while (files.size() > 1) {
			Integer[] sorted = files.toArray(new Integer[files.size()]);
			Arrays.sort(sorted);
			files = new ArrayList<>(Arrays.asList(sorted));

			Integer sum = files.get(0) + files.get(1);

			files.remove(0);
			files.remove(0);
			files.add(sum);
			result += sum;
		}

		return result;
	}

	public static void main(String[] args) {
		MinimumTimeToMergeFilesWITHOUTpriorityQ solver = new MinimumTimeToMergeFilesWITHOUTpriorityQ();

		System.out.println(solver.minimumTime(4, Arrays.asList(8, 4, 6, 12))); // should be 58
		System.out.println(solver.minimumTime(4, Arrays.asList(20, 4, 8, 2))); // should be 54
		System.out.println(solver.minimumTime(6, Arrays.asList(1, 2, 5, 10, 35, 89))); // should be 224
		System.out.println(solver.minimumTime(4, Arrays.asList(2, 2, 3, 3))); // should be 20
		System.out.println(solver.minimumTime(1, Arrays.asList(12))); // should be 0
		System.out.println(solver.minimumTime(2, Arrays.asList(12, 5))); // should be 17
	}
}
