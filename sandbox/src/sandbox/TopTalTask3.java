package sandbox;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Find the shortest path for a knight in the game of chess on an infinite chess
 * board
 * 
 * https://github.com/Jarosh/Exercises/wiki/Finding-the-shortest-path-as-for-knight--in-the-game-of-chess-on-an-infinite-board-(WARNING:-solution-for-%22can't-move-backward%22-scenario-that-isn't-actually-implied-by-the-original-problem).
 * 
 * Refer below for problem definition:
 * https://cloud.githubusercontent.com/assets/375123/12902375/cff946e2-ce75-11e5-841f-93ef6d28ebe1.png
 * 
 * @author bash
 *
 */
public class TopTalTask3 {

	public static void main(String[] args) {
		caller(2, 1); // 1
		caller(4, 2); // 2
		caller(6, 3); // 3

		caller(1, 2); // 1
		caller(2, 4); // 2
		caller(3, 6); // 3

		caller(1, 1); // ???

		caller(10, 20); // 10
//		caller(100, 200); // 100
//		caller(1000, 2000); // 1000
//		caller(10000, 20000); // 10000
//		caller(100000, 200000); // 100000
//
//		caller(-10, -20); // 10
//		caller(-100, -200); // 100
//		caller(-1000, -2000); // 1000
//		caller(-10000, -20000); // 10000
//		caller(-100000, -200000); // 100000
		caller(3, 10006); // ???
	}

	public static void caller(int A, int B) {
		System.out.printf("Destination Cell to move to: (%s, %s)\n", A, B);
		System.out.printf("Shortest path has %s moves.\n", solution(A, B));
		System.out.println();
	}

	/**
	 * Slowest O(N^N)
	 * 
	 * @param targetX
	 * @param targetY
	 * @return
	 */
	public static int solutionSlowest(int targetX, int targetY) {
		int[] rowMoveDistance = { 2, 2, -2, -2, 1, 1, -1, -1 };
		int[] columnMoveDistance = { 1, -1, -1, 1, 2, -2, 2, -2 };
		Map<ChessBoardCell, Boolean> visitedCellsMap = new HashMap<ChessBoardCell, Boolean>();

		Queue<ChessBoardCell> q = new ArrayDeque<ChessBoardCell>();

		ChessBoardCell startingCell = new ChessBoardCell(0, 0, 0);
		q.add(startingCell);

		while (!q.isEmpty()) {
			ChessBoardCell currentCell = q.poll();

			int currentX = currentCell.x;
			int currentY = currentCell.y;
			int distanceFromPreviousCell = currentCell.distnace;

			if (currentX == targetX && currentY == targetY) {
				return distanceFromPreviousCell;
			}

			if (visitedCellsMap.get(currentCell) == null) {
				visitedCellsMap.put(currentCell, true);
				for (int i = 0; i < 8; i++) {
					ChessBoardCell movableCell = new ChessBoardCell(currentX + rowMoveDistance[i],
							currentY + columnMoveDistance[i], distanceFromPreviousCell + 1);
					q.add(movableCell);
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * Refer to:
	 * https://github.com/Jarosh/Exercises/raw/master/path-of-chess-knight/path-of-chess-knight-02.png?raw=true
	 * 
	 * You can't move vertically without taking at least half of that move
	 * horizontally, as well as you can't move horizontally without taking at least
	 * half of that move vertically.
	 * 
	 * For e.g.,
	 * 
	 * 2x = 1y -> x/y = 1/2 -> x/y = 0.5
	 * 
	 * 2y = 1x -> x/y = 2/1 -> x/y = 2
	 * 
	 * Hence, moving on the positive quadrant (+X, +Y) leads to the formula:
	 * 
	 * ( x/y >= 0.5 && x/y <= 2 )
	 * 
	 * Moving on the negative quadrants leads to the formula
	 * 
	 * -2x = 1y -> -x/y = 1/2 -> x/y = -0.5
	 * 
	 * 2y = -1x -> -x/y = 2/1 -> x/y = -2
	 * 
	 * ( x/y <= -0.5 && x/y >= -2 )
	 * 
	 * Now, to reach movable cells in between the boundary, 'diagonals' can be
	 * formed by orange dots as shown in the image referenced above. Easily enough:
	 * move +1 on X-axis and -1 on Y-axis... until opposite reachable square located
	 * on the the boundary is met.
	 * 
	 * As for this specific 'diagonal', the sum of X and Y of every reachable point
	 * on it will be the same, and that the sum is divisible by 3. Also, 'opposite'
	 * points located on the boundaries represent extreme cases with either 1:2 or
	 * 2:1 ratio of X to Y, and the diagonal is built by just subtracting 1 from Y
	 * and adding 1 to X. Hence to check if a cell is reachable, we can use the
	 * formula:
	 * 
	 * ( (x+y) % 3 == 0 )
	 * 
	 * To determine the number of moves: because by one move you'd pass not more,
	 * not less but 3 squares, the total number of moves obviously is a multiple of
	 * 3 and it can't be less than the sum of X and Y of the destination point
	 * divided by 3. Hence to find the minimum number of moves, we can use the
	 * formula
	 * 
	 * N = (x+y)/3
	 * 
	 * , provided all the other conditions mentioned above are met!
	 * 
	 * @param targetX
	 * @param targetY
	 * @return
	 */
	public static int forwardOnlysolution(int targetX, int targetY) {
		double targetXByTargetY = (double) targetX / targetY;

		return (((targetX + targetY) % 3 == 0) && ((targetXByTargetY >= 0.5 && targetXByTargetY <= 2)
				|| (targetXByTargetY <= -0.5 && targetXByTargetY >= -2))
						? (((targetX + targetY) / 3 <= 100000000) ? Math.abs((targetX + targetY) / 3) : -2)
						: -1);
	}

	/**
	 * Incorrect.
	 * 
	 * @param targetX
	 * @param targetY
	 * @return
	 */
	public static int solution(int targetX, int targetY) {
		double targetXByTargetY = (double) targetX / targetY;

		if (targetY > (double) targetX / 2) {
			return ((targetX + targetY) / 3 <= 100000000) ? Math.abs((targetX + targetY) / 3) : -2;
		} else {
			return (targetX / 2 <= 100000000) ? Math.abs(targetX / 2) : -2;
		}
	}
}