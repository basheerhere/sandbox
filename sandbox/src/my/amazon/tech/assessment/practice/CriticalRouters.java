package my.amazon.tech.assessment.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * <PRE>
You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which, 
when removed along with associated edges, makes the graph disconnected 
(or more precisely, increases the number of connected components in the graph). 

The task is to find all articulation points in the given graph.

Input:
The input to the function/method consists of three arguments:

numNodes, an integer representing the number of nodes in the graph.
numEdges, an integer representing the number of edges in the graph.
edges, the list of pair of integers - A, B representing an edge between the nodes A and B.

Output:
Return a list of integers representing the critical nodes.

Example:

Input: numNodes = 7, numEdges = 7, edges = [[0, 1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3, 4]]

    4
   /
  3 <- Critical
 / \
1   2 <- Critical
 \ / \
  0   5 <- Critical
     /
    6  

Output: [2, 3, 5]
 * </PRE>
 *
 */
public class CriticalRouters {

	static int time = 0;

	public static void main(String[] args) {
		int numRouters1 = 7;
		int numLinks1 = 7;
		int[][] links1 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 2, 5 }, { 5, 6 }, { 3, 4 } };
		System.out.println(getCriticalNodes(links1, numLinks1, numRouters1));
	}

	private static List<Integer> getCriticalNodes(int[][] links, int numLinks, int numRouters) {
		time = 0;
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < numRouters; i++) {
			map.put(i, new HashSet<>());
		}
		for (int[] link : links) {
			map.get(link[0]).add(link[1]);
			map.get(link[1]).add(link[0]);
		}
		Set<Integer> set = new HashSet<>();
		int[] low = new int[numRouters];
		int[] ids = new int[numRouters];
		int parent[] = new int[numRouters];
		Arrays.fill(ids, -1);
		Arrays.fill(parent, -1);
		for (int i = 0; i < numRouters; i++) {
			if (ids[i] == -1) // If this node is not yet visited, perform dfs
				dfs(map, low, ids, parent, i, set);
		}
		return new ArrayList<>(set);
	}

	private static void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] ids, int[] parent, int cur,
			Set<Integer> res) {
		int children = 0;
		ids[cur] = low[cur] = ++time;
		for (int nei : map.get(cur)) {
			// if this neighbor is not yet visited...,
			// - increment child count for current node,
			// - add current node as the parent of neighbor and
			// - perform dfs starting from neighbor
			if (ids[nei] == -1) {
				children++;
				parent[nei] = cur;
				dfs(map, low, ids, parent, nei, res);
				low[cur] = Math.min(low[cur], low[nei]);

				// After updating the lowLink value of current node,
				// - check if current node is the root (i,e, it parent is -1) AND
				// it has more than one children
				//
				// - OR
				//
				// - check if current node is not the root AND
				// current node id <= low link value of neighbor
				//
				// If any of the above condition is true, we found a critical router.
				if ((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= ids[cur]))
					res.add(cur);
			} else if (nei != parent[cur])
				low[cur] = Math.min(low[cur], ids[nei]);
		}
	}
}
