package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2146_다리만들기
 * 메모리 : 21448KB
 * 시간 : 236ms
 * 길이 : 2989B
 * 풀이
 * 1. BFS
 * 2. 섬을 구분해주기, 다른섬까지의 최소거리 구하기 -> 2번의 BFS
 */

public class Main_B_G3_2146_다리만들기 {

	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static int MIN;

	// 상하좌우
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Point implements Comparable<Point>{
		int i, j, cnt;

		Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		MIN = Integer.MAX_VALUE;
		int cnt = 2; // 섬 번호
		visited = new boolean[N][N];
		// 같은 섬을 같은 번호로 표시하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					bfs(i, j, cnt); // 다리길이
					cnt++;
				}
			}
		}

		// 다리길이 만들기(다리길이 누적)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {	//섬이면 bfs시작
					// visited 초기화
					for (int v = 0; v < N; v++) {
						Arrays.fill(visited[v], false);
					}
					find(i, j, map[i][j]); // 섬 번호
				}
			}
		}
		System.out.println(MIN);

	}

	private static void find(int i, int j, int island) {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.add(new Point(i, j, 0));

		int num;
		boolean flag = false;
		
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			num = cur.cnt;
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + dir[d][0];
				int nj = cur.j + dir[d][1];

				if (ni < 0 || nj < 0 || ni >= N || nj >= N) {
					continue;
				}

				if (map[ni][nj] == 0 && !visited[ni][nj]) {	//바다면 다리길이 연장
					pq.add(new Point(ni, nj, num+1));
					visited[ni][nj] = true;
				}
				
				if(map[ni][nj] != 0 && map[ni][nj] != island) {	//다른 섬이면 최소값 비교한담에 return
					MIN = Math.min(MIN, num);
					//어느섬이던 만나기만 하면 return
					flag = true;
				}
			}
			
			if(flag)
				return;
		}
	}

	private static void bfs(int i, int j, int cnt) {

		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j, 0));
		map[i][j] = cnt;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ni = cur.i + dir[d][0];
				int nj = cur.j + dir[d][1];

				if (ni < 0 || nj < 0 || ni >= N || nj >= N) {
					continue;
				}

				if (map[ni][nj] == 1) {
					map[ni][nj] = cnt;
					queue.add(new Point(ni, nj, 0));
				}
			}
		}
	}

}
