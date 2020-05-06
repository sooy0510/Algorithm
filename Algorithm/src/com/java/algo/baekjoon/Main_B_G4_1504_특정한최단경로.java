package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1504_특정한최단경로
 * 메모리 : 75664KB
 * 시간 : 924ms
 * 길이 : 2748B
 * 풀이
 * 1. Dijkstra_PQ
 * 2. 무향그래프래서 크루스칼이나 프림인줄 알았음 
 * 	-> 크루스칼은 가중치순대로 간선정렬한뒤 간선합쳐가야돼서 안됨
 * 3. 다익스트라에서 재방문가능하므로 방문처리 작업만 빼주기
 */

public class Main_B_G4_1504_특정한최단경로 {

	private static int V, E;
	private static int v1, v2;
	private static List<Edge>[] adj;
	private final static int INF = 800 * 1000;

	private static class Edge implements Comparable<Edge> {
		int v, weight;

		Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	public static void main(String[] args) throws Exception {
		// 최단경로 -> 다익스트라, 인접리스트 -> pq사용
		// 0 -> v1 -> v2 -> V-1
		// 0 -> v2 -> v1 -> V-1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < E; i++) {
	         st = new StringTokenizer(br.readLine(), " ");
	         // 무향그래프
	         int input1 = Integer.parseInt(st.nextToken()) - 1;
	         int input2 = Integer.parseInt(st.nextToken()) - 1;
	         int input3 = Integer.parseInt(st.nextToken());
	         adj[input1].add(new Edge(input2, input3));
	         adj[input2].add(new Edge(input1,input3));
	      }

		// dijkstra

		// 반드시 거쳐야 하는 두 정점 v1, v2
		st = new StringTokenizer(br.readLine(), " ");
		v1 = Integer.parseInt(st.nextToken()) - 1;
		v2 = Integer.parseInt(st.nextToken()) - 1;

		long sum1 = getDistance(0, v1) + getDistance(v1, v2) + getDistance(v2, V - 1);
		long sum2 = getDistance(0, v2) + getDistance(v2, v1) + getDistance(v1, V - 1);

		if (sum1 >= INF && sum2 >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(sum1, sum2));
		}

	}

	private static long getDistance(int v11, int v22) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		//boolean[] check = new boolean[V];	//재방문이므로 체크안해도됨
		Edge[] D = new Edge[V];

		// 시작점은 v1으로
		for (int i = 0; i < V; i++) {
			if (i == v11) {
				D[i] = new Edge(i, 0);
			} else {
				D[i] = new Edge(i, INF);
			}
			pq.add(D[i]);
		}

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			for (Edge next : adj[edge.v]) { // 정점에 연결되어있는 간선들 모두 탐색
				if (D[next.v].weight > D[edge.v].weight + next.weight) {
					D[next.v].weight = D[edge.v].weight + next.weight;
					pq.remove(D[next.v]);
					pq.add(D[next.v]);
				}
			}
			//check[edge.v] = true;
		}

		return D[v22].weight;
	}

}
