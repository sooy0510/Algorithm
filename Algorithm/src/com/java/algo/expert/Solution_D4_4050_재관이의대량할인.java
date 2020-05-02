package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 4050_재관이의대량할인
 * 메모리 : 99128KB
 * 실행시간 : 334ms
 * 길이 : 1233B
 * 풀이
 * 1. Greedy?
 * 2. 처음에 내림차순으로 정렬한뒤 3벌중 가장 저렬한 옷 제외하고 모두 더했는데, 이렇게 하면 마지막에 1벌 또는 2벌이 남았을때도 할인이 들어가는 경우가 있었다
 * => 오름차순으로 수정
 */

public class Solution_D4_4050_재관이의대량할인 {
	private static int N;
	private static int result;
	private static ArrayList<Integer> clothes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			
			clothes = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i=0; i<N; i++) {
				clothes.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(clothes, new Comparator<Integer>() {

				//오름차순
				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o2, o1);
				}
			});
			
			result = 0;
			
			for(int i=0; i<N; i++) {
				if((i+1)%3 == 0) continue;	//제일 저렴한 옷 제거
				result += clothes.get(i);
			}
			
			
			sb.append("#"+test+" "+result+"\n");
		}
		System.out.println(sb);
	}
}
