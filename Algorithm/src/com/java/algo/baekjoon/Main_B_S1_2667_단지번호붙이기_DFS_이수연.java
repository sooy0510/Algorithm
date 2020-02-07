package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author sooy0510
 * 메모리 : 13204KB
 * 시간 : 92ms
 * 코드길이 : 2076B
 * 풀이 : 입력받은 map배열을 내부적으로 바꾸면서 탐색, 사방탐색 이용
 */

public class Main_B_S1_2667_단지번호붙이기_DFS_이수연 {

	private static int[][] map;
	private static boolean visited[][];
	static int cnt=0;
	static int N=0;
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[] danzies;
	static int danzi=2; 	//danzi ��ȣ
	static LinkedList<Integer> dong = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		danzies = new int[N*N/2+1];
		
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
					dfs(i,j,danzi);
					dong.add(cnt);
					cnt = 0;
					danzi++;
				}
				//danzies[danzi] = cnt;
				//cnt = 0;
			}
		}
		
		
		Collections.sort(dong);
		System.out.println(dong.size());
		for(int i : dong)
			System.out.println(i);
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
	}

	
	// 행, 열 동시에 탐색, 단지번호
	private static void dfs(int row, int col, int danzi) {
		
		for(int s=0; s<4; s++) {
			int next_row = row+dx[s];
			int next_col = col+dy[s];
			
			if(next_row <0 || next_row >= N || next_col <0 || next_col >=N)
				continue;
			
			if(map[next_row][next_col] == 1) {
				map[next_row][next_col] = danzi;
				cnt++;
				danzies[danzi]++;
				dfs(next_row, next_col, danzi);	
			}
		}
		
		if(cnt == 0)
			cnt++;
		
	}

}
