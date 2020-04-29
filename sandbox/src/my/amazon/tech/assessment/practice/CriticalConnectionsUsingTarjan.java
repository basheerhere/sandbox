package my.amazon.tech.assessment.practice;

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
 * https://www.youtube.com/watch?v=aZXi1unBdJA
 * 
 *
 */
public class CriticalConnectionsUsingTarjan {
	static int[] discoveredTime; // Discovery time of a server
	static int[] lowestServer; // lowest server that can be reached from current server
	static boolean[] visited;
	static List<List<Integer>> criticalConnections;
	static List<Integer>[] network;
	static int time = 0;

	public static List<List<Integer>> getCriticalConnections(int noOfServers, List<List<Integer>> connections) {
		initialize(noOfServers);
		
		buildNetwork(connections);
		System.out.println("Network:" + Arrays.toString(network));
		
		identifyCriticalConnections(0, -1);

		return criticalConnections;
	}

	public static void initialize(int noOfServers) {
		discoveredTime = new int[noOfServers];
		lowestServer = new int[noOfServers];
		visited = new boolean[noOfServers];
		criticalConnections = new ArrayList<>();

		network = new ArrayList[noOfServers];
		for (int i = 0; i < noOfServers; i++) {
			network[i] = new ArrayList<>();
		}
	}

	public static void buildNetwork(List<List<Integer>> connections) {
		for (List<Integer> connection : connections) {
			int from = connection.get(0);
			int to = connection.get(1);

			network[from].add(to);
			network[to].add(from);
		}
	}

	public static void identifyCriticalConnections(int current, int parent) {
		time++;
		discoveredTime[current] = time;
		lowestServer[current] = time;
		visited[current] = true;

		for (int neighbor : network[current]) {
			if (neighbor == parent) {
				continue;
			}

			if (visited[neighbor] == false) { // if neighbor's discovery time is not found
				identifyCriticalConnections(neighbor, current);
				lowestServer[current] = Math.min(lowestServer[current], lowestServer[neighbor]);

				// lowestServer of neighbor > current's discoveredTime => critical connection!!
				// lowestServer of neighbor <= current's discoveredTime => not critical
				// connection. there is a circular network.
				if (lowestServer[neighbor] > discoveredTime[current]) {
					criticalConnections.add(Arrays.asList(current, neighbor));
				}
			} else {
				// if this neighbor is already visited, lowestServer of current will be
				// updated.
				lowestServer[current] = Math.min(lowestServer[current], discoveredTime[neighbor]);
			}
		}
	}

	public static void main(String[] args) {

		int[][] grid = { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 1, 3 } };
		int n = 4;

		List<List<Integer>> connections = Arrays.stream(grid).map(Arrays::stream).map(IntStream::boxed)
				.map(values -> values.collect(Collectors.toList())).collect(Collectors.toList());
		System.out.println("Critical Connections:" + getCriticalConnections(4, connections));

	}
	/*
	 * static int currentTime = 0; static List<Integer>[] myNetwork; static int[]
	 * lowestServer; // lowest server that can be reached from current server static
	 * int[] discoveredTime; // discovery time to a server boolean[] alreadyVisited;
	 * static List<List<Integer>> criticalConnections;
	 * 
	 * 
	 * static int time = 0; static List<Integer>[] network; static int[]
	 * lowestVertex; // lowest vertext from current vertex static int[]
	 * discoveredTime; // discovered time of vertext static boolean[] visited;
	 * static List<List<Integer>> critialConnections;
	 * 
	 * public static List<List<Integer>> criticalConnections(int n,
	 * List<List<Integer>> connections) {
	 * 
	 * initialization(n); buildNetwork(connections); getCritialConnections(0, -1);
	 * 
	 * return critialConnections; }
	 * 
	 * public static void initialization(int n) { lowestVertex = new int[n];
	 * discoveredTime = new int[n]; visited = new boolean[n]; critialConnections =
	 * new ArrayList<>();
	 * 
	 * network = new ArrayList[n]; for (int i = 0; i < n; i++) network[i] = new
	 * ArrayList<>(); }
	 * 
	 * public static void buildNetwork(List<List<Integer>> connections) { for
	 * (List<Integer> connection : connections) {
	 * network[connection.get(0)].add(connection.get(1));
	 * network[connection.get(1)].add(connection.get(0)); } }
	 * 
	 * // dfs search public static void getCritialConnections(int current, int
	 * parent) {
	 * 
	 * time++; lowestVertex[current] = time; discoveredTime[current] = time;
	 * visited[current] = true;
	 * 
	 * for (int neighbor : network[current]) { if (neighbor == parent) continue;
	 * 
	 * if (visited[neighbor] == false) { // if it does not discovered
	 * 
	 * getCritialConnections(neighbor, current);
	 * 
	 * lowestVertex[current] = Math.min(lowestVertex[current],
	 * lowestVertex[neighbor]);
	 * 
	 * 
	 * // lowestVertex of neighbor > current's discoveredTime => critical
	 * connection!! // lowestVertex of neighbor <= current's discoveredTime => not
	 * critical // connection. there is a circular network.
	 * 
	 * if (lowestVertex[neighbor] > discoveredTime[current]) {
	 * critialConnections.add(Arrays.asList(current, neighbor)); } } else { // if
	 * this neighbor is already visited, lowerVertext of current will be //
	 * updated!! lowestVertex[current] = Math.min(lowestVertex[current],
	 * discoveredTime[neighbor]); } } }
	 * 
	 * 
	 * 
	 * private int timeOfDiscovery = 0;
	 * 
	 * public List<List<Integer>> findCriticalConnections(int numberOfServers,
	 * List<List<Integer>> connections) { List<List<Integer>> criticalConnections =
	 * new ArrayList<>();
	 * 
	 * // Build a graph of servers and its connections List<Integer>[] graph = new
	 * ArrayList[numberOfServers]; for (int i = 0; i < numberOfServers; i++) {
	 * graph[i] = new ArrayList<>(); }
	 * 
	 * for (List<Integer> aConnection : connections) { int server1 =
	 * aConnection.get(0); int server2 = aConnection.get(1);
	 * 
	 * graph[server1].add(server2); graph[server2].add(server1); }
	 * 
	 * boolean[] visited = new boolean[numberOfServers]; int[] discoveryTimeOfServer
	 * = new int[numberOfServers]; int[] earliestDiscoveredServer = new
	 * int[numberOfServers];
	 * 
	 * dfs(0, -1, discoveryTimeOfServer, earliestDiscoveredServer, visited, graph,
	 * criticalConnections);
	 * 
	 * return criticalConnections; }
	 * 
	 * public void dfs(int root, int parent, int[] discoveryTimeOfServer, int[]
	 * earliestDiscoveredServer, boolean[] visited, List<Integer>[] graph,
	 * List<List<Integer>> criticalConnections) { visited[root] = true;
	 * discoveryTimeOfServer[root] = timeOfDiscovery++;
	 * earliestDiscoveredServer[root] = discoveryTimeOfServer[root];
	 * 
	 * for (Integer neighbor : graph[root]) { if (neighbor == parent) { continue; }
	 * 
	 * if (!visited[neighbor]) { dfs(neighbor, root, discoveryTimeOfServer,
	 * earliestDiscoveredServer, visited, graph, criticalConnections);
	 * earliestDiscoveredServer[root] = Math.min(earliestDiscoveredServer[root],
	 * earliestDiscoveredServer[neighbor]); if (discoveryTimeOfServer[root] <
	 * earliestDiscoveredServer[neighbor]) { // we found a critical connection
	 * criticalConnections.add(Arrays.asList(root, neighbor)); }
	 * 
	 * } else { earliestDiscoveredServer[root] =
	 * Math.min(earliestDiscoveredServer[root], discoveryTimeOfServer[neighbor]); }
	 * }
	 * 
	 * }
	 * 
	 * public static void main(String[] args) {
	 * 
	 * int[][] grid = { { 0, 1, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, {
	 * 0, 1, 0, 0, 0 } };
	 * 
	 * List<List<Integer>> connections =
	 * Arrays.stream(grid).map(Arrays::stream).map(IntStream::boxed) .map(values ->
	 * values.collect(Collectors.toList())).collect(Collectors.toList());
	 * //System.out.println(minDays(grid));
	 * 
	 * }
	 */
}
