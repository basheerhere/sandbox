package my.leetcode.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * <pre>
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where 
connections[i] = [a, b] represents a connection between servers a and b. 

Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.

   critical
   |
0-1-3
|/
2

			  2
			 /|
			1 |
			|\|
Critical -> | 0
			3

Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
 
Constraints:

1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.
 * </pre>
 * 
 * https://www.youtube.com/watch?v=aZXi1unBdJA
 * 
 *
 */

public class CriticalConnectionsFastestSolution {
	int edgeIndex = 0;
	int[] to;
	int[] next;
	int[] head;
	int[] low;
	int time = -1;
	List<List<Integer>> res = new ArrayList<>();

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		low = new int[n];
		int m = connections.size();
		to = new int[m * 2];
		head = new int[n];
		next = new int[m * 2];
		Arrays.fill(head, -1);
		Arrays.fill(next, -1);
		Arrays.fill(low, -1);

		printBooks("After initializing...");

		for (List<Integer> edge : connections) {
			int u = edge.get(0);
			int v = edge.get(1);
			addEdge(u, v);
			addEdge(v, u);
		}
		printBooks("After adding ALL edges...");

		dfs(1, -1);
		return res;
	}

	private void printBooks(String message) {
		System.out.println();
		System.out.println(message);
		System.out.format("Edge Index: %d\n", edgeIndex);
		System.out.format("to[]: %s\n", Arrays.toString(to));
		System.out.format("next[]: %s\n", Arrays.toString(next));
		System.out.format("head[]: %s\n", Arrays.toString(head));
		System.out.format("low[]: %s\n", Arrays.toString(low));
		System.out.format("time: %d\n", time);
		System.out.format("res: %s\n", res);
	}

	private void dfs(int node, int parent) {
		if (low[node] != -1) {
			return;
		}

		int min = low[node] = ++time;

		System.out.format("\nNode: %d, Parent: %d, Min: %d", node, parent, min);
		
		for (int edge = head[node]; edge != -1; edge = next[edge]) {
			int next = to[edge];
			System.out.format("\nNext: %d\n", next);

			if (low[next] == -1) {
				dfs(next, node);
				printBooks("After returning from recursion...");

				low[node] = Math.min(low[node], low[next]);

				if (low[next] > min) {
					res.add(Arrays.asList(node, next));
				}
			} else if (next != parent) {
				low[node] = Math.min(low[node], low[next]);
			}
		}
	}

	private void addEdge(int u, int v) {
		to[edgeIndex] = v;
		next[edgeIndex] = head[u];
		head[u] = edgeIndex++;
		printBooks("After adding edges: U=" + u + ", V=" + v);
	}

	public static void main(String[] args) {
		CriticalConnectionsFastestSolution solve = new CriticalConnectionsFastestSolution();

		int[][] connectionsArray = { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 1, 3 } };
		int n = 4;

		List<List<Integer>> connections = Arrays.stream(connectionsArray).map(Arrays::stream).map(IntStream::boxed)
				.map(values -> values.collect(Collectors.toList())).collect(Collectors.toList());
		System.out.println("Critical Connections:" + solve.criticalConnections(n, connections));
	}
}