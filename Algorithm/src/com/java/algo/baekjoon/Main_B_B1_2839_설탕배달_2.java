package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 2839_설탕배달(2차)
 * 메모리 : 15588KB
 * 시간 : 120ms
 * 길이 : 727B
 * 풀이
 * 1. 백드래킹 => 조건이 만족할 때의 모든 조합의 수를 살펴보는것
 * 2. 시간복잡도 : O(n)
 * 
 */

public class Main_B_B1_2839_설탕배달_2 {

	static int M;	//만들어야 하는 소금무게
	static int cnt;	
	static int MIN;//최소 봉지수

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		MIN = Integer.MAX_VALUE;
		gogosing(M, 0);
		System.out.println(MIN == Integer.MAX_VALUE? -1 : MIN);
	}

	//M : 남은 무게, cnt : 지금까지 사용한 봉지 수
	private static void gogosing(int M, int cnt) {
		if(M < 0) {	//해가 없다
			return;
		}else if(M == 0) {	//해
			System.out.println(cnt == Integer.MAX_VALUE? -1 : cnt);
			System.exit(0);
		}
		else {
			gogosing(M-5, cnt+1);	//5키로를 먼저 써야 빨리 도달
			gogosing(M-3, cnt+1);
		}
	}
}
