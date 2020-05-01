package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 4366_정식이의은행업무
 * 메모리 : 21032KB
 * 시간 : 106ms
 * 길이 : 1933B
 */

public class Solution_D4_4366_정식이의은행업무 {

	private static String num2_str;
	private static ArrayList list;
	private static String num3_str;
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			num2_str = br.readLine();
			num3_str = br.readLine();
			list = new ArrayList();
			
			//num2에서 가능한 수들
			getNum2(num2_str);
			
			//num3에서 가능한 수들
			getNum3(num3_str);
			
			System.out.println("#"+test+" "+result);
		}
	}

	private static void getNum3(String num_str) {
		int[] arr = new int[num_str.length()];
		for(int i=0; i<arr.length; i++) {
			arr[i] = Character.getNumericValue(num_str.charAt(i));
		}
		
		int[] numbers = {0,1,2};
		
		for(int i=0; i<arr.length; i++) {
			for(int n=0; n<3; n++) {
				if(arr[i] == numbers[n])
					continue;
				
				int temp = arr[i];
				arr[i] = numbers[n];
				
				//계산
				int sum = 0;
				for(int j=arr.length-1; j>= 0; j--) {
					sum += arr[arr.length-j-1] * Math.pow(3,j);
				}
				
				if(list.contains(sum)) {
					result = sum;
					return;
				}
				
				//원상태
				arr[i] = temp;
			}
		}
		
	}

	private static void getNum2(String num_str) {
		int[] arr = new int[num_str.length()];
		for(int i=0; i<arr.length; i++) {
			arr[i] = Character.getNumericValue(num_str.charAt(i));
		}
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == 0) {
				if(i == 0) {
					continue;
				}
				arr[i] = 1;
			}else {
				arr[i] = 0;
			}
			
			//계산 
			int sum = 0;
			for(int j=arr.length-1; j>= 0; j--) {
				sum += arr[arr.length-j-1] * Math.pow(2,j);
			}
			list.add(sum);
			
			if(arr[i] == 0) {
				arr[i] = 1;
			}else {
				arr[i] = 0;
			}
		}
	}

}
