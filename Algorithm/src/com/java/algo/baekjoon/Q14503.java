package com.java.algo.baekjoon;

import java.util.Scanner;

public class Q14503 {
	
	static int[][] arr;
	static int cnt;
	
	static int robot_x;
	static int robot_y;
	
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();

		arr = new int[N][M];
		
		robot_x = sc.nextInt();
		robot_y = sc.nextInt();
		int p = sc.nextInt(); // 0:북, 1:동, 2:남, 3:서
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		while(true) {
			p = print4(robot_x, robot_y, p);
			if(p == 5) {
				System.out.println(cnt);
				break;
			}
		}
		
	}

	private static int print4(int i, int j, int p) {
		// 3:서, 0:북, 1:동, 2:남
		int pp = (p+4)%4;
		int c=0;
		
		int[] dy4 = {0,1,0,-1};
		int[] dx4 = {-1,0,1,0};
		
		for(int k=0; k<4; k++) {
			int nexty = i + dy4[k];
			int nextx = j + dx4[k];
			if(nexty >= arr.length || nexty<0 || nextx >= arr.length || nextx<0) {
				continue;
			}
			if(arr[nexty][nextx] == 1) {
				c++;
			}else {	//0이면
				arr[nexty][nextx] = 1;
				cnt++;
			}
		}
		
		if(c == 4) {	//뒤로 후진
			int nexty = i + dy4[(p+3)%4];
			int nextx = j + dx4[(p+3)%4];
			
			if(arr[robot_x][robot_y] == 1) {	// 종료
				System.out.println("종료");
				pp = 5;
			}else {
				robot_x = nexty;
				robot_y = nextx;
				cnt++;
			}
		}
		
		return pp;
	}
}
