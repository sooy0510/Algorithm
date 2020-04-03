package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 9659_다항식계산
 * 메모리 : 23812KB
 * 시간 : 113ms
 * 길이 : 1671B
 * 풀이
 * 1. 페르마소정리 이용
 *
 */

public class Solution_D4_9659_다항식계산 {

	private static int N;
	private static int t,a,b;
	private static long[] func;
	private static int[][] info;
	private static final long P = 998244353;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());	//2 <= n <=50
			
			info = new int[N+1][3];
			func = new long[N+1];
			
			StringTokenizer st;
			for(int i=2; i<=N; i++) {	// 2 <= i <= N
				st = new StringTokenizer(br.readLine()," ");
				info[i][0] = Integer.parseInt(st.nextToken());	//t
				info[i][1] = Integer.parseInt(st.nextToken());	//a
				info[i][2] = Integer.parseInt(st.nextToken());	//b
			}
			
			//M
			long M = Long.parseLong(br.readLine());
			
			st = new StringTokenizer(br.readLine(), " ");
			sb.append("#"+test+" ");
			for(int j=1; j<=M; j++) {
				Arrays.fill(func, 0);
				func[0] = 1;
				
				int x = Integer.parseInt(st.nextToken());
				func[1] = x;
				makeFunc(x);
				
				long result = func[N]%P;
				sb.append(result+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void makeFunc(int x) {
		for(int i=2; i<=N; i++) {
			t = info[i][0];
			a = info[i][1];
			b = info[i][2];
			
			switch (t) {
			case 1:func[i] = (func[a]+func[b])%P;break;
			case 2:func[i] = (a * func[b])%P;break;
			case 3:func[i] = (func[a] * func[b])%P;break;
			}
		}
	}

}
