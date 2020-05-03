package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 5658_보물상자 비밀번호
 * 메모리 : 28420KB
 * 시간 : 114ms
 * 길이 : 1462B
 * 풀이
 * 1. Collection 오츰차순 정렬 : Collections.sort(list, Collections.reverseOrder())
 * 2. 16진수 문자열 10진수 변환 : Integer.parseInt(list.get(K-1),16)
 */

public class Solution_D_5658_보물상자비밀번호 {

	private static int N,K;
	private static String input;
	private static ArrayList<String> list;
	private static char[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			input = br.readLine();
			arr = input.toCharArray();
			
			list = new ArrayList<String>();
			
			char temp;
			String num;
			
			for(int i=0; i<N/4; i++) { //회전
				for(int j=0; j<N; j = j+N/4) { //한변
					num = "";
					for(int k=0; k<N/4; k++) {
						num += arr[j+k];
					}
					
					if(!list.contains(num)) {
						list.add(num);
					}
				}
				
				//한칸씩 회전
				temp = arr[N-1];
				for(int j=N-1; j>=1; j--) {
					arr[j] = arr[j-1];
				}
				arr[0] = temp;
			}
			
			//오름차순 정렬
			Collections.sort(list, Collections.reverseOrder());
			
			//K번째로 큰수 16진수로 계산
			sb.append("#"+test+" "+Integer.parseInt(list.get(K-1),16)+"\n");
		}
		System.out.println(sb.toString());
	}
}
