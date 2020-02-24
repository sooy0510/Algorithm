package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 2178_미로탐색
 * 메모리 : 13712KB
 * 시간 : 100ms
 * 풀이
 * 1. 최소거리이므로 레벨을 알아야 풀수있던 문제
 * => while문 두개를 사용해 한 레벨을 돌때마다 거리를 추가하도록 하였다
 *
 */

public class Main_B_S1_2178_미로탐색 {

	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int result=0;
	static int MIN = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		
		for(int i=1; i<N+1; i++) {
			String str = br.readLine();
			for(int j=1; j<M+1; j++) {
				map[i][j] = str.charAt(j-1)-'0';
			}
		}
		
		
		bfs(1,1);	//시작점 위치
		System.out.println(result);
		
	}

	//우, 하, 좌, 상 순
	static int[] di = {0,1,0,-1};
	static int[] dj = {1,0,-1,0};
	
	private static void bfs(int i, int j) {
		Queue<Point1> queue = new LinkedList<Point1>();
		visited[i][j] = true;
		queue.offer(new Point1(i,j));
		result++;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			result++;
			
			while(size --> 0) {
				Point1 now = queue.poll();
				
				for(int d=0; d<4; d++) {
					int next_i = now.i + di[d];
					int next_j = now.j + dj[d];
					
					if(next_i < 1 || next_i >= N+1 || next_j < 1 || next_j >=M+1)
						continue;
					
					if(next_i == N && next_j == M) {
						return;
					}
					
					//방문 전 체크
					if(!visited[next_i][next_j] && map[next_i][next_j] == 1) {
						visited[next_i][next_j] = true;
						queue.offer(new Point1(next_i,next_j));
					}
					
				}
			}
			
			
		}
		
		
		
	}

}

class Point1{
	int i,j;
	
	Point1(int i, int j){
		this.i = i;
		this.j = j;
	}
}
