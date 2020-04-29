package my.leetcode.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class CriticalConnectionsInANetwork {
	static int[] discoveryTime;
	static int[] lowLinkValues;
	static boolean[] visited;
	static List<Integer>[] network;
	static int time = 0;
	static List<List<Integer>> criticalConnectionsList;

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		initialize(n);
		buildNetwork(connections);
		identifyCriticalConnections(0, -1);
		return criticalConnectionsList;
	}

	private void initialize(int n) {
		discoveryTime = new int[n];
		lowLinkValues = new int[n];
		visited = new boolean[n];
		criticalConnectionsList = new ArrayList<>();

		network = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			network[i] = new ArrayList<>();
		}
	}

	private void buildNetwork(List<List<Integer>> connections) {
		for (List<Integer> connection : connections) {
			int fromNode = connection.get(0);
			int toNode = connection.get(1);

			network[fromNode].add(toNode);
			network[toNode].add(fromNode);
		}
	}

	private void identifyCriticalConnections(int current, int parent) {
		time++;
		discoveryTime[current] = time;
		lowLinkValues[current] = time;
		visited[current] = true;

		for (int neighbor : network[current]) {
			if (neighbor == parent)
				continue;

			if (!visited[neighbor]) {
				identifyCriticalConnections(neighbor, current);
				lowLinkValues[current] = Math.min(lowLinkValues[current], lowLinkValues[neighbor]);
				if (discoveryTime[current] < lowLinkValues[neighbor]) {
					criticalConnectionsList.add(Arrays.asList(current, neighbor));
				}
			} else {
				lowLinkValues[current] = Math.min(lowLinkValues[current], discoveryTime[neighbor]);
			}
		}
	}
}