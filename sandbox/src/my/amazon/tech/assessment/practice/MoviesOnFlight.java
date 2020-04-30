package my.amazon.tech.assessment.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Movies on Flight
 * 
 * You are on a flight and wanna watch two movies during this flight. You are
 * given int[] movie_duration which includes all the movie durations. You are
 * also given the duration of the flight which is d in minutes. Now, you need to
 * pick two movies and the total duration of the two movies is less than or
 * equal to (d - 30min). Find the pair of movies with the longest total
 * duration. If multiple found, return the pair with the longest movie.
 * 
 * e.g. Input movie_duration: [90, 85, 75, 60, 120, 150, 125] d: 250
 * 
 * Output from aonecode.com [90, 125] 90min + 125min = 215 is the maximum number
 * within 220 (250min - 30min)
 *
 * 
 */

public class MoviesOnFlight {
	public static void main(String[] args) {
		int[] movie_duration1 = { 90, 85, 75, 60, 120, 150, 125 };
		int d1 = 250;
		int[] movie_duration2 = { 90, 85, 75, 60, 155, 150, 125 };
		int d2 = 250;
		int[] movie_duration3 = { 90, 85, 75, 60, 120, 110, 110, 150, 125 };
		int d3 = 250;
		System.out.println(Arrays.toString(get2SumClosest(movie_duration1, d1 - 30)));
		System.out.println(Arrays.toString(get2SumClosest(movie_duration2, d2 - 30)));
		System.out.println(Arrays.toString(get2SumClosest(movie_duration3, d3 - 30)));
	}

	private static int[] get2SumClosest(int[] movie_duration, int d) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < movie_duration.length; i++) {
			map.putIfAbsent(movie_duration[i], new ArrayList<>());
			map.get(movie_duration[i]).add(i);
		}

		System.out.println("Map of movie duraions as KEY with index locations as VALUE: " + map);

		Arrays.sort(movie_duration);

		int l = 0, r = movie_duration.length - 1;
		int max = 0;
		int[] res = new int[] { -1, -1 };
		while (l < r) {
			// Get the sum of shortest (left most in the sorted list) and longest (right
			// most in the sorted list) movies.
			int sum = movie_duration[l] + movie_duration[r];

			// if total duration is longer than the previously identified longer combination
			// of movies...
			// OR
			// if total duration is same as previously identified longer movie combo,
			// check if the longest movie identified now is even longer than the longest
			// movie of the previously identified movie combo
			//
			// AND
			//
			// make sure the total duration is still less than or equal to the target
			// duration

			// If above conditions are met, then we identified a new movie combination to
			// watch
			if ((sum > max || (sum == max && Math.max(movie_duration[l], movie_duration[r]) > Math.max(res[0], res[1])))
					&& sum <= d) {
				max = sum;
				res[0] = movie_duration[r]; // Watch the longest movie first
				res[1] = movie_duration[l];
			}

			// If total duration is longer than the target duration, lets move to the next
			// longest movie. Otherwise, lets move to the next shortest movie
			if (sum > d)
				r--;
			else
				l++;
		}

		// Finally, get the indices of the movies
		if (map.get(res[0]) == map.get(res[1])) {
			res[0] = map.get(res[0]).get(0);
			res[1] = map.get(res[1]).get(1);
		} else {
			res[0] = map.get(res[0]).get(0);
			res[1] = map.get(res[1]).get(0);
		}
		return res;
	}

	/*
	 * public static void main(String[] args) throws Exception { int[] arrayN = {
	 * 90, 85, 75, 60, 120, 150, 125 }; int d = 250; caller(d, arrayN); }
	 * 
	 * public static void caller(int d, int[] arr) {
	 * System.out.println("Two movies to watch, with their durations: " +
	 * solution(d, arr)); System.out.println(); }
	 * 
	 * public static int solution(int d, int[] arr) { int targetDuration = d - 30;
	 * List<Integer> movieDurationsLessThanTargetDuration =
	 * Arrays.stream(arr).sorted() .filter(i -> i <=
	 * targetDuration).peek(System.out::println).boxed().collect(Collectors.toList()
	 * ); Integer longestMovieDuration =
	 * Collections.max(movieDurationsLessThanTargetDuration);
	 * movieDurationsLessThanTargetDuration.remove(longestMovieDuration);
	 * System.out.println("longestMovieDuration = " + longestMovieDuration);
	 * 
	 * Integer totalMovieDuration = 0; Integer secondMovieDuration = 0;
	 * 
	 * do { secondMovieDuration =
	 * Collections.max(movieDurationsLessThanTargetDuration);
	 * movieDurationsLessThanTargetDuration.remove(secondMovieDuration);
	 * totalMovieDuration = longestMovieDuration + secondMovieDuration; } while
	 * (!movieDurationsLessThanTargetDuration.isEmpty() && totalMovieDuration >
	 * targetDuration); System.out.println("secondMovieDuration = " +
	 * secondMovieDuration);
	 * 
	 * return 1;
	 * 
	 * }
	 */
}
