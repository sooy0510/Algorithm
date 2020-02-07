package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_S3_14889_스타트와링크 {
	static int MIN = Integer.MAX_VALUE;
	static int N;
	static int[][] arr;
	
	static int[] team1;
	static int[] team2;
	static boolean[] selected;
	static boolean[] visited1;
	static boolean[] visited2;
	
	static int members1[] = new int[2];
	static int members2[] = new int[2];
	
	static int sum1=0;
	static int sum2=0;
	// N : 사람수(짝수)
	// 번호배정은 1부터 N까지
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		arr = new int[N+1][N+1];
		team1 = new int[N/2];
		team2 = new int[N/2];
		selected = new boolean[N+1];
		visited1= new boolean[N+1];
		visited2= new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int i=1; i<=N; i++) {
//			for(int j=1; j<=N; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		// N/2명씩 팀짜기 순열
		makeTeam(1,0);
		System.out.println(MIN);
		
	}
	
	private static void makeTeam(int index, int cnt) { //team배열에서 처리할 위치
		//인덱스 다 차면
		if(cnt == N/2) {
			System.out.print("team1 : ");
			System.out.println(Arrays.toString(team1));
			
			//teamB구하기
			int idx = 0;
			for(int i=1; i<=N; i++) {
				if(selected[i] == false) {
					team2[idx++] = i;
				}
			}
			
			System.out.print("team2 : ");
			System.out.println(Arrays.toString(team2));
			
			//System.out.println(getScore(team1));
			//System.out.println(getScore(team2));
			
			int abs = Math.abs(operate1(0)-operate2(0));
			MIN = Math.min(MIN, abs);
			
			return;
		}

		
		
		for(int i=1; i<=N; i++) {
			if(selected[i] == true) {
				continue;
			}
			//team1에들어가면 team2에는 없음
			team1[index-1] = i;
			selected[i] = true;
			makeTeam(index+1, cnt+1);
			selected[i] = false;
		}
	}

//	private static int getScore(int[] team) {
//		int score=0;
//		
//		// index, sum
//		operate1(0);
//		
//		return score;
//	}

	private static int operate1(int index) {

		System.out.println(Arrays.toString(team1));
		
		int score = 0;
		if(index >= 1) {
			score = arr[members1[0]][members1[1]] + arr[members1[1]][members1[0]];
		}
		
		for(int i=1; i<N/2; i++) {
			if(visited1[i] == true )
				continue;
			members1[index] = team1[i-1];
			visited1[i] = true;
			operate1(index+1);
			visited1[i] = false;
		}
		
		
		//System.out.println("score1 : "+score);
		return score;
		
	}
	
	private static int operate2(int index) {

		int score = 0;
		
		if(index >= 1) {
			score = arr[members2[0]][members2[1]] + arr[members2[1]][members2[0]];
		}
		
		for(int i=1; i<N/2; i++) {
			if(visited2[i] == true )
				continue;
			members2[index] = team2[i-1];
			visited2[i] = true;
			operate2(index+1);
			visited2[i] = false;
		}
		
		//System.out.println("score2 : "+score);
		return score;
		
	}

}
