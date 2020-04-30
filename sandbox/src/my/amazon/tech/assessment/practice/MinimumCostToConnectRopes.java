package my.amazon.tech.assessment.practice;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * <PRE>
Given n ropes of different lengths, we need to connect these ropes into one rope. 
We can connect only 2 ropes at a time. The cost required to connect 2 ropes is 
equal to sum of their lengths. The length of this connected rope is also equal 
to the sum of their lengths. This process is repeated until n ropes are connected 
into a single rope. 

Find the min possible cost required to connect all ropes.

Example 1:

Input: ropes = [8, 4, 6, 12]
Output: 58
Explanation: The optimal way to connect ropes is as follows
1. Connect the ropes of length 4 and 6 (cost is 10). Ropes after connecting: [8, 10, 12]
2. Connect the ropes of length 8 and 10 (cost is 18). Ropes after connecting: [18, 12]
3. Connect the ropes of length 18 and 12 (cost is 30).
Total cost to connect the ropes is 10 + 18 + 30 = 58

Example 2:

Input: ropes = [20, 4, 8, 2]
Output: 54

Example 3:

Input: ropes = [1, 2, 5, 10, 35, 89]
Output: 224

Example 4:

Input: ropes = [2, 2, 3, 3]
Output: 20 

A typical priority queue problem, every step you pick two shortest ropes and connect them, then put it back to the queue, you keep this process until there is only one left.

PROOF that the greedy approach (i.e., picking two shortest ropes) is optimal:

Think of it this way, every time you connect two ropes you get a bigger rope (assuming non negative rope length). 
We want to minimize the overall cost. The cost is dependent on the sum of a pair. But once we have connected two ropes together 
it acts as a single rope, and hence the sum of that will be even larger.

It is a monotonic function which means the minimum of each steps leads to an overall minimum -- the definition of greedy. 
Hence to minimize it, all you have to do is make sure you always form a very short rope so that the next time you join it'll minimize the added rope cost.
 * </PRE>
 * 
 *
 */
public class MinimumCostToConnectRopes {
	public int minCost(List<Integer> ropes) {
		Queue<Integer> pq = new PriorityQueue<>(ropes);
		int totalCost = 0;
		while (pq.size() > 1) {
			int cost = pq.poll() + pq.poll();
			pq.add(cost);
			totalCost += cost;
		}
		return totalCost;
	}

	public static void main(String[] args) {
		List<Integer> ropes = Arrays.asList(20, 4, 8, 2);

		MinimumCostToConnectRopes solver = new MinimumCostToConnectRopes();
		System.out.println("Minimum cost to connect all ropes is: " + solver.minCost(ropes));
	}
}
