package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 4012_요리사
 * 메모리 : 37580KB -> 33456KB
 * 시간 : 211ms -> 161ms
 * 풀이
 * 1. 팀나누기, 시너지구할 2개재료 고르기 => 조합
 * 2. 같은 재료끼리는 어차피 시너지 0이니까 이중포문으로 시너지 구하기 -> 시간감소
 *
 */

public class Solution_D_4012_요리사 {

	private static int N;
	private static int[][] synergy;
	private static int[] dish1;
	private static int[] dish2;
	private static boolean[] selected;
	static int MIN;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		//조합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			synergy = new int[N][N];
			selected = new boolean[N];
			MIN = Integer.MAX_VALUE;
			dish1 = new int[N/2];
			dish2 = new int[N/2];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//조합
			combination(0,0);
			
			sb.append("#").append(test).append(" ").append(MIN).append("\n");
		}
		System.out.println(sb);
	}

	private static void combination(int index, int count) {
		if(count == N/2) {
			
			int idx = 0;
			for(int i=0; i<N; i++) {
				if(!selected[i]) {
					dish2[idx++] = i;
				}
			}
			
			int syn1 = 0; int syn2 = 0; int n1; int n2;
			
			//getFlavor1(new int[2], 0, 0);
			//getFlavor2(new int[2], 0, 0);
			
			
			//어차피 같은 재료 끼리는 시너지 0
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<N/2; j++) {
					n1 = dish1[i];
					n2 = dish1[j];
					syn1 += synergy[n1][n2];
				}
			}
			
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<N/2; j++) {
					n1 = dish2[i];
					n2 = dish2[j];
					syn2 += synergy[n1][n2];
				}
			}
			
			int diff = Math.abs(syn1 - syn2);
			
			
			if(diff < MIN) {
				MIN = diff;
				return;
			}
			
			return;
		}
		
		for(int i=index; i<N; i++) {
			if(selected[i])
				continue;
			
			dish1[count] = i;
			selected[i] = true;
			combination(i+1, count+1);
			selected[i] = false;
		}
	}

//	private static void getFlavor2(int[] temp, int index, int count) {
//		if(count == 2) {
//			syn2 += synergy[temp[0]][temp[1]];
//			syn2 += synergy[temp[1]][temp[0]];
//			return;
//		}
//		
//		for(int i=index; i<N/2; i++) {
//			temp[count] = dish2[i];
//			getFlavor2(temp, i+1, count+1);
//		}
//		
//	}
//
//	//같은 팀에서 시너지 구할 두개의 원소 구하기
//	private static void getFlavor1(int[] temp, int index, int count) {
//		
//		if(count == 2) {
//			syn1 += synergy[temp[0]][temp[1]];
//			syn1 += synergy[temp[1]][temp[0]];
//			return;
//		}
//		
//		for(int i=index; i<N/2; i++) {
//			temp[count] = dish1[i];
//			getFlavor1(temp, i+1, count+1);
//		}
//		
//	}

}
