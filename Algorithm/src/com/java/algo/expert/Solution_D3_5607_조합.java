package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 5607_조합
 * 메모리 : 33804KB
 * 시간 : 134ms
 * 길이 : 1173B
 * 풀이
 * 1. 페르마의 소정리 [https://cru6548.tistory.com/23]참고
 * 2. 분할정복 -> 새로 알아감!
 *
 */

public class Solution_D3_5607_조합 {

	private static long P = 1234567891;
	private static int N,R;
	private static long[] fact = new long[1000001];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		//팩토리얼값 저장
		fact[0] = 1;
		for(int i=1; i<1000001; i++) {
			fact[i] = (fact[i-1]*i)%P;
		}
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			R= Integer.parseInt(st.nextToken());
			
			long A = fact[N]%P;
			long B = pow(fact[R] * fact[N-R] % P, P-2);
			sb.append("#"+test+" "+(A*B)%P+"\n");
		}
		System.out.println(sb);
	}

	private static long pow(long n, long p) {	//n : 밑수, p : 제곱값
		if(p == 0)return 1;
		
		//분할정복, 반반 나눠서 구해주기
		long res = (pow(n, p/2)) % P;
		res = (res * res) % P;
		
		if(p % 2 != 0) {	//홀수면 한 번 더 곱해줌
			res = (res * n) % P;
		}
		return res;
	}
}
