  
package com.java.algo.jungol;

import java.util.Scanner;

public class Main_J_2259_참외밭 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();
		int[] arr = new int[6];
		
		int h =0;
		int w =0;
		int hh = 0;
		int ww = 0;
		
		for(int i=0; i<6; i++) {
			sc.nextInt();
			arr[i] = sc.nextInt();
		}
		
		// 전체 사각형의 높이, 너비
		for(int i=0; i<6; i++) {
			if(i%2==0) {
				if(w < arr[i]) {
					w = arr[i];
				}
			}else {
				if(h < arr[i]) {
					h = arr[i];
				}
			}
		}
		
		// 한변(array[i])을 기준으로 array[i-1]+array[i+1]이 전체 사각형의 
		// 너비 또는 높이일 경우 빈 사각형 의미
		for(int i=0; i<6; i++) {
			if(i%2 == 0) {
				if(h == arr[(i+5)%6] + arr[(i+1)%6]) {
					ww = arr[i];
				}
			}else {
				if(w == arr[(i+5)%6] + arr[(i+1)%6]) {
					hh = arr[i];
				}
			}
		}
		System.out.println((w * h - ww * hh) * cnt);
	}

}