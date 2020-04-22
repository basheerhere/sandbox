package sandbox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class L3PermMissingElemOld {

	public static void main(String[] args) {
		int[] A = { 1, 2, 4 };
		System.out.println("Solution 1:");
		solution(A);
		
		System.out.println("Solution 2:");
		solution2(A);
		
	}

	public static int solution(int[] A) {
		List<Integer> B = IntStream.rangeClosed(1, A.length).boxed().collect(Collectors.toList());
		List<Integer> AList = Arrays.stream(A).boxed().collect(Collectors.toList());
		B.removeAll(AList);
		return B.get(0);
	}

	public static int solution2(int[] A) {
		var mySetA = IntStream.of(A).boxed().collect(Collectors.toSet());
		//var mySetA = Set.of(Arrays.asList(A));
		var mySetB = IntStream.rangeClosed(1, A.length).boxed().collect(Collectors.toSet());
		mySetB.removeAll(mySetA);
		
		System.out.println("mySetA:" + mySetA);

		mySetB.forEach(x -> System.out.println(x));
		return -1;
	}
}

