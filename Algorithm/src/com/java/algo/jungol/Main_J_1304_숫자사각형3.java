package com.java.algo.jungol;

import java.util.Scanner;

public class Main_J_1304_숫자사각형3{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n][n];
		int num = 1;
		
		for(int j=0; j<n; j++) {
			for(int i=0; i<n; i++) {
				arr[i][j] = num++;
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

}