package my.amazon.tech.assessment.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

	public static void main(String[] args) throws Exception {
		int[] arrayN = { 90, 85, 75, 60, 120, 150, 125 };
		int d = 250;
		caller(d, arrayN);
	}

	public static void caller(int d, int[] arr) {
		System.out.println("Two movies to watch, with their durations: " + solution(d, arr));
		System.out.println();
	}

	public static int solution(int d, int[] arr) {
		int targetDuration = d - 30;
		List<Integer> movieDurationsLessThanTargetDuration = Arrays.stream(arr).sorted()
				.filter(i -> i <= targetDuration).peek(System.out::println).boxed().collect(Collectors.toList());
		Integer longestMovieDuration = Collections.max(movieDurationsLessThanTargetDuration);
		movieDurationsLessThanTargetDuration.remove(longestMovieDuration);
		System.out.println("longestMovieDuration = " + longestMovieDuration);

		Integer totalMovieDuration = 0;
		Integer secondMovieDuration = 0;

		do {
			secondMovieDuration = Collections.max(movieDurationsLessThanTargetDuration);
			movieDurationsLessThanTargetDuration.remove(secondMovieDuration);
			totalMovieDuration = longestMovieDuration + secondMovieDuration;
		} while (!movieDurationsLessThanTargetDuration.isEmpty() && totalMovieDuration > targetDuration);
		System.out.println("secondMovieDuration = " + secondMovieDuration);

		return 1;

	}
}
