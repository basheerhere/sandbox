package my.hackerrank.algorithms.greedy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.*;

public class MinimumAbsoluteDifferenceSolution {

	// Complete the minimumAbsoluteDifference function below.
	static int minimumAbsoluteDifference(int[] arr) {
		// First, sort the array in ascending order.
		int[] sortedArray = Arrays.stream(arr).sorted().peek(System.out::println).toArray();

		// Next, once sorted, find the absolute difference between an element and its
		// next element and store it in a stream of integers

		// Lastly, find the minimum of the differences from the previous resulting
		// integer stream.
		System.out.println("Map of differences follows...");
		return IntStream.range(0, sortedArray.length - 1).map(i -> Math.abs(sortedArray[i] - sortedArray[i + 1]))
				.peek(System.out::println).min().getAsInt();

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] arr = new int[n];

		String[] arrItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int arrItem = Integer.parseInt(arrItems[i]);
			arr[i] = arrItem;
		}

		int result = minimumAbsoluteDifference(arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
