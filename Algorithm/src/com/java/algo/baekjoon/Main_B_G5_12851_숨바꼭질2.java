package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 12851_숨바꼭질2
 * 메모리 : 76040KB
 * 시간 : 252ms
 * 코드길이 : 2251B
 * 풀이 
 * 1. 처음 들어왔을때는 fast_time에 넣어준다
 * 2. top-down이니까 depth인 time은 점점늘어나고, 들어올수있는건 같은 레벨에 있는 time밖에 없다는점을 이용
 *
 */

public class Main_B_G5_12851_숨바꼭질2 {

	static int N;
	static int K;
	static boolean visited[];
	static int time = 0;
	static int fast_time = 100000;
	static boolean isFirst = false;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];

		//n이 k보다 클때는 방법(result)는 1가지, fast_time도 한가지 밖에 안나온다
		if(N > K) {
			System.out.println(N-K);
			System.out.println(1);
			return;
		}
		
		//만약 수빈이 위치와 동생위치가 같으면 0
		if(N == K) {
			System.out.println(0);
			System.out.println(1);
			return;
		}
		
		
		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(N);
		
		int size, current, next = 0;

		// BFS
		while (!q.isEmpty()) {
			time++; // depth를 시간으로 생각
			// 같은 레벨에 있는 요소를 순회하기 위해
			size = q.size();
			while (size --> 0) {
				current = q.poll();
				
				//방문처리를 먼저 해줌
				visited[current] = true;
				
				
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
					
					if (next < 0 || next >= 100001) {
						continue;
					}

					if (next == K) { 	//처음 들어왔을 떄는 fast_time에 넣어주고 //처음 들어올때는 fast_time이 무조건 time보다 크다
						//top-down이니까 time은 점점늘어나고, 들어올수있는건 같은 레벨에 잇는 time밖에 없다
						if(fast_time >= time) {	
							fast_time = time;
							result++;
						}
						
					}
					
					// 이미 방문했으면 pass
					if (visited[next])
						continue;

					//visited처리돼서 재방문안하게됨
					q.offer(next);
				}
			}

		}
		
		System.out.println(fast_time);
		System.out.println(result);

	}
}