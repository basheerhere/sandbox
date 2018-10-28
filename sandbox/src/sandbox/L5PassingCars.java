package sandbox;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Count the number of passing cars on the road.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingP2WZDW-8GK/
 * 
 * @author bash
 *
 */
public class L5PassingCars {

	public static void main(String[] args) {
		caller(new int[] { 0, 1, 0, 1, 1 });
		caller(new int[] { 1, 0, 1, 1, 0, 1, 1, 0 });
		caller(new int[] {});
		caller(new int[] { 0 });
		caller(new int[] { 1 });
		caller(new int[] { 1, 0 });
		caller(new int[] { 0, 1 });
		caller(new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
		caller(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 });
		caller(new int[] { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 });
		int[] hugeInts = IntStream.rangeClosed(0, 1000).toArray();
		caller(hugeInts);
	}

	public static void caller(int[] A) {
		System.out.println("Input Array = " + Arrays.toString(A));
		System.out.println("Number of cars on road = " + A.length);
		System.out.println("Number of passing cars = " + solution(A));
		System.out.println();
	}

	public static int solution(int[] A) {

		int eastBoundCarsOnRoad = 0;
		int passingCarsCount = 0;
		for (int carOnRoad : A) {
			if (carOnRoad == 0) {
				eastBoundCarsOnRoad++;
			} else {
				// Met a west bound car.
				if (eastBoundCarsOnRoad > 0) {
					if (passingCarsCount <= 1000000000) {
						// Because this west bound car is going to pass all the
						// east bound cars already located on the road, increase
						// the passing cars count by number of east bound cars met so far.
						passingCarsCount += eastBoundCarsOnRoad;
					} else {
						// passing cars already crossed max.
						return -1;
					}
				}
			}
		}
		return passingCarsCount;

	}
}
