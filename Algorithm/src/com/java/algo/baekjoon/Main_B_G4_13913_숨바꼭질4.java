package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 13913_숨바꼭질4
 * 메모리 : 32336KB
 * 시간 : 476ms
 * 코드길이 : 2309B
 * 풀이
 * 1. 이동경로를 메모해가며 탐색했다
 * - ex) 5 -> 10 -> 9 -> 18 -> 17 의 경로로 이동한다고 했을때
 * roottable의 다음(next)인덱스에 현재(current)인덱스를 저장해두는 식
 * - 최소시간을 찾으면 재귀로 경로(index)를 출력해주면 된다
 *
 */

public class Main_B_G5_13913_숨바꼭질4 {

	static int N;
	static int K;
	static boolean visited[];
	static int roottable[];
	static int time = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];
		roottable = new int[100001];


		//n이 k보다 클때는 최소시간은 0, 이동루트는 K to N
		if(N > K) {
			System.out.println(N-K);

			for(int i=N; i>=K; i--) {
				System.out.print(i+" ");
			}
			return;
		}
		
		//N=K이면 최소시간은 0, 이동루트는 K K
		if(N == K) {
			System.out.println(0);
			System.out.println(K);
			return;
		}
		
		
		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(N);
		
		int size, current, next = 0;
		// BFS
		while (!q.isEmpty()) {
			time++;
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
					
					//timetable에 값이 있으면 pass, 체크 안해주면 next가 중복될때마다 plus가 더해진다
					if(roottable[next] != 0) {
						continue;
					}
					
					if (next == K) {
						System.out.println(time);
						roottable[K] = current;
						getRoot(K);
						return;
					}

					roottable[next] = current;
					//System.out.println("next : ["+ next + "] : "+timetable[next]);
					q.offer(next);
				}
			}
		}

	}

	private static void getRoot(int k) {
		//인덱스를 출력
		if(k == N) {
			System.out.print(k+" ");
			return;
		}
		
		getRoot(roottable[k]);
		System.out.print(k+" ");
	}
}