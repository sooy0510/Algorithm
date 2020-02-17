package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_12851_숨바꼭질2 {

	static int N;
	static int K;
	static boolean visited[];
	static int time = 0;
	static int fast_time = 0;
	static boolean isFirst = false;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];

		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(N);
		visited[N] = true;

		//만약 수빈이 위치와 동생위치가 같으면 0
		
		int size, current, next = 0;

		// BFS
		while (!q.isEmpty()) {
			time++; // depth를 시간으로 생각
			// 같은 레벨에 있는 요소를 순회하기 위해
			size = q.size();
			while (size --> 0) {
				current = q.poll();
				for (int i = 0; i < 3; i++) {
					switch (i) {
					case 0:
						next = current * 2;
						break;

					case 1:
						next = current - 1;
						break;

					case 2:
						next = current + 1;
						break;
					}

					// 범위안에 들고, 동생 찾앗으면
					if (next < 0 || next >= visited.length) {
						continue;
					}

					// 이미 방문했으면 pass
					if (visited[next])
						continue;

					if (next == K) {
						//같은 레벨(최소 거리 같을떄만)count올려줘야함
						//처음 들어왔을 떄는 fast_time에 넣어주고
						if(!isFirst) {
							fast_time = time;
							isFirst = true;
							result++;
							continue;
						}else {
							if(time == fast_time) {
								result++;
							}
						}
						
					}
					
					visited[next] = true;
					q.offer(next);
				}
			}

		}
		
		System.out.println(fast_time);
		if(fast_time == 0) {
			System.out.println(1);
		}else {
			System.out.println(result);
		}

	}
}