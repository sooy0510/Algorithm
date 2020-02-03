package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 백준_G5_15684_사다리조작
 * 메모리 : 15488KB
 * 시간 : 1956ms
 * 코드길이 : 1571B
 * 
 */

public class Main_B_G5_15684_사다리조작 {

	static int N,M,H;
	static boolean[][] ladder;
	static int min = 4;
	
	
	//i번 세로선의 결과가 i번이 나오는지 확인하는 메서드
	private static boolean check() {
		for(int x=1; x<=N; x++) {
			int end_x = x;
			for(int y=1; y<=H; y++) {
				// end_x열과 end_x+1이 이어져 있으면 ladder[y][end_x]는 true이다
				if(ladder[y][end_x]) {
					end_x += 1;
				}else if(ladder[y][end_x-1]){
					end_x -= 1;
				}
			}
			
			//i번 세로선결과가 i아니면 return false
			if(end_x != x)
				return false;
		}
		
		return true;
	}
	
	
	//x(열방향)에 가로선 그리면서 탐색
	private static void search(int cnt, int y) {
		
		//기저조건
		if(cnt > 3) {
			return;
		}
		
		
		//i번 세로선의 결과가 i번이 나오는지 체크
		if(check()) {
			// 최소값구하기
			min = Math.min(cnt, min);
			return;
		}
		
		
		
		
		//행단위로 탐색
		for(int i=y; i<=H; i++) {
			for(int j=1; j<=N; j++) {
				
				//가로선 그리려는 곳에 이미 가로선 있으면 가로선 불가
				if(ladder[i][j] || ladder[i][j-1] || ladder[i][j+1])
					continue;
				
				// 가로선 그리기
				ladder[i][j] = true;
				
				// 가로선 개수 +1해주고 계속 탐색
				search(cnt+1, i);
				
				// 다른 재귀를 위해서 false로 초기화해주기
				ladder[i][j] = false;
			}
		}
	}


	public static void main(String[] args) throws Exception {
		//N, M, H받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		//배열 만들기, 배열의 최대크기
		//ladder = new boolean[32][11];
		ladder = new boolean[H+2][N+2];
			
		//가로선 들어오는 곳마다 true
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			ladder[y][x] = true;
		}
		
		//재귀 시작 search(cnt: 가로선개수, y좌표 시작위치);
		search(0,1);
		
		//3넘거나 불가능하면 -1출력
		if(min == 4) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
		
	}
}
