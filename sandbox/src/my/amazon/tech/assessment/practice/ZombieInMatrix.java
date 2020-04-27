package my.amazon.tech.assessment.practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * <PRE>
Given a 2D grid, each cell is either a zombie 1 or a human 0. Zombies can turn adjacent (up/down/left/right) human 
beings into zombies every hour. Find out how many hours does it take to infect all humans?

Example:

Input:
[[0, 1, 1, 0, 1],
 [0, 1, 0, 1, 0],
 [0, 0, 0, 0, 1],
 [0, 1, 0, 0, 0]]

Output: 2

Explanation:
At the end of the 1st hour, the status of the grid:
[[1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [0, 1, 0, 1, 1],
 [1, 1, 1, 0, 1]]

At the end of the 2nd hour, the status of the grid:
[[1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1]]
 * </PRE>
 * 
 * @author macadmin2
 *
 */
public class ZombieInMatrix {
	public static void main(String[] args) {
		int[][] grid = { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0 } };
		System.out.println(minDays(grid));
	}

	private static int minDays(int[][] grid) {
		Queue<int[]> q = new LinkedList<>();
		int target = grid.length * grid[0].length;
		int cnt = 0, res = 0;

		// Add the position of zombies into a queue
		// Count the number of zombies
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					q.offer(new int[] { i, j });
					cnt++;
				}
			}
		}

		System.out.println("Queue of Zombie Locations:");
		q.stream().forEach(s -> System.out.println(Arrays.toString(s)));
		System.out.format("Target cells = %d, Count of Zombies: %d\n", target, cnt);

		int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		// BFS starting from existing zombies

		while (!q.isEmpty()) {
			int size = q.size();

			// If number of zombies matches the target,
			// then return the total number of iterations
			if (cnt == target)
				return res;

			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				System.out.println("Current Zombie location: " + Arrays.toString(cur));

				// check for a human in all directions
				for (int[] dir : dirs) {
					int ni = cur[0] + dir[0];
					int nj = cur[1] + dir[1];

					// If target location ni and nj is out of bounds we do nothing
					// If there is a human at the target location (ni, nj), turn them to a zombie &
					// increment zombie count to compare against the target count
					if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length && grid[ni][nj] == 0) {
						cnt++;
						q.offer(new int[] { ni, nj });
						grid[ni][nj] = 1;
					}
				}
			}
			res++;
		}

		// If reached here, there were no zombies to begin with and so it's not possible
		// to turn any human to a zombie. so, return -1
		return -1;
	}
}
