package sandbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Sort huge file consisting of known set of characters
 * 
 * @author bash
 *
 */
public class G2SortHugeFile {

	public static void main(String[] args) throws IOException {
		String fileName = "G2HugeFileToSort.txt";
		System.out.println("Destination file to sort and print: " + fileName);
		System.out.println("Sorted file contents follows...");
		System.out.println();
		solution(fileName);
	}

	public static void solution(String fileName) throws IOException {
		BufferedReader bfr = null;
		try {
			int bufferSize = 1024;
			bfr = new BufferedReader(new FileReader(new File(fileName)), bufferSize);
			char[] cBfr = new char[bufferSize];
			int[] resultCounter = new int[10];
			int charsRead = 0;
			// Read file stream character by character in a loop.
			while ((charsRead = bfr.read(cBfr)) > 0) {
				for (int position = 0; position < charsRead; position++) {
					char c = cBfr[position];
					int i = Character.getNumericValue(c);
					// Count number of entries of every character.
					if (i < 0 || i > 9) {
						System.out.printf("encountered a non numeric char %s at position %s\n", c, position + 1);
						continue;
					}
					resultCounter[i]++;
				}
			}

			// Print characters from the smallest to the biggest for specified number of
			// times mentioned in the saved counter.
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < resultCounter[i]; j++) {
					System.out.print(i);
				}
			}

			// That's all. No bubble sort, no quick sort, no any other actual sorting
			// applied at all.
		} finally {
			if (bfr != null) {
				bfr.close();
			}
		}
	}
}
