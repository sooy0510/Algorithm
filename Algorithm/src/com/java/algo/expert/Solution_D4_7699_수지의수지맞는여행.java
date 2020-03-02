package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 7699_수지의수지맞는여행
 * 메모리 : 29320KB
 * 시간 : 138ms
 * 길이 : 1695B
 * 풀이
 * 1. DFS
 * -> 다음 DFS를 위해 유적방문을 false처리
 * 2. MAX값의 최대는 26이기 때문에 MAX==26일때 리턴시켜서 실행시간을 단축
 *
 */

public class Solution_D4_7699_수지의수지맞는여행 {

	private static int R;
	private static int C;
	private static char[][] map;
	private static boolean[] ruins;
	static int MAX = 0;
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			MAX = 0;
			map = new char[R+1][C+1];
			ruins = new boolean[26];
			
			for(int i=1; i<R+1; i++) {
				String str = br.readLine();
				for(int j=1; j<C+1; j++) {
					map[i][j] = str.charAt(j-1);
				}
			}
			
			ruins[map[1][1] - 'A'] = true;
			go(1,1,1);
			
			sb.append("#"+test+" "+MAX+"\n");
		}
		System.out.println(sb);
	}

	//우, 하, 좌, 상
	static int[] di = {0,1,0,-1};
	static int[] dj = {1,0,-1,0};
	
	private static void go(int i, int j, int cnt) {
		
		if(cnt > MAX) {
			MAX = cnt;
		}
		
		if(MAX == 26){
	        return;
	    }
		
		for(int d=0; d<4; d++) {
			int next_i = i + di[d];
			int next_j = j + dj[d];
			
			if(next_i < 1 || next_i >= R+1 || next_j < 1 || next_j >= C+1)
				continue;
			
			//본 명물아니면
			if(!ruins[map[next_i][next_j]-'A']) {
				ruins[map[next_i][next_j]-'A'] = true;
				go(next_i, next_j, cnt+1);
				ruins[map[next_i][next_j]-'A'] = false;
			}
		
		}
	}
}

class Point{
	int i,j;
	Point(int i, int j){
		this.i = i;
		this.j = j;
	}
}
