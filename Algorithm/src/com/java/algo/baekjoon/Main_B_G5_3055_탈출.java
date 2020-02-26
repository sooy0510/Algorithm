package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 3055_탈출
 * 메모리 : 13256KB
 * 시간 : 80ms
 * 코드길이 : 3173B
 * 풀이
 * 1. BFS
 * 2. 물이 다음시간에 차면 못가기 때문에 어떻게 처리하지 햇는데 물을 먼저 채우면 해결되는 문제였다
 *
 */

public class Main_B_G5_3055_탈출 {

	static int R,C;
	static char[][] map;
	static Queue<Point3> queue_w = new LinkedList<Point3>();
	static Queue<Point3> queue_k = new LinkedList<Point3>();
	static boolean visited_w[][];
	static boolean visited_k[][];
	static int result=0;
	static boolean isStop;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited_w = new boolean[R][C];
		visited_k = new boolean[R][C];
		char input;
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				input = (char)str.charAt(j);
				map[i][j] = input;
				if(!visited_w[i][j] && input == '*') {
					visited_w[i][j] = true;
					queue_w.add(new Point3(i,j));
				}
				else if(!visited_k[i][j] && input == 'S') {
					visited_k[i][j] = true;
					queue_k.add(new Point3(i,j));
				}
			}
		}
		
		bfs();
		if(isStop) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
	}

	//상하우좌, 대각선
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,1,-1};
	
	private static void bfs() {
		while(!queue_k.isEmpty()) {
			int size = queue_k.size();
			result++;
			isStop = false;
			
			
			int w_size = queue_w.size();
			while(w_size --> 0) {
				Point3 water = queue_w.poll();
				
				for(int d=0; d<4; d++) {
					int next_i = water.i + di[d]; 
					int next_j = water.j + dj[d]; 
					
					if(next_i<0 || next_i >= R || next_j<0 || next_j >= C)
						continue;
					
					if(map[next_i][next_j] == 'X')
						continue;
					
					//비버굴은 못감
					if(map[next_i][next_j] == 'D')
						continue;
					
					if(map[next_i][next_j] == '*')
						continue;
					
					if(!visited_w[next_i][next_j]) {
						visited_w[next_i][next_j] = true;
						map[next_i][next_j] = '*';
						queue_w.add(new Point3(next_i, next_j));
					}
				}
			}
			
			while(size --> 0) {
				Point3 kaktus = queue_k.poll();
				int cnt = 0;
				
				for(int d=0; d<4; d++) {
					int next_ki = kaktus.i + di[d]; 
					int next_kj = kaktus.j + dj[d]; 
					
					if(next_ki<0 || next_ki >= R || next_kj<0 || next_kj >= C) {
						cnt++;
						continue;
					}
					
					
					//돌만날때, 물만날때
					if(map[next_ki][next_kj] == 'X' || map[next_ki][next_kj] == '*') {
						cnt++;
						continue;
					}
					
					
					if(map[next_ki][next_kj] == 'D') {
						isStop = false;
						return;
					}
					
					if(!visited_k[next_ki][next_kj]) {
						visited_k[next_ki][next_kj] = true;
						map[next_ki][next_kj] = 'S';
						map[kaktus.i][kaktus.j] = '.';
						queue_k.add(new Point3(next_ki, next_kj));					
					}else {
						cnt++;
					}
				}
				
				if(cnt == 4) {
					isStop = true;
				}
				
			}
			
			
//			System.out.println(result);
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print((char)map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			
		}
	}

}

class Point3{
	int i,j;
	Point3(int i, int j){
		this.i = i;
		this.j = j;
	}
}
