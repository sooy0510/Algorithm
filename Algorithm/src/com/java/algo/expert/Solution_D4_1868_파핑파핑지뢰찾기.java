package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 1868_파핑파핑지뢰찾기
 * 메모리 : 48624KB
 * 시간 : 190ms
 * 길이 : 2388
 * 풀이
 * 1. 배열에서 지뢰아닌곳들을 모두 탐색하려고 해서 시간도 오래걸렸고, 접근방법을 잘못 생각했다
 * 2. 주변에 지뢰없는것만 bfs처리 해주기
 *
 */

public class Solution_D4_1868_파핑파핑지뢰찾기 {

	private static int N;
	private static char[][] map;
	static int[] di = {0,1,1,1,0,-1,-1,-1};
	static int[] dj = {1,1,0,-1,-1,-1,0,1};
	private static int cntClick;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			
			cntClick = 0;
			
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			
			//8방향에 지뢰가 하나도 없는 곳만 가능
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == '.' && !visited[i][j] && isPossible(i,j)) {
						cntClick++;
						click(i,j);	//클릭가능한곳만 bfs
					}
				}
			}
			
			//주변에 지뢰가 있어서 클릭되지 못한 곳
			int cntNotClick = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j] && map[i][j] == '.') {
						cntNotClick++;
					}
				}
			}
		
			sb.append("#").append(test).append(" ").append(cntClick+cntNotClick).append("\n");
		}
		System.out.println(sb);
	}

	private static void click(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		visited[i][j] = true;
		q.add(new Point(i,j));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
 			for(int d=0; d<8; d++) {
				int next_i = now.i + di[d];
				int next_j = now.j + dj[d];
				
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= N)
					continue;
				
				if(!visited[next_i][next_j]) {
					visited[next_i][next_j] = true;
					if(isPossible(next_i, next_j)) {
						q.add(new Point(next_i, next_j));
					}
				}
			}
		}		
	}

	private static boolean isPossible(int i, int j) {
		for(int d=0; d<8; d++) {
			int next_i = i + di[d];
			int next_j = j + dj[d];
			
			if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= N)
				continue;
			
			if(map[next_i][next_j] != '.')
				return false;
		}
		return true;
	}
	
	static class Point{
		int i,j;
		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}

