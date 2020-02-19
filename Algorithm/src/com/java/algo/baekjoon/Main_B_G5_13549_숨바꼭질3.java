package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 13549_숨바꼭질3
 * 메모리 : 16756KB
 * 시간 : 104ms
 * 코드길이 : 2017B
 * 풀이
 * 1. 걸린시간을 저장하는 timetable 배열을 만든다
 * 2. ex) 5 17 
 * timetable[5] = 0
 * timetable[10] = timetable[5](이전까지의 시간) + 0 or 1
 *
 */

public class Main_B_G5_13549_숨바꼭질3 {

	static int N;
	static int K;
	static boolean visited[];
	static int timetable[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];
		timetable = new int[100001];


		//n이 k보다 클때는 방법(result)는 1가지, fast_time도 한가지 밖에 안나온다
		if(N > K) {
			System.out.println(N-K);
			return;
		}
		
		//만약 수빈이 위치와 동생위치가 같으면 0
		if(N == K) {
			System.out.println(0);
			return;
		}
		
		
		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(N);
		
		int size, current, next = 0;
		// BFS
		while (!q.isEmpty()) {
			// 같은 레벨에 있는 요소를 순회하기 위해
			size = q.size();
			while (size --> 0) {
				current = q.poll();
				visited[current] = true;
				int plus = 0;
				
				for (int i = 0; i < 3; i++) {
					switch (i) {
					case 0:	//*2 거치면 time--해줘야함
						next = current * 2;
						plus = 0;
						break;

					case 1:
						next = current - 1;
						plus = 1;
						break;

					case 2:
						next = current + 1;
						plus = 1;
						break;
					}
					
					if (next < 0 || next >= visited.length) {
						continue;
					}
					// 이미 방문했으면 pass
					if (visited[next])
						continue;
					
					//timetable에 값이 있으면 pass, 체크 안해주면 next가 중복될때마다 plus가 더해진다
					if(timetable[next] != 0) {
						continue;
					}
					
					if (next == K) {
						System.out.println(timetable[current]+plus);
						return;
					}

					timetable[next] = timetable[current]+plus;
					//System.out.println("next : ["+ next + "] : "+timetable[next]);
					q.offer(next);
				}
			}
		}

	}
}