package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S4_14501_퇴사_ing {

	static int[] res;	//예약
	static int[] mon;	//금액
	static boolean[] visited;
	static int N;
	
	static int[] subset;
	static int max = 0 ; //최대수익
	
	
	public static void main(String[] args) throws Exception {
		//상담 가능 일자 부분조합 모두 구하고, 금액 같이 구하기 => 큰 금액
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //상담가능 일수
		
		res = new int[N+1];
		mon = new int[N+1];
		
		visited = new boolean[N+1];
		
		//각 날마다 잡혀있는 예약
		for(int i=1; i<N+1; i++) {	
			st = new StringTokenizer(br.readLine());
			res[i] = Integer.parseInt(st.nextToken());
			mon[i] = Integer.parseInt(st.nextToken());
		}
		
		// getnerateSubset(처리할 날짜, 총 금액)
		generateSubset(1,0);
		System.out.println(max);
	}


	private static void generateSubset(int day, int m_sum) {
		
		if(visited[day])
			return;
		
//		if(day > N || (day == N && res[day] != 1)) {
//			for(int s=1; s<=N; s++) {
//				if(visited[s]) System.out.print(s+" ");
//			}
//			System.out.println();
//
//			max = Math.max(m_sum, max);
//			return;
//		}
		
		if(day == N && res[day] == 1) {
			visited[day] = true;
//			for(int s=1; s<=N; s++) {
//				if(visited[s]) System.out.print(s+" ");
//			}
//			System.out.println();
			
			m_sum += mon[day]; 
			max = Math.max(m_sum, max);
			return;
		}
		
		int next_day = day + res[day];
		if(next_day > N) {
			for(int s=1; s<=N; s++) {
				if(visited[s]) System.out.print(s+" ");
			}
			System.out.println();
			
			max = Math.max(m_sum, max);
			return;
		}else {
			for(int t=next_day; t<=N; t++) {
				if(t > N) {
					//오늘자 선택안함
//					for(int s=1; s<=N; s++) {
//						if(visited[s]) System.out.print(s+" ");
//					}
//					System.out.println();
					max = Math.max(m_sum, max);
					
					return;
				}else {
					visited[day] = true;
					//m_sum += mon[day];
					generateSubset(t, m_sum+mon[day]);
					visited[day] = false;
					generateSubset(t, m_sum);
				}
			}
		}
		
		
	}
		
		

}
