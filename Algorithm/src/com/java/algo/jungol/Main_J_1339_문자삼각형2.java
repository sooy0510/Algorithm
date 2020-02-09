package com.java.algo.jungol;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
 * Output Limit Exceeded 발생
 * */

public class Main_J_1339_문자삼각형2 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = sc.nextInt();
		
		if(n<1 || n>100 || n%2 == 0) {
			System.out.println("INPUT ERROR");
		}
		
		int mid = n/2;
		char c = 'A';
		
		char[][] arr = new char[n][];
		
		//동적할당
		for(int i=0; i<n; i++) {
			arr[i] = new char[mid+1];
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<mid+1; j++) {
				arr[i][j] = ' ';
			}
		}
		
		
		// 우측에서 좌측으로
		for(int j=mid; j>=0; j--) {
			for(int i=j; i<n-j; i++) {
				arr[i][j] = c;
				c = (char)(c + 1);
				if(c > 'Z') {
					c = 'A';
				}
			}
		}
		
//		for(int i=0; i<arr.length; i++) {
//			for(int j=0; j<arr[i].length; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			if(i != arr.length-1) {
//				System.out.println();
//			}
//		}
//		return;
		
		for(int row=0; row<n; row++) {
			if(row <= n/2) {
				for(int col =0; col<row+1; col++) {
					bw.write((char)arr[row][col] + " ");
				}
			}else {
				for(int col =0; col < n-row; col++) {
					bw.write((char)arr[row][col] + " ");
				}
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		sc.close();
	}
	
}