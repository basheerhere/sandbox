package my.amazon.tech.assessment.practice;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
	public List<Integer> partitionLabels(String S) {
		if (S == null || S.length() == 0) {
			return null;
		}
		List<Integer> result = new ArrayList<>();
		int[] lastOccurrence = new int[26]; // record the last index of the each char

		for (int i = 0; i < S.length(); i++) {
			lastOccurrence[S.charAt(i) - 'a'] = i;
		}

		// record the end index of the current sub string
		int startOfPartition = 0;
		int endOfPartition = 0;

		for (int index = 0; index < S.length(); index++) {
			int lastOccurrenceOfLetter = lastOccurrence[S.charAt(index) - 'a'];

			if (lastOccurrenceOfLetter > endOfPartition) {
				endOfPartition = lastOccurrenceOfLetter;
			} else if (index == endOfPartition) { // We reached a partition
				result.add(endOfPartition - startOfPartition + 1);
				startOfPartition = endOfPartition + 1;
				endOfPartition = startOfPartition;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		PartitionLabels solver = new PartitionLabels();

		System.out.println(solver.partitionLabels("ababcbacadefegdehijhklij")); // should be [9, 7, 8]
	}
}