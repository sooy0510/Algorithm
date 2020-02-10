package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 4408_자기방으로 돌아가기
 * 메모리 : 21340KB
 * 실행시간 : 107ms
 * 코드길이 : 1320
 * 풀이
 * 1. 마주보는 방(홀수방, 짝수방)을 같은 index로 생각하고 배열의 크기를 200으로 잡는다
 * 2. 출발점이 도착점보다 클때 출발점과 도착점을 바꿔준다
 * 3. 홀수일 때, 짝수일 떄 나눠서 해당되는 배열의 index값을 +1해준다
 * 4. 정렬후, 가장 큰 값 가져오기
 * => 동선이 누적된 값이므로, 전체 범위에서 가장큰 수가 조건에 맞는 최소 이동수다
 *
 */

public class Solution_D4_4408_자기방으로돌아가기 {

	static BufferedReader br;
	static StringTokenizer st;
	static int[] arr;	
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int test=1; test<=T; test++) {
			int N = Integer.parseInt(br.readLine().trim());
			
			arr = new int[200];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				int temp;
				
				if(start > end) {
					temp = start;
					start = end;
					end = temp;
				}
				
				//홀수 : n/2, 짝수 : n/2-1
				if(start%2 == 1) {
					start = start/2;
				}else {
					start = start/2-1;
				}
				
				if(end%2 == 1) {
					end = end/2;
				}else {
					end = end/2-1;
				}
				
				for(int j=start; j<=end; j++) {
					arr[j]++;
				}
				
			}
			
			Arrays.sort(arr);
			
			sb.append("#"+test+" "+arr[199]+"\n");		
		}
		
		System.out.println(sb);
	}

}

