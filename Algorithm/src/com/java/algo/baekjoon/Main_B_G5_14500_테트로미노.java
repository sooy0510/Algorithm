package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 14500_테트로미노
 * 메모리 : 33964KB
 * 시간 : 484ms
 * 길이 : 2596B
 * 풀이 
 * 1. 가능한 테트로미노 정보 다 저장하고 시작
 *
 */

public class Main_B_G5_14500_테트로미노 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static Tetromino[] list;
	private static int result=0;
	private static int MAX = 0;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		list = new Tetromino[19];
		
		//테트로미노 모양 다 넣어주기 di, dj
		list[0] = new Tetromino(0,0,0,0,0,1,2,3);
		list[1] = new Tetromino(0,1,2,3,0,0,0,0);
		list[2] = new Tetromino(0,0,1,1,0,1,0,1);
		list[3] = new Tetromino(0,1,2,2,0,0,0,1);
		list[4] = new Tetromino(0,0,0,-1,0,1,2,2);
		list[5] = new Tetromino(0,0,1,2,0,1,1,1);
		list[6] = new Tetromino(0,0,0,1,0,1,2,0);
		list[7] = new Tetromino(0,1,2,2,0,0,0,-1);
		list[8] = new Tetromino(0,0,0,1,0,1,2,2);
		list[9] = new Tetromino(0,1,2,0,0,0,0,1);
		list[10] = new Tetromino(0,1,1,1,0,0,1,2);
		list[11] = new Tetromino(0,1,1,2,0,0,1,1);
		list[12] = new Tetromino(0,0,1,1,0,-1,-1,-2);
		list[13] = new Tetromino(0,1,1,2,0,0,-1,-1);
		list[14] = new Tetromino(0,0,1,1,0,1,1,2);
		list[15] = new Tetromino(0,0,0,1,0,1,2,1);
		list[16] = new Tetromino(0,0,-1,1,0,1,1,1);
		list[17] = new Tetromino(0,1,1,1,0,-1,0,1);
		list[18] = new Tetromino(0,1,2,1,0,0,0,1);
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				check(i,j);
			}
		}
		
		System.out.println(MAX);
	}

	private static void check(int i, int j) {
		for(int t=0; t<19; t++) {
			Tetromino tetro = list[t];
			int[] di = tetro.di;
			int[] dj = tetro.dj;
			
			int cnt=0;
			int result=0;
			
			for(int d=0; d<4; d++) {
				int next_i = i + di[d];
				int next_j = j + dj[d];
				
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
					break;
				
				result += map[next_i][next_j];
				cnt++;
			}
			
			if(cnt == 4) {
				//테트로미노 놓을 수 있음
				MAX = Math.max(result, MAX);
			}
		}
	}

}

class Tetromino{
	int[] di = new int[4];
	int[] dj = new int[4];
	
	Tetromino(int i0, int i1, int i2, int i3, int j0, int j1, int j2, int j3) {
		di[0] = i0;di[1] = i1;di[2] = i2;di[3] = i3;
		dj[0] = j0;dj[1] = j1;dj[2] = j2;dj[3] = j3;
	}
}
