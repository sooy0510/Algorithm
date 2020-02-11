package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1861_정사각형방
 * 메모리 : 29124KB
 * 실행시간 : 309ms
 * 풀이
 * 1. 다해놓고 방갯수로 분기하는 과정에서 실수했음
 * => 방갯수 같을때만 방번호 바꿔야하는지 체크해야하는데, 
 * 	방갯수 더 클때도 체크하도록 해서 방번호가 이상하게 나왓다...
 *
 */

public class Solution_D4_1861_정사각형방 {

	static int N;
	static int[][] arr;
	
	//상하좌우
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	static int cnt = 0;
	static int roomnum;
	static int max = 0;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("Solution1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			max = 0;
			
			arr = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			roomnum = N*N;
			//각지점마다 순회(row, col, cnt)
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					cnt = 0;
					dfs(i,j,0);
					//////////////////////////////////
					if(cnt == max) {
						//max = cnt;
						if(arr[i][j] < roomnum) {
							roomnum = arr[i][j];
						}
					}
					
					if(cnt > max) {
						max = cnt;
						roomnum = arr[i][j];
					}
					//////////////////////////////////
				}
			}
			
			
			sb.append("#"+test+" "+roomnum+" "+(max+1)+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int row, int col, int count) {
		for(int d=0; d<4; d++) {
			int next_row = row + dy[d];
			int next_col = col + dx[d];
			
			if(next_row < 0 || next_row >= N || next_col < 0 || next_col >= N) {
				continue;
			}
			
			if(arr[next_row][next_col] - arr[row][col] == 1) {
				cnt += 1;
				count += 1;
				dfs(next_row, next_col, count);
			}else {
				continue;
			}
			
		}
	}

}
