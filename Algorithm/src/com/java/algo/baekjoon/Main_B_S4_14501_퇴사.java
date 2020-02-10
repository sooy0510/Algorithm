package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S4_14501_퇴사 {

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
		generateSubset(1,mon[1]);
		System.out.println(max);
	}


	private static void generateSubset(int day, int m_sum) {
		//마지막 날이고, 상담기간도 1일이면 방문하고 금액계산
		if(day == N && res[day] ==1) {
			visited[day] = true;
			if(m_sum <= max)return;
//			for(int i=1; i<= N; i++) {
//				if(visited[i]) {
//					System.out.print(i+" ");
//				}
//			}
//			System.out.println();
			//return;
			max = Math.max(m_sum, max);
			return;
		}
		
		
//		//상담가능한 날 이상이면 방문하지 않고 금액계산
//		if(day >= N) {
//			if(m_sum <= max)return;
//			for(int i=1; i<= N; i++) {
//				if(visited[i]) {
//					System.out.print(i+" ");
//				}
//			}
//			System.out.println();
//			//return;
//			max = Math.max(m_sum, max);
//			return;
//		}
		
		int next_day = day + res[day];	//그 다음 가능한 날짜
		
		// 다음날짜가 상담가능날짜 이상이면 지금까지만 방문하고 금액계산
		if(next_day > N) {
			//안되면 방문 x
			if(m_sum <= max)return;
			for(int i=1; i<= N; i++) {
				if(visited[i]) {
					System.out.print(i+" ");
				}
			}
			System.out.println();
			max = Math.max(m_sum, max);
			return;
		}
		
//		if(next_day == N && res[next_day] ==1) {
//			visited[day] = true;
//			if(m_sum <= max)return;
//			for(int i=1; i<= N; i++) {
//				if(visited[i]) {
//					System.out.print(i+" ");
//				}
//			}
//			System.out.println();
//			//return;
//			max = Math.max(m_sum, max);
//			return;
//		}
		
		visited[day] = true;
		generateSubset(next_day, m_sum+mon[next_day]);
		visited[day] = false;
		//generateSubset(next_day, m_sum);
	}
		
		

}
