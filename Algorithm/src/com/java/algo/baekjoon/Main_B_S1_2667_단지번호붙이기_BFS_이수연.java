package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_B_S1_2667_단지번호붙이기_BFS_이수연 {

	private static int[][] map;
	static int cnt=0;
	static int N=0;
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int danzi=2; 	//danzi ��ȣ
	static LinkedList<Integer> dong = new LinkedList<>();
	static Queue<Point> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Character.getNumericValue(tmp.charAt(j));
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					cnt = 0;
					bfs(i,j,danzi);
					dong.add(cnt);
					//System.out.println(cnt);
					danzi++;
				}
			}
		}
		
		
		Collections.sort(dong);
		System.out.println(dong.size());
		for(int i : dong)
			System.out.println(i);
	}

	
	// 행, 열 동시에 탐색, 단지번호
	private static void bfs(int row, int col, int danzi) {
		
		q.offer(new Point(row, col));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			//map[p.row][p.col] = danzi;
			
			
			
			for(int s=0; s<4; s++) {
				int next_row = p.row+dy[s];
				int next_col = p.col+dx[s];
				
				if(next_row <0 || next_row >= N || next_col <0 || next_col >=N)
					continue;
				
				
				if(map[next_row][next_col] == 1) {
//					System.out.println("(" + next_row + "," + next_col + ")");
					map[next_row][next_col] = danzi;
					q.offer(new Point(next_row, next_col));
					cnt++;
		
				}
			}
		}
		// 주변에 아무집도 없을때
		if(cnt == 0)
			cnt++;
		
	}
	
}


class Point {
	int row,col;
	
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}