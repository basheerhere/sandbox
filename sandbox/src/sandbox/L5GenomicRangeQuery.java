package sandbox;

import java.util.Arrays;

/**
 * Find the minimal nucleotide from a range of sequence DNA.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingRXU3TC-H4W/
 * 
 * @author bash
 *
 */
public class L5GenomicRangeQuery {

	public static void main(String[] args) {
		caller("CAGCCTA", new int[] { 2, 5, 0 }, new int[] { 4, 5, 6 });
		caller("CAGAC", new int[] { 2, 4, 0 }, new int[] { 4, 4, 4 });
		caller("GCCTC", new int[] { 4, 2, 0 }, new int[] { 4, 3, 0 });
	}

	public static void caller(String S, int[] P, int[] Q) {
		System.out.println("DNA Sequence: " + S);
		System.out.println("Queries starting positions: " + Arrays.toString(P));
		System.out.println("Queries ending positions: " + Arrays.toString(Q));
		System.out.println("Minimum Impact Factors:" + Arrays.toString(solution(S, P, Q)));
		System.out.println();
	}

	/**
	 * Slow due to O(N*M)
	 * 
	 * @param S
	 * @param P
	 * @param Q
	 * @return
	 */
	public static int[] slowSolution(String S, int[] P, int[] Q) {
		int[] minimumImpactFactors = new int[P.length];

//		for (int i = 0; i < P.length; i++) {
//			minimumImpactFactors[i] = IntStream.of(convertToImpactFactors(S.substring(P[i], Q[i] + 1))).min()
//					.getAsInt();
//		}

		int[] impactFactors = convertToImpactFactors(S);
		for (int i = 0; i < P.length; i++) {
			minimumImpactFactors[i] = Arrays.stream(impactFactors, P[i], Q[i] + 1).min().getAsInt();
		}

		return minimumImpactFactors;
	}

	public static int[] convertToImpactFactors(String S) {
		char[] dnaChars = S.toCharArray();
		int[] impactFactors = new int[S.length()];
		int i = 0;
		for (char c : dnaChars) {
			switch (c) {
			case 'A':
				impactFactors[i++] = 1;
				break;
			case 'C':
				impactFactors[i++] = 2;
				break;
			case 'G':
				impactFactors[i++] = 3;
				break;
			case 'T':
				impactFactors[i++] = 4;
				break;
			default:
				impactFactors[i++] = -1;
				break;
			}
		}
		return impactFactors;
	}

	public static int[] solution(String S, int[] P, int[] Q) {
		// used jagged array to hold the prefix sums of each A, C and G genoms
		// we don't need to get prefix sums of T, you will see why.
		int[][] genoms = new int[3][S.length() + 1];
		// if the char is found in the index i, then we set it to be 1 else they are 0
		// 3 short values are needed for this reason
		short a, c, g;
		for (int i = 0; i < S.length(); i++) {
			a = 0;
			c = 0;
			g = 0;
			if ('A' == (S.charAt(i))) {
				a = 1;
			}
			if ('C' == (S.charAt(i))) {
				c = 1;
			}
			if ('G' == (S.charAt(i))) {
				g = 1;
			}
			// here we calculate prefix sums. To learn what's prefix sums look at here
			// https://codility.com/media/train/3-PrefixSums.pdf
			genoms[0][i + 1] = genoms[0][i] + a;
			genoms[1][i + 1] = genoms[1][i] + c;
			genoms[2][i + 1] = genoms[2][i] + g;
		}

		int[] result = new int[P.length];
		// here we go through the provided P[] and Q[] arrays as intervals
		for (int i = 0; i < P.length; i++) {
			int fromIndex = P[i];
			// we need to add 1 to Q[i],
			// because our genoms[0][0], genoms[1][0] and genoms[2][0]
			// have 0 values by default, look above genoms[0][i+1] = genoms[0][i] + a;
			int toIndex = Q[i] + 1;
			if (genoms[0][toIndex] - genoms[0][fromIndex] > 0) {
				result[i] = 1;
			} else if (genoms[1][toIndex] - genoms[1][fromIndex] > 0) {
				result[i] = 2;
			} else if (genoms[2][toIndex] - genoms[2][fromIndex] > 0) {
				result[i] = 3;
			} else {
				result[i] = 4;
			}
		}

		return result;
	}
}
