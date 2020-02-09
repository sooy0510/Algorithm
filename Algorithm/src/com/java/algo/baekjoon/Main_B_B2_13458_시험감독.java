package com.java.algo.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main_B_B2_13458_시험감독 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int a[] = new int[N];
		long result = 0;
		
		for(int i=0; i<N; i++) {
			a[i] = sc.nextInt();
		}
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		
		//각 반마다 필요한 감독관 수 더하기
		for(int i=0; i<N; i++) {
			//총감독관이 감시하고 남은학생수
			if(a[i] <= B) {
				result += 1;
				continue;
			}else {
				a[i] -= B;
				//부감독관 수 구하기
				if(a[i]%C == 0) {	//딱 맞으면 몫이 부감독 수
					result += a[i]/C + 1;
				}else {
					result += a[i]/C + 2; 
				}
			}
		}
		
		System.out.println(result);
		
	}

}
