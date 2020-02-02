package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G5_15684_사다리조작 {

	static boolean[][] ladder;
	static int N;	//세로줄
	static int M;	//가로줄
	static int H;
	static int ans=4;
	

	private static void move(int x, int y, int cnt) {
		if(x==M) {
			++y;
			x=1;
		}
		
//		if(check()){
//			ans = Math.min(ans, cnt);
//			System.out.println(ans);
//		}
		
		if((y==N && x==M) || cnt ==3) {
			if(check()) {
				System.out.println("checking");
				ans = Math.min(ans, cnt);
				System.out.println(ans);
			}
			return;
		}
		
		if(ladder[x][y] || ladder[x][y-1] || ladder[x][y+1]) return;
	
		
		// 이미 다리가 있거나, 인접한 가로선이 있을 때
		//if(y==M || ladder[x][y] || (y!=1 && ladder[x][y-1]) || ladder[x][y+1]) return;
		//if(ladder[x][y] || ladder[x][y-1] || ladder[x][y+1]) return;
		
		
		// 설치 안하거나
		move(x+1, y, cnt);
		// 설치하거나
		ladder[x][y] = true;
		move(x+1, y, cnt+1);
		ladder[x][y] = false;
		
	}

	private static boolean check() {
		System.out.println("들어왓슈");
		
		for(int i=1; i<=N; i++) {	//각 세로줄 순회
			int start = i, end = i;
			for(int j=1; j<=M; j++) {	//사다리타기
				if(end == 1 || end==N)continue;
				
				//오른쪽
				if(ladder[j][end]) {
					++end;
					continue;
				}
				
				//왼쪽
				if(!ladder[j][end]) {
					if(ladder[j][end-1]) --end;
				}
			}
			if(start !=end)return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ladder = new boolean[H+2][N+2];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			ladder[x][y] = true;
			//ladder[y][x+1] = true;
		}
		
//		for (int i = 0; i < H+2; i++) {
//			for (int j = 0; j < N+2; j++) {
//				System.out.print(ladder[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		move(1,1,0);
		
	}

}
