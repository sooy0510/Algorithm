package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 1753_최단경로
 * 메모리 : 123936KB
 * 시간 : 1084ms
 * 길이 : 2221B
 * 풀이
 * 1. 유향 가중치 그래프, 간선이 적음 => 다익스트라 pq
 *
 */

public class Main_B_G5_1753_최단경로 {

	static class Edge implements Comparable<Edge>{
		int v, weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws Exception{
		//1<=V<=20000, 1<=E<=300,000
		//유향 가중치 그래프, 간선이 적음 => Dijstra_PQ
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		List <Edge>[] adj = new ArrayList[V+1]; 
		
		for(int i=1; i<V+1; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		int start = Integer.parseInt(br.readLine());
		
		//간선정보 받기
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			adj[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		//dijstra
		PriorityQueue<Edge> pq = new PriorityQueue();
		boolean[] check = new boolean[V+1];
		Edge[] D = new Edge[V+1];	//경로(가중치)를 저장
		
		for(int i=1; i<V+1; i++) {
			//지정해준 시작점부터 시작
			if(i == start) {
				D[i] = new Edge(i, 0);
			}else {
				D[i] = new Edge(i, 999999999);
			}
			
			pq.add(D[i]);
		}
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			//현재 정점과 인접한 정점들 검사
			for (Edge next : adj[edge.v]) {
				if(!check[next.v] && D[next.v].weight > D[edge.v].weight + next.weight) {
					D[next.v].weight =  D[edge.v].weight + next.weight;
					
				pq.remove(D[next.v]);
				pq.add(D[next.v]);
				}
			}
			check[edge.v]= true; 
		}
		

		for(int i=1; i<V+1; i++) {
			//경로가 존재하지 않는경우, "INF"출력
			if(D[i].weight == 999999999) { //Integer.MAX_VALUE하면 오버플로가능성
				System.out.println("INF");
			}
			else
				System.out.println(D[i].weight);
		}
 	}

}
