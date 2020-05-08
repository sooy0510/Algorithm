package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 17472_다리만들기2
 * 메모리 : 13252KB
 * 시간 : 76ms
 * 길이 : 4084B
 * 풀이
 * 1. BFS, PRIM
 * 2. 익숙해질때까지 풀어볼것
 */

public class Main_B_G3_17472_다리만들기2 {

	private static int N,M;
	private static int[][] map;
	
	//상하좌우
	static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
	private static int islandIdx;
	private static int[][] graph;
	private final static int INF = 987654321;
	
	static class Point{
		int i,j;
		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int v, cost;
		
		Edge(int v, int cost){
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
		
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//각 섬에 번호 붙이기
		islandIdx = 2;	//2 <= 섬의 개수 <= 6
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1) {
					setIslandIdx(i,j);
					islandIdx++;
				}
			}
		}
		
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		//graph 구성하기 - 각 섬간에 최단 거리 찾아서 업데이트 - 최대값으로 초기화
		graph = new int[islandIdx][islandIdx];
		for(int r=2; r < islandIdx; r++) {
			Arrays.fill(graph[r], INF);
		}
		
		//각 섬별로 거리 찾아보기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] > 1) {	//섬이면
					makeGraph(i, j);
				}
			}
		}
		
//		for (int i = 2; i < islandIdx; i++) {
//			for (int j = 2; j < islandIdx; j++) {
//				System.out.print(graph[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		//MST 구하기
		System.out.println(prim());
	}

	

	private static int prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		Edge[] d = new Edge[islandIdx]; 	//최소거리 저장할 배열
		
		for(int i=2; i<islandIdx; i++) {
			if(i==2) {
				d[i] = new Edge(i, 0);
			}else {
				d[i] = new Edge(i, INF);
			}
			
			pq.offer(d[i]);
		}
		
		int sum = 0;
		Edge edge, next;
		while(!pq.isEmpty()) {
			edge = pq.poll();
			
			//처음에 2부터 시작하고 그 이후로 경로 업데이트해가니까 cost도 변경된다. 그래도 INF이면 갈수없는것
			if(edge.cost == INF)	//갈수없는 경로
				return -1;
			
			sum += edge.cost;
			
			for(int i=2; i<islandIdx; i++) {
				next = d[i];
				if(pq.contains(next)) {
					if(next.cost > graph[edge.v][i]) {
						next.cost = graph[edge.v][i];
						pq.remove(next);
						pq.offer(next);
					}
				}
			}
		}
		
		return sum;
	}



	private static void makeGraph(int i, int j) {
		int base = map[i][j];	//기준 섬
		
		int ni,nj;
		for(int d=0; d<4; d++) {
			for(int c=1;; c++) {
				ni = i + dir[d][0] * c;
				nj = j + dir[d][1] * c;
				
				if(isIn(ni,nj)) {
					if(map[ni][nj] == 0) {	//바다
						continue;
					}else if(map[ni][nj] == base) {	//같은 섬
						break;
					}else {	//다른 섬
						if( c > 2) {	//길이가 2보다 클때 다리 만들기
							graph[base][map[ni][nj]] = graph[map[ni][nj]][base] = Math.min(graph[base][map[ni][nj]], c-1);
						}
						break;
					}
				}else 	//범위 벗어나있고, 이미 방문한 곳은 더 갈 필요 없음
					break;
			}
		}
	}



	private static void setIslandIdx(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		map[i][j] = islandIdx;
		q.add(new Point(i,j));
		
		Point cur;
		int ni, nj;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int d=0; d<4; d++) {
				ni = cur.i + dir[d][0];
				nj = cur.j + dir[d][1];
				
				if(isIn(ni,nj)) {
					if(map[ni][nj] == 1) {
						map[ni][nj] = islandIdx;
						q.add(new Point(ni,nj));
					}
				}
				
			}
		}
	}

	private static boolean isIn(int ni, int nj) {
		return (ni >= 0 && nj >= 0 && ni < N && nj < M);
	}

}
