package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 2630_색종이자르기
 * 메모리 : 14584KB
 * 시간 : 116ms
 * 길이 : 1401B
 * 풀이
 * 1. 시작인덱스 확인 잘하기,, 4구역 나눠서 재귀보낼때 실수햇따
 *
 */

public class Main_B_S3_2630_색종이만들기 {

	static int N; //한 변의 길이
	static int[][] input;
	static int white;
	static int blue;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		input = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		exp(0, 0, N/2);
		exp(0, N/2, N/2);
		exp(N/2, N/2, N/2);
		exp(N/2, 0, N/2);
		
		System.out.println(white);
		System.out.println(blue);
	}

	private static void exp(int row, int col, int n) {
		
		int same = n*n;
		int w;
		int b;
		
		if(n == 1) {
			if(input[row][col] == 0) {
				white++;
			}else {
				blue++;
			}
			return;
		}
		
		
		
		w = 0; b = 0;
		for(int i=row; i<row+n; i++) {
			for(int j=col; j<col+n; j++) {
				if(input[i][j] == 0) {
					w++;
				}else {
					b++;
				}
			}
		}
		
		if(w == same) {
			white++;
		}else if(b == same) {
			blue++;
		}else {
			//4구역에 나눠서 검사
			int newSize = n/2;
			exp(row, col, newSize);
			exp(row, col+newSize, newSize);
			exp(row+newSize, col+newSize, newSize);
			exp(row+newSize, col, newSize);
		}
		
	}

}
