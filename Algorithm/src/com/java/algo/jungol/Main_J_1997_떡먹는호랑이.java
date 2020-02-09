package com.java.algo.jungol;

import java.util.Scanner;

public class Main_J_1997_떡먹는호랑이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();	//할머니가 넘어온 날
		int K = sc.nextInt();	//떡 개수
		
		int[] arr = new int[D+1];
		
		// x,y 둘다 0이면 모든 배열 값이 0이므로 첫째날만 떡개수 0가능
		for(int x=0; x<100; x++) {
			arr[1] = x; 
			for(int y=1; y<100; y++) {
				arr[2] = y;
				
				// D째날 떡개수 구해서 arr[D]에 넣기
				for(int i=3; i<D+1; i++) { 
					arr[i] = arr[i-2]+arr[i-1];
				}
				
				// 조건 : 둘째날 떡개수가 첫째날 떡개수보다 크고, 떡개수는 10이상이고 K개일때 출력
				if((x<y) &&(arr[D] >= 10) && (arr[D] == K)) {
					System.out.println(x);
					System.out.println(y);
					return;
				}
			}
		}
	}
}