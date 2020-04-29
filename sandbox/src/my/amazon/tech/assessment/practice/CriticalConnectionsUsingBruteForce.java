package my.amazon.tech.assessment.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 
<pre>
Given an undirected connected graph with n nodes labeled 1..n. A bridge (cut edge) is defined as an edge which, 
when removed, makes the graph disconnected (or more precisely, increases the number of connected components in the graph). 
Equivalently, an edge is a bridge if and only if it is not contained in any cycle. 

The task is to find all bridges in the given graph. Output an empty list if there are no bridges.

Input:

n, an int representing the total number of nodes.
edges, a list of pairs of integers representing the nodes connected by an edge.
Example 1:

Input: n = 5, edges = [[1, 2], [1, 3], [3, 4], [1, 4], [4, 5]]

3-4-5
\/
1-2

Output: [[1, 2], [4, 5]]

Explanation:
There are 2 bridges:
1. Between node 1 and 2
2. Between node 4 and 5
If we remove these edges, then the graph will be disconnected.
If we remove any of the remaining edges, the graph will remain connected.
Example 2:

Input: n = 6, edges = [[1, 2], [1, 3], [2, 3], [2, 4], [2, 5], [4, 6], [5, 6]]

1   4
|\ / \
| 2   6
|/ \ /
3   5
Output: []
Explanation:
We can remove any edge, the graph will remain connected.
Example 3:

Input: n = 9, edges = [[1, 2], [1, 3], [2, 3], [3, 4], [3, 6], [4, 5], [6, 7], [6, 9], [7, 8], [8, 9]]

1     7
|\   / \
2-3-6   8
  | \  /
  |  9
  |
  4-5

Output: [[3, 4], [3, 6], [4, 5]]
</pre>
 * The approach is to one by one remove all edges and see if removal of an edge
 * causes a disconnected graph. For every edge (each list in PairInt) remove
 * (x,y), see if the graph remains connected using DFS (always start DFS at
 * vertex 1), add it back to the graph. If we do not visit each vertex then that
 * edge is a bridge, because removing that edge means the graph is no longer
 * connected.
 * 
 * @author macadmin2
 *
 */
public class CriticalConnectionsUsingBruteForce {
	List<PairInt> list;
	Map<Integer, Boolean> visited;

	List<PairInt> criticalConnections(int numOfServers, int numOfConnections, List<PairInt> connections) {
		Map<Integer, HashSet<Integer>> adj = new HashMap<>();
		for (PairInt connection : connections) {
			int u = connection.first;
			int v = connection.second;
			if (adj.get(u) == null) {
				adj.put(u, new HashSet<Integer>());
			}
			adj.get(u).add(v);
			if (adj.get(v) == null) {
				adj.put(v, new HashSet<Integer>());
			}
			adj.get(v).add(u);
		}

		list = new ArrayList<>();
		for (int i = 0; i < numOfConnections; i++) {
			visited = new HashMap<>();
			PairInt p = connections.get(i);
			int x = p.first;
			int y = p.second;
			adj.get(x).remove(y);
			adj.get(y).remove(x);
			DFS(adj, 1);
			if (visited.size() != numOfServers) {
				if (p.first > p.second)
					list.add(new PairInt(p.second, p.first));
				else
					list.add(p);
			}
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		return list;
	}

	public void DFS(Map<Integer, HashSet<Integer>> adj, int u) {
		visited.put(u, true);
		if (adj.get(u).size() != 0) {
			for (int v : adj.get(u)) {
				if (visited.getOrDefault(v, false) == false) {
					DFS(adj, v);
				}
			}
		}
	}
}

class PairInt {
	int first;
	int second;

	PairInt(int x, int y) {
		first = x;
		second = y;
	}
}