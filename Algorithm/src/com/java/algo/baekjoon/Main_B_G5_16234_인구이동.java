package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 16234_인구이동
 * 메모리 : 28168KB
 * 시간 : 352ms
 * 길이 : 2450B
 * 풀이
 * 1. DFS 
 * 2. HT코드 참조
 */

public class Main_B_G5_16234_인구이동 {

	private static int N,L,R;
	private static int[][] map;
	private static boolean[][] visited;
	private static boolean[][] newVisited;

	//상하좌우
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int count;	//이동한 횟수
	private static int sum, result, change;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		count = 0;
		sum = 0;
		result = 0;
		change = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			int temp = change;
			visited = new boolean[N][N];
			newVisited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (newVisited[i][j] == true) {
						continue;
					}
					sum = 0;
					count = 0;
					solve(i, j);
					if (count <= 1) { // 하나만 방문했을때 맵리셋 필요 x
						visited[i][j] = newVisited[i][j];
					} else {
						resetMap();
					}
				}
			}
			
			result++;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j]+" ");
//				}System.out.println();
//			}
			if(temp == change) break;
		}

		System.out.println(result - 1);
	}

	// dfs해서 true로 만듬 - true로 바뀌는 경우가 없을때 끝!
	static void solve(int x, int y) {
		newVisited[x][y] = true;
		sum += map[x][y];
		count++;
		for (int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
				continue;
			if (newVisited[nx][ny] == true)
				continue;
			int temp = Math.abs(map[x][y] - map[nx][ny]);
			if (temp >= L && temp <= R) {
				solve(nx, ny);
			}

		}

	}

	// true인걸 골라서 sum 구하고 다시 넣어주기
	static void resetMap() {
		int avg = sum / count;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] != newVisited[i][j]) {
					map[i][j] = avg;
					change++;
				}
				visited[i][j] = newVisited[i][j];
			}
		}
	}
}

