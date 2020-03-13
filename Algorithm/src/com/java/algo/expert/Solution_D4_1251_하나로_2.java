package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로_2 {

	private static int N;
	private static long[][] islands;
	private static long[][] graph;
	private static double E;
	
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
			
			//섬 좌표(i, j)넣기
			for(int n=0; n<N; n++) {
				islands[n] = new long[]{Long.parseLong(iLine.nextToken()), Long.parseLong(jLine.nextToken())};
			}

			E = Double.parseDouble(br.readLine());
			
			//그래프(간선-가중치 정보)만들기
			graph = new long[N][N];
			long[] from,to;
			
			for(int i=0; i<N; i++) {
				from = islands[i];
				for(int j=i+1; j<N; j++) {
					to = islands[j];
					graph[i][j] = graph[j][i] = (from[0] - to[0])*(from[0] - to[0]) + (from[1] - to[1])*(from[1] - to[1]);
				}
			}
			
			double cost = Prim(0) * E;	//시작정점 넣어주기
			sb.append(Math.round(cost));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static double Prim(int start) {
		//MST에 들어가지 않은 녀석들, PriorityQueue로 관리, 가중치 제일작은게 앞으로 와야하니까
		PriorityQueue<Edge> pq =  new PriorityQueue<Edge>();
		
		//모든 정점들을 관리
		Edge[] dynamicGraph = new Edge[N];
		
		//모든 정점들을 pq에 넣어준다
		for(int n=0; n<dynamicGraph.length; n++) {
			dynamicGraph[n] = new Edge(n, Long.MAX_VALUE);
			
			//시작점이면 가장 앞에 오게 함
			if(n == start) {
				dynamicGraph[n].cost = 0;
			}
			
			pq.add(dynamicGraph[n]);
		}
		
		long cost = 0;
		
		//MST에 모든 정점들이 들어갈때까지
		while(!pq.isEmpty()) {
			Edge front = pq.poll();
			cost += front.cost;
			
			//자식탐색
			for(int i=0; i<N; i++) {
				Edge child = dynamicGraph[i];
				//child가 MST에 포함되었는지 검사
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
		
		public Edge(int idx, long cost){
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			//간선의 가중치를 비교
			return Long.compare(this.cost, o.cost);
		}
		
	}
}
