package com.java.algo.jungol;

import java.util.Arrays;
import java.util.Scanner;

public class Main_J_Q1740_�Ҽ�_�̼��� {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[] arr = new int[N-M+1];
		int idx = 0;
		int sum = 0;
		
		// 2���� ��� ����
		for(int i=M; i<=N; i++) {
			if(i == 1) i=2;
			
			boolean isPrime = true; //�ʱⰪ
			//for(int j=2; j*j<=i; j++) {
			for(int j=2; j<=Math.sqrt(i); j++) {
				if(i % j == 0) {
					//�Ҽ��� �ƴ�
					isPrime = false;
					break;
				}
			}
			
			if(isPrime) {
				//�Ҽ�
				sum += i;
				arr[idx] = i;
				idx++;
			}
		}
		
		//�Ҽ� 0���϶��� -1 ���
		if(sum == 0) {
			System.out.println(-1);
		}else {
			System.out.println(sum);
			System.out.println(arr[0]);
		}
	}

}
