package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_4485_녹색옷입은애가젤다지 {

	private static int N;
	private static int[][] adj;
	private static boolean[][] visited;
	private static int result;

	private static class Edge{
		int i,j,weight;
		Edge(int i, int j, int weight){
			this.i = i;
			this.j = j;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//최단경로, 다익스트라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			// 0 이면 전체 입력 종료
			if(N == 0) {
				break;
			}
			
			result = 0;
			adj = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<N; j++) {
					adj[i][j] = st.nextToken().charAt(0)-'0';
				}
			}
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(adj[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			visited = new boolean[N][N];
			bfs();
			System.out.println(result);
			
		}
		
	}

	//우하좌상
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	
	private static void bfs() {
		// (0,0)부터 시작
		Queue<Edge> queue = new LinkedList<Edge>();
		visited[0][0] = true;
		queue.add(new Edge(0,0,adj[0][0])); //i좌표, j좌표, 이제까지 잃은 코인의 합
		
		int i,j,weight;
		int ti,tj,nweight;
		int ni = 0,nj = 0;
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			i = cur.i; j = cur.j; weight = cur.weight;
			
			nweight = 999999999;
			for(int d=0; d<4; d++) {
				ti = i + dir[d][0];
				tj = j + dir[d][1];
				
				if(ti < 0 || tj < 0 || ti >= N || tj >= N)
					continue;
				
				if(visited[ti][tj]) continue;
				
				if(nweight > weight + adj[ti][tj]) {
					nweight = weight + adj[ti][tj];
					ni = ti; nj = tj;
				}
			}

			result = nweight;
			visited[ni][nj] = true;
			queue.add(new Edge(ni,nj,nweight));
		}
	}

}
