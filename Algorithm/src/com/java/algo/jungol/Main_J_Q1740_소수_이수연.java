package com.java.algo.jungol;

import java.util.Arrays;
import java.util.Scanner;

public class Main_J_Q1740_소수_이수연 {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[] arr = new int[N-M+1];
		int idx = 0;
		int sum = 0;
		
		// 2부터 배수 제거
		for(int i=M; i<=N; i++) {
			if(i == 1) i=2;
			
			boolean isPrime = true; //초기값
			//for(int j=2; j*j<=i; j++) {
			for(int j=2; j<=Math.sqrt(i); j++) {
				if(i % j == 0) {
					//소수가 아님
					isPrime = false;
					break;
				}
			}
			
			if(isPrime) {
				//소수
				sum += i;
				arr[idx] = i;
				idx++;
			}
		}
		
		//소수 0개일때는 -1 출력
		if(sum == 0) {
			System.out.println(-1);
		}else {
			System.out.println(sum);
			System.out.println(arr[0]);
		}
	}

}