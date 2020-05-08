package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 6987_월드컵(2차)
 * 메모리 : 13604KB
 * 시간 : 76ms
 * 길이 : 1844B
 * 풀이
 * 1. dfs
 */

public class Main_B_S3_6987_월드컵_2 {

	private static int[][] score;
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		score = new int[4][18];	//4가지 케이스, 18개의 score 결과
		
		for(int t=0; t<4; t++) {	//4가지 결과
			result = 0;
			st = new StringTokenizer(br.readLine()," ");
			for(int s=0; s<18; s++) {
				score[t][s] = Integer.parseInt(st.nextToken());
			}
			dfs(0,1,score[t]);	//기준 팀, 비교할 팀, 점수배열
			sb.append(result+" ");
		}
		System.out.println(sb);
		
	}

	private static void dfs(int teamA, int teamB, int[] score) {
		
		if(teamB == 6) {	//기준팀 뒤의 모든 팀들을 검사했으면 기준팀 바꿔주기
			dfs(teamA+1, teamA+2, score);
			return;
		}
		
		//기준팀이 마지막팀이 되어 비교할 팀이 없어짐 -> 가능한 결과인지 가능하지 않은 결과인지 체크
		//score에 0만 존재하면 가능 , score에 0이 아닌 수가 하나라도 있으면 불가능
		if(teamA > 4) {
			for(int i=0; i<score.length; i++) {
				if(score[i] > 0) {
					result = 0;	//불가능
					return;
				}
			}
			
			result = 1;	//가능
			return;
		}
	
		
		//기준팀에 대해 뒤에있는 모든 팀의 승패, 무승부 비교 계산
		for(int i=0,j=2; i<3; i++,j--) {	//teamA는 '승'부터, teamB는 '패'부터 계산
			if(score[teamA * 3 + i] > 0 && score[teamB * 3 + j] > 0) {
				score[teamA * 3 + i]--;
				score[teamB * 3 + j]--;
				dfs(teamA, teamB+1, score);	//기준팀뒤에 있는 팀들을 차례차례 비교
				score[teamA * 3 + i]++;
				score[teamB * 3 + j]++;
			}
		}
	}

}
