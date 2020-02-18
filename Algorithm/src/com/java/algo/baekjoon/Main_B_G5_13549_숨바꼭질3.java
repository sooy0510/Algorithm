package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_B_G5_13549_숨바꼭질3 {

	static int N;
	static int K;
	static boolean visited[];
	static int time = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];

		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(N);

		//만약 수빈이 위치와 동생위치가 같으면 0
		if(N == K) {
			System.out.println(0);
			return;
		}
		
		int size, current, next = 0;
		// BFS
		while (!q.isEmpty()) {
			time++; // depth를 시간으로 생각
			// 같은 레벨에 있는 요소를 순회하기 위해
			size = q.size();
			while (size --> 0) {
				current = q.poll();
				visited[current] = true;
				
				for (int i = 0; i < 3; i++) {
					switch (i) {
					case 0:	//*2 거치면 time--해줘야함
						next = current * 2;
						break;

					case 1:
						next = current - 1;
						break;

					case 2:
						next = current + 1;
						break;
					}
					
					if (next < 0 || next >= visited.length) {
						continue;
					}
					// 이미 방문했으면 pass
					if (visited[next])
						continue;
					
					if (next == K) {
						System.out.println(time);
						return;
					}

					q.offer(next);
				}
			}

		}

	}
}