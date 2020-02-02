package com.java.algo.hw;

import java.util.Scanner;

public class Main_J_1175_�ֻ���������2_�̼��� {

	static int[] arr;
	static int N;
	static int M;
	static int sum; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		
		permutation(0);
	}

	private static void permutation(int index) {	//���� ó���Ϸ��� �ϴ� ��ġ
		
		if(index == N) {
			sum = 0;
			for(int i=0; i<N; i++) {
				sum += arr[i];
			}
			
			if(sum == M) {
				for(int i=0; i<N; i++) {
					System.out.print(arr[i]+" ");
				}
				System.out.println();
			}
			
			return;
		}
		
		for(int i=1; i<7; i++) {
			arr[index] = i;
			permutation(index+1);
		}
	}

}
