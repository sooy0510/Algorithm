package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 14889_스타트와링크
 * 메모리 : 16916KB
 * 시간 : 228ms
 * 길이 : 1651B
 * 풀이
 * 1. 조합
 * 2. 예전에 풀었을때 팀나누고 점수계산할때도 조합써서 어렵게 푸느라 포기했던 기억이 나는데
 * 왜 어렵게 생각했을까 싶다. 
 */

public class Main_B_S3_14889_스타트와링크 {
	
	private static int N;
	private static int[][] power;
	private static int[] team_a, team_b;
	private static boolean[] selected;
	private static int MIN;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		MIN = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		
		power = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		team_a = new int[N/2];
		team_b = new int[N/2];
		
		selected = new boolean[N];
		
		//조합
		comb(0,0);
		
		System.out.println(MIN);
	}

	private static void comb(int index, int count) {
		
		if(count == N/2) {
			//team_b구하기
			int idx = 0;
			for(int i=0; i<N; i++) {
				if(!selected[i]) {
					team_b[idx++] = i;
				}
			}

			//능력치 차이 구하기
			int power_a = getPower(team_a);
			int power_b = getPower(team_b);
			
			MIN = Math.min(MIN, Math.abs(power_a - power_b));
			
			return;
		}
		
		for(int i=index; i<N; i++) {
			team_a[count] = i;
			selected[i] = true;
			comb(i+1, count+1);
			selected[i] = false;
		}
	}

	private static int getPower(int[] team) {
		int p = 0;
		
		for (int i = 0; i < team.length-1; i++) {
			for (int j = i+1; j < team.length; j++) {
				p += power[team[i]][team[j]];
				p += power[team[j]][team[i]];
			}
		}
		
		return p;
	}
}

