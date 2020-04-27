package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G3_13460_구슬탈출2 {

	private static int N,M;
	private static char[][] map;
	private static int ri, rj, bi, bj, di, dj;
	static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		result = Integer.MAX_VALUE;
		
		char input;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				input = str.charAt(j);
				map[i][j] = input;
				if(input == 'R') { ri = i; rj = j; }
				else if(input == 'B') { bi = i; bj = j; }
				else if(input == '0') { di = i; dj = j; }
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		goEscape(ri, rj, bi, bj, 0);
		System.out.println(result);
	}

	//우상하좌,0
	static int[][] dir = {{0,1},{-1,0},{1,0},{0,-1}};
	
	private static void goEscape(int ri, int rj, int bi, int bj, int cnt) {
		if(cnt > 10) {
			System.out.println(-1);
			System.exit(0);
		}
		
		int nri = 0, nrj=0, nbi=0, nbj=0;
		for(int d=0; d<4; d++) {
			
			while(map[ri][rj] != '#') {
				nri = ri + dir[d][0];
				nrj = rj + dir[d][1];
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						System.out.print(map[i][j]+" ");
					}
					System.out.println();
				}
				System.out.println();
				
				//벽일때
				if(nri < 1 || nrj < 1 || nri > N || nrj > M) {
					System.out.println("벽");
					break;
				}
				
				System.out.println(nri +" / "+nrj);
				
				if(map[nri][nrj] == '0') {
					result = Math.min(result, cnt+1);
					System.out.println(result);
					//return;
					System.exit(0);
				}
				
				if(map[nri][nrj] == '.') {//R이 B보다 앞에 있을 때
					map[ri][rj] = '.';
					map[nri][nrj] = 'R';
					ri = nri;
					rj = nrj;
					
					
					while(map[bi][bj] != '#') {
						nbi = bi + dir[d][0];
						nbj = bj + dir[d][1];
						
						if(nbi < 1 || nbj < 1 || nbi > N || nbj > M)
							break;
						
						if(map[nbi][nbj] == '0') {
							result = Math.min(result, cnt);
							return;
						}
						map[bi][bj] = '.';
						map[nbi][nbj] = 'B';
						bi = nbi;
						bj = nbj;
						
						for (int i = 0; i < N; i++) {
							for (int j = 0; j < M; j++) {
								System.out.print(map[i][j]+" ");
							}
							System.out.println();
						}
						System.out.println();
					}
					
				}else if(map[nri][nrj] == 'B') {	//B가 R보다 앞에 있을 때
					//B먼저 이동
					while(map[bi][bj] != '#') {
						nbi = bi + dir[d][0];
						nbj = bj + dir[d][1];
						
						if(nbi < 1 || nbj < 1 || nbi > N || nbj > M)
							break;
						
						if(map[nbi][nbj] == '0') {
							return;
						}
						map[bi][bj] = '.';
						map[nbi][nbj] = 'B';
						bi = nbi;
						bj = nbj;
					}
					
					map[ri][rj] = '.';
					map[nri][nrj] = 'R';
					ri = nri;
					rj = nrj;
					
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < M; j++) {
							System.out.print(map[i][j]+" ");
						}
						System.out.println();
					}
					System.out.println();
				}
				
			}
			
			goEscape(ri, rj, bi, bj, cnt+1);
			
		}
	}

}
