package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로 {

	private static int N;
	private static long[][] islands;
	static double E;
	static long[][] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			sb.append("#"+test+" ");
			N = Integer.parseInt(br.readLine());
			
			islands = new long[N][2];
			
			StringTokenizer iLine = new StringTokenizer(br.readLine());
			StringTokenizer jLine = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) {
				islands[n] = new long[]{Integer.parseInt(iLine.nextToken()),Integer.parseInt(jLine.nextToken())}; 
			}
			
			
			E = Double.parseDouble(br.readLine());
			
			//간선정보 저장
			graph = new long[N][N];
			
			long[] from, to;
			for(int r=0; r<N; r++) {
				from = islands[r];
				for(int c=r+1; c<N; c++) {
					to = islands[c];
					graph[r][c] = graph[c][r] = (from[0] - to[0]) * (from[0] - to[0]) + (from[1] - to[1]) * (from[1] - to[1]);
				}
			}
			
			double cost = prim(0) * E;
			sb.append(Math.round(cost));
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	
	static double prim(int start) {
		//MST에 들어가지 않은 녀석들
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		//모든 정점들을 다 관리
		Edge[] dynamicGraph = new Edge[N];
		
		for(int n=0; n<dynamicGraph.length; n++) {
			dynamicGraph[n] = new Edge(n, Long.MAX_VALUE);
			//pq.add(dynamicGraph[n]);
			
			//pq에서 가장 앞쪽에 오게 하려고
			if(n == start) {
				dynamicGraph[n].cost = 0;
			}
			pq.add(dynamicGraph[n]);
		}

		long cost = 0;
		
		while(!pq.isEmpty()) {
			Edge front = pq.poll();	//MST에 포함되는 녀석, 제일 작은 녀석이 튀어나옴
			cost += front.cost;
			
			//자식탐색
			for(int i=0; i<N; i++) {
				Edge child = dynamicGraph[i];
				//child가 MST에 포함되지 않았는지
				//pq : 비 MST
				if(pq.contains(child)) {
					//가중치 비교
					long tempCost = graph[front.idx][child.idx];
					if(tempCost < child.cost) {
						child.cost = tempCost;
						//순서바꿔주기
						pq.remove(child);
						pq.offer(child);
					}
				}
			}
		}
		
		return cost;
	}
	
	static class Edge implements Comparable<Edge>{
		int idx;
		long cost;
		public Edge(int idx, long cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		//간선들의 가중치에 따라 비교
		public int compareTo(Edge o) {
			//return this.cost - o.cost>0 ? 1 : -1;
			return Long.compare(this.cost, o.cost);
		}
	}
}
