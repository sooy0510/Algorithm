package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_9282_초콜릿과건포도 {

	private static int N;
	private static int M;
	private static int[][] map;
	static int result;	//건포도 갯수
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}	
			
			dfs(0,0,N,M,0);
			System.out.println("#"+test+" "+MIN);
		}
		
	}

	private static void dfs(int si, int sj, int ei, int ej, int cnt) {
		System.out.println("si : "+si+" /sj : "+sj+" /ei : "+ei+" /ej : "+ej);
		if(cnt > MIN)
			return;
		
		//더 이상 자를 조각이 없으면
		if(ei-si-1==0 && ej-sj-1==0) {
			//System.out.println("hi");
			System.out.println(cnt);
			MIN = Math.min(MIN, cnt);
			return;
		}
		
		
		System.out.println("넘겨받은 건포도 : "+cnt);
		//초기 초콜릿에 있었던 건포도 갯수 구하기
		for(int i=si; i<ei; i++) {
			for(int j=sj; j<ej; j++) {
				cnt += map[i][j];
				//System.out.println(cnt);
			}
		}
		System.out.println("추가후 건포도 : "+cnt);

		for(int t=0; t<2; t++) {
			if(t == 0) {//가로로 자르기
				int K = ei-si-1;
				
				if(K <= 0)
					continue;
				
				System.out.println("가로");
				for(int k=1; k<=K; k++) {
					int temp = 0;
					//윗부분
					for(int i=si; i<si+k; i++) {
						for(int j=sj; j<ej; j++) {
							temp += map[i][j];
						}
					}
					dfs(si, sj, si+k, ej, cnt+temp);
					//아랫부분
					temp = 0;
					for(int i=si+k; i<ei; i++) {
						for(int j=sj; j<ej; j++) {
							temp += map[i][j];
						}
					}
					dfs(si+k, sj, ei, ej, cnt+temp);
				}
			}else {
				//세로로 자르기
				int K = ej-sj-1;

				if(K <= 0)
					continue;
				
				System.out.println("세로");
				for(int k=1; k<=K; k++) {
					int temp = 0;
					//윗부분
					for(int i=si; i<ei; i++) {
						for(int j=sj; j<sj+k; j++) {
							temp += map[i][j];
						}
					}
					dfs(si, sj, ei, sj+k, cnt+temp);
					//아랫부분
					temp = 0;
					for(int i=si; i<ei; i++) {
						for(int j=sj+k; j<ej; j++) {
							temp += map[i][j];
						}
					}
					dfs(si, sj+k, ei, ej, cnt+temp);
				}
			}
		}
		
	}

}

/*
 * import java.io.BufferedReader; import java.io.InputStreamReader; import
 * java.util.Arrays; import java.util.StringTokenizer;
 * 
 * public class swea_9282 {
 * 
 * private static int TC; private static int N; private static int M; private
 * static int[][] map;
 * 
 * public static void main(String[] args) throws Exception { BufferedReader br =
 * new BufferedReader(new InputStreamReader(System.in)); StringTokenizer st =
 * new StringTokenizer(br.readLine(), " "); TC =
 * Integer.parseInt(st.nextToken()); for (int tc = 1; tc <= TC; tc++) { st = new
 * StringTokenizer(br.readLine(), " ");
 * 
 * N = Integer.parseInt(st.nextToken()); // 행 M =
 * Integer.parseInt(st.nextToken()); // 열 map = new int[N][M];
 * 
 * for (int i = 0; i < N; i++) { st = new StringTokenizer(br.readLine(), " ");
 * for (int j = 0; j < M; j++) { map[i][j] = Integer.parseInt(st.nextToken()); }
 * } memo = new int[N][M][N+1][M+1]; int temp = solve(0,0,N,M);
 * System.out.println("#"+tc+" "+temp); } }
 * 
 * static int[][][][] memo; static int solve(int startX,int startY,int rLen,int
 * cLen) { if(rLen == 1 && cLen == 1) { return 0; }
 * if(memo[startX][startY][rLen][cLen]>0) { return
 * memo[startX][startY][rLen][cLen]; }
 * 
 * int min = Integer.MAX_VALUE; int sum = 0; for(int i=startX;i<startX+rLen;i++)
 * { for(int j=startY;j<startY+cLen;j++) { sum+=map[i][j]; } } // 가로 for(int
 * i=1;i<rLen;i++) { int temp = sum + solve(startX,startY,i,cLen) +
 * solve(startX+i,startY,rLen-i,cLen); min = Math.min(min, temp); } // 세로
 * for(int j=1;j<cLen;j++) { int temp = sum + solve(startX,startY,rLen,j) +
 * solve(startX,startY+j,rLen,cLen-j); min = Math.min(min, temp); }
 * memo[startX][startY][rLen][cLen] = min;
 * 
 * return min; }
 * 
 * 
 * }
 */
