package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1916_최소비용구하기
 * 메모리 : 48476KB
 * 시간 : 384ms
 * 길이 : 2006B
 * 풀이
 * 1. 다익스트라 - 인접리스트, PQ
 */

public class Main_B_G5_1916_최소비용구하기 {

	private static int N,M;
	private static int start,end;

	private static class Edge implements Comparable<Edge>{
		int v,weight;
		
		Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		//오름차순
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws Exception{
		//유향 가중치 그래프 -> 다익스트라
		// 정점(N) : 1<=N<=1000, 간선(M) : 1<=M<=100000 -> 인접리스트, pq사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
	
		List<Edge>[] adj = new ArrayList[N];
		for(int i=0; i<N; i++) {
			adj[i] = new ArrayList();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			adj[Integer.parseInt(st.nextToken())-1].add(new Edge(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())));
		}
		
		st = new StringTokenizer(br.readLine()," ");
		start = Integer.parseInt(st.nextToken())-1;
		end = Integer.parseInt(st.nextToken())-1;
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		boolean[] visited = new boolean[N];
		Edge[] D = new Edge[N];
		
		//start위치에서 시작
		for(int i=0; i<N; i++) {
			if(i == start) {
				D[i] = new Edge(i, 0);
			}else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
			}
			pq.add(D[i]);
		}
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			for (Edge next : adj[cur.v]) {
				//다음 정점에 저장되어 있는 가중치 합 > 지금까지의 가중치합 + 다음정점의 가중치 -> 교체
				if(!visited[next.v] && D[next.v].weight > D[cur.v].weight + next.weight) {
					D[next.v].weight = D[cur.v].weight + next.weight;
					
					//PQ에 기존Edge를 새로운 가중치합으로 바뀐 Edge로 교체해준다
					pq.remove(D[next.v]);
					pq.add(D[next.v]);
				}
			}
			visited[cur.v] = true;
		}
		
		System.out.println(D[end].weight);
	}

}
