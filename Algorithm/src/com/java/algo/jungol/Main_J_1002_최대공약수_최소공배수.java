package com.java.algo.jungol;

import java.util.Arrays;
import java.util.Scanner;

public class Main_J_1002_최대공약수_최소공배수 {

	static int gcf = 1; //최대공약수
	static int lcm = 1; //최소공배수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		arr[0] = sc.nextInt();
		gcf = arr[0];
		lcm = arr[0];
		
		for(int i=1; i<N; i++) {
			arr[i] = sc.nextInt();
			gcf = get_gcf(gcf, arr[i]);
			lcm = get_lcm(lcm, arr[i]);
		}
		
		System.out.print(gcf+" "+lcm);
	}

	private static int get_gcf(int a, int b) {
		int max = Math.max(a, b);
		for(int i=1; i<=max; i++) {
			if(a%i == 0 && b%i == 0) {
				gcf = i;
			}
		}
		return gcf;
	}

	private static int get_lcm(int a, int b) {
		for(int i=1; i<=2000000000; i++) {
			if(i%a == 0 && i%b == 0) {
				lcm = i;
				break;
			}
		}
		return lcm;
	}
	

}