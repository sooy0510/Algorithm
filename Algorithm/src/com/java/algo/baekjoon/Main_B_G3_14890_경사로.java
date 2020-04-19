package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 14890_경사로
 * 메모리 : 14890KB
 * 시간 : 96ms
 * 길이 : 3011B
 * 풀이
 * 1. 시뮬레이션
 * 2. 노가다..?
 */

public class Main_B_G3_14890_경사로 {

	private static int N,L;
	private static int[][] map;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken()); //2<= N <=100
		L = Integer.parseInt(st.nextToken()); //1<= L <=N
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//가로줄
		int cur;
		int len;
		boolean[] visited = new boolean[N];
		boolean isOk;
		int result = 0;
		for (int i = 0; i < N; i++) {
			cur = map[i][0];
			len = 0;
			isOk = false;
			Arrays.fill(visited, false);
			for (int j = 0; j < N; j++) {
				if(visited[j]) continue;
				
				if(cur == map[i][j]) {
					visited[j] = true;
					len++;
					isOk = true;
				}else if(map[i][j] - cur == 1) {	// 오르막길
					if(len < L) {
						isOk = false;
						break;
					}
					visited[j] = true;
					cur = map[i][j];
					len = 1;
					isOk = true;
				}else if(cur - map[i][j] == 1) {	// 내리막길\
					//앞으로의 L만큼 검사해서 경사로 만들어지는지 확인
					if(j + L > N) {	//범위초과
						visited[j] = true;
						isOk = false;
						break;
					}
					
					for(int l=0; l<L; l++) {
						visited[j+l] = true;
						if(map[i][j+l] != cur-1) { //틀리면 통로 못됨
							isOk = false;
							break;
						}
					}
					if(!isOk) break;

					cur = cur-1; //=cur-1
					len = 0;
					isOk = true;
					
				}else {	//높이차가 1이 아님 ->  경사로 불가
					isOk = false;
					break;
				}
			}
			if(isOk)result++;
		}
		
		
		//세로줄
		for (int j = 0; j < N; j++) {
			cur = map[0][j];
			len = 0;
			isOk = false;
			Arrays.fill(visited, false);
			for (int i = 0; i < N; i++) {
				if(visited[i]) continue;
				
				if(cur == map[i][j]) {
					visited[i] = true;
					len++;
					isOk = true;
				}else if(map[i][j] - cur == 1) {	// 오르막길
					if(len < L) {
						isOk = false;
						break;
					}
					visited[i] = true;
					cur = map[i][j];
					len = 1;
					isOk = true;
				}else if(cur - map[i][j] == 1) {	// 내리막길\
					//앞으로의 L만큼 검사해서 경사로 만들어지는지 확인
					if(i + L > N) {	//범위초과
						visited[i] = true;
						isOk = false;
						break;
					}
					
					for(int l=0; l<L; l++) {
						visited[i+l] = true;
						if(map[i+l][j] != cur-1) { //틀리면 통로 못됨
							isOk = false;
							break;
						}
					}
					if(!isOk) break;

					cur = cur-1; //=cur-1
					len = 0;
					isOk = true;
					
				}else {	//높이차가 1이 아님 ->  경사로 불가
					isOk = false;
					break;
				}
			}
			if(isOk)result++;
			//System.out.println(Arrays.toString(visited));
		}
		System.out.println(result);
	}

}
