package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_S3_14889_스타트와링크 {
	static int MIN = Integer.MAX_VALUE;
	static int N;
	static int[] team1;
	static int[] team2;
	static boolean[] selected_1;
	static boolean[] selected_2;
	// N : 사람수(짝수)
	// 번호배정은 1부터 N까지
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int[][] arr = new int[N+1][N+1];
		team1 = new int[N/2+1];
		team2 = new int[N/2+1];
		selected_1 = new boolean[N+1];
		selected_2 = new boolean[N+1];
		
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
		
	}
	
	private static void makeTeam(int index, int cnt) { //team배열에서 처리할 위치
		//인덱스 다 차면
		if(cnt == N/2) {
			System.out.print("team1 : ");
			System.out.println(Arrays.toString(team1));
			
			System.out.print("team2 : ");
			System.out.println(Arrays.toString(team2));
			return;
		}

		
		
		for(int i=1; i<=N; i++) {
			if(selected_1[i] == true)
				continue;
			if(selected_2[i] == true)
				continue;
			//team1에들어가면 team2에는 없음
			team1[index] = i;
			selected_1[i] = true;
			selected_2[i] = false;
			makeTeam(index+1, cnt+1);
			team2[index] = i;
			selected_1[i] = false;
			selected_2[i] = true;
		}
	}

}
