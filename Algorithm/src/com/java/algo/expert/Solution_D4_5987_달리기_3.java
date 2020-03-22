package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 5987_달리기(3차)
 * 메모리 :65568LB
 * 시간 : 172ms
 * 길이 : 1470B
 *
 */

public class Solution_D4_5987_달리기_3 {

	private static int N,M;
	private static int[] input;
	private static long[] memo;  

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			input = new int[N];	//선수 수만큼
			memo = new long[1<<N];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken())-1;
				int y = Integer.parseInt(st.nextToken())-1;
				//x가 y보다 먼저 들어옴, input[x]에 자기보다 늦게 들어온 플레이어들 표시
				input[x] |= 1<<y;
			}
			
			long result = solve(0);
			sb.append("#"+test+" "+result+"\n");
		}
		System.out.println(sb);
	}

	private static long solve(int flag) {
		
		if(flag == (1<<N)-1) {	//선수들의 모두 순서를 구했을때
			return 1;
		}
		
		if(memo[flag] > 0) {	//memoi
			return memo[flag];
		}
		
		for(int i=0; i<N; i++) {
			if((flag & 1<<i) == 0) {	//아직 방문 안했으면
				if((flag & input[i]) == input[i]) {	
					//예를들어 input[i] =0의 의미는 i보다 늦게 들어온 선수가 없다는 것을 뜻함, 
					//그렇기때문에 이자리(flag)에 본인(i)이 들어갈수 있고,
					//flag & 1<<i를 해줌으로써 다음순서를 구할수있도록 한다
					//=> 자기보다 늦은 선수들이 순서에 모두 반영되었다면 memoi
					memo[flag] += solve(flag | 1<<i);
				}
			}
		}
		return memo[flag];
	}
}
