package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 7793_오나의여신님
 * 메모리 : 23008KB
 * 시간 : 110ms
 * 길이 : 2840B
 * 풀이
 * 1. BFS
 * 2. 방문체크 안해서 시간초과 오류
 * 3. 방문할 수 없는 곳 조건 실수
 * 4. 1초가 지날 때마다 악마의범위를 지정해주고, 나머지 범위에서 수연이가 갈 수 있는 곳을 체크함
 *
 */

public class Solution_D5_7793_오나의여신님 {

	private static int N,M;
	private static char[][] map;
	private static Queue<Point8> qs;	//수연이
	private static Queue<Point8> qd;	//악마
	private static boolean[][] visited_s;
	private static int MIN;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			qs = new LinkedList<Point8>();
			qd = new LinkedList<Point8>();
			visited_s = new boolean[N][M];
			MIN = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				for(int j=0; j<M; j++) {
					char input = str.charAt(j);
					map[i][j] = input;
					if(input == 'S') {
						visited_s[i][j] = true;
						qs.add(new Point8(i,j));
					}else if(input == '*') {
						qd.add(new Point8(i, j));
					}
				}
			}
			
			go();
			sb.append("#"+test+" "+(MIN == Integer.MAX_VALUE? "GAME OVER" : MIN)+"\n");
		}
		System.out.println(sb);
	}

	//우, 하, 좌, 상
	static int[] di = {0,1,0,-1};	
	static int[] dj = {1,0,-1,0};
	
	private static void go() {
		int result = 0;
		
		while(!qs.isEmpty()) {
			int size = qs.size();
			
			devilSkill();
			
			while(size --> 0) {
				//수연이 이동
				Point8 s = qs.poll();
				
				if(map[s.i][s.j] == 'D') {
					MIN = result;
					return;
				}
				
				for(int d=0; d<4; d++) {
					int next_i = s.i + di[d];
					int next_j = s.j + dj[d];
					
					if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
						continue;
					
					
					//돌이나 악마범위
					if(map[next_i][next_j] == 'X' || map[next_i][next_j] == '*')
						continue;
					
					//이미 방문한 곳이면
					if(visited_s[next_i][next_j])
						continue;
					
					qs.add(new Point8(next_i, next_j));
					visited_s[next_i][next_j] = true;
				}
				
			}
			result++;
		}
	}

	private static void devilSkill() {
		int s = qd.size();
		while(s --> 0) {
			Point8 devil = qd.poll();
			for(int d=0; d<4; d++) {
				int next_i = devil.i + di[d];
				int next_j = devil.j + dj[d];
				
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
					continue;
				
				if(map[next_i][next_j] == 'X' || map[next_i][next_j] == 'D' || map[next_i][next_j] == '*')
					continue;
				
				map[next_i][next_j] = '*';
				qd.add(new Point8(next_i, next_j));
			}
		}		
	}
	
}

class Point8{
	int i,j;
	Point8(int i, int j){
		this.i = i;
		this.j = j;
	}
}
