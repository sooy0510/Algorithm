package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 4485_녹색옷입은애가젤다지?
 * 메모리 : 22444KB
 * 시간 : 312ms
 * 길이 : 2633B
 * 풀이
 * 1. 최단거리 -> 다익스트라
 * 2. pq에서 기존 가중치가 들어있는 점(문제에서는 Edge로 표시)을 제거하고 새 가중치가 있는 점으로 교체해야함!
 *
 */

public class Main_B_G4_4485_녹색옷입은애가젤다지 {

	private static int N;
	private static int[][] adj;
	private static boolean[][] visited;
	private static Edge[][] D;
	private static StringBuilder sb = new StringBuilder();

	private static class Edge implements Comparable<Edge>{
		int i,j,weight;
		Edge(int i, int j, int weight){
			this.i = i;
			this.j = j;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws Exception {
		//최단경로, 다익스트라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			// 0 이면 전체 입력 종료
			if(N == 0) {
				break;
			}
			
			adj = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<N; j++) {
					adj[i][j] = st.nextToken().charAt(0)-'0';
				}
			}
			
			visited = new boolean[N][N];
			dijkstra();

			sb.append("Problem"+" "+test+": "+D[N-1][N-1].weight+"\n");
			test++;
		}
		
		System.out.println(sb.toString());
	}

	//우하좌상
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	
	private static void dijkstra() {
		// (0,0)부터 시작
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		
		D = new Edge[N][N];	//경로(가중치)를 저장
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i == 0 && j == 0) {
					D[i][j] = new Edge(i,j, adj[i][j]);
				}else {
					D[i][j] = new Edge(i,j, 999999999);
				}
				pq.add(D[i][j]);
			}
		}
		
		int i,j,weight;
		int ni,nj,nweight;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			i = cur.i; j = cur.j; weight = cur.weight;
			
			//이제까지의 계산된 거리가 새로 꺼낸 가중치보다 작으면 계산할필요없음 
			if(D[i][j].weight < weight)continue;
			
			for(int d=0; d<4; d++) {
				ni = i + dir[d][0];
				nj = j + dir[d][1];
				
				if(ni < 0 || nj < 0 || ni >= N || nj >= N)
					continue;
				
				if(visited[ni][nj]) continue;
				
				//다음위치에 저장된 거리가 이번에 계산한거리보다 크다면, 방문한적이 없고
				if(D[ni][nj].weight > D[i][j].weight + adj[ni][nj]) {
					D[ni][nj].weight = D[i][j].weight + adj[ni][nj];
					pq.remove(D[ni][nj]);
					pq.add(D[ni][nj]);
				}
			}
			visited[i][j] = true;
		}
	}

}
