package com.java.algo.baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * 13913_숨바꼭질4(2차)
 * 메모리 : 34220KB
 * 시간 : 520ms
 * 길이 : 1738B
 *
 */

public class Main_B_G4_13913_숨바꼭질4_2 {

	private static int N,K;
	private static int[] memo;
	private static int time;
	static boolean visited[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		time = 0;
		visited = new boolean[100001];
		memo = new int[100001];
		
		//동생이 수빈이보다 앞에있을떄는 x-1밖에 못함
		if(K < N) {
			System.out.println(N-K);
			for(int i=N; i>=K; i--) {
				System.out.print(i+" ");
			}
			return;
		}
		
		//동생이랑 위치가 똑같을때
		if(N == K) {
			System.out.println(0);
			System.out.println(K);
			return;
		}
		
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(N);
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			while(size-->0) {
				int now = q.poll();
				visited[now] = true;
				
				for(int i=0; i<3; i++) {
					int next=0;
					switch (i) {
					case 0:	//순간이동
						next = 2*now;
						break;
						
					case 1:	//x-1
						next = now-1;
						break;

					case 2:	//x+1
						next = now+1;
						break;
					}
					
					//범위벗어나면
					if(next < 0 || next > 100000) {
						continue;
					}
					
					if(visited[next])
						continue;
					
					//이미 메모된게 있으면
					if(memo[next] != 0) {
						continue;
					}
					
					if(next == K) {
						System.out.println(time);
						memo[next] = now;
						find(next);
						return;
					}
					
					memo[next] = now;
					q.offer(next);
					
				}
			}
		}
		
	}

	private static void find(int k) {
		if(k == N) {
			System.out.print(k+" ");
			return;
		}
		find(memo[k]);
		System.out.print(k+" ");
	}

}
