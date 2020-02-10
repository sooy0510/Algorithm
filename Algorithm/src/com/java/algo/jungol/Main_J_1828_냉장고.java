package com.java.algo.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_J_1828_냉장고 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int result=0;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer[]> arr = new ArrayList<>();
		//LinkedList<Integer[]> list = new LinkedList<>();
		boolean isSame = false;
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int min = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			
			if(i==0) {
				//처음에 일단 냉장고 한대 등록
				Integer[] in = {min, max};
				arr.add(in);
				//System.out.println(arr.get(0)[0]);
				//System.out.println(arr.get(0)[1]);
				continue;
			}
			//System.out.println(arr.size());
			
			for(int j=0; j<arr.size(); j++) {
				if(min >= arr.get(j)[0] && min <= arr.get(j)[1]) {
					//같은 냉장고 사용가능
					isSame = true;
					break;
				}
			}
			isSame = false;
			
				
			if(isSame) { //기존에 있던 냉장고 사용가능이면
				continue;
			}else {	//아니면 새 냉장고 추가
				Integer[] in = {min, max};
				arr.add(in);
//				for(int a=0; a<arr.size(); a++) {
//					System.out.print(arr.get(a)[0]+" ");
//					System.out.print(arr.get(a)[1]+" ");
//					System.out.println();
//				}
			}
			
			
			
		}
		
//		for(int i=0; i<arr.size(); i++) {
//			for(int j=0; j<2; j++) {
//				System.out.print(arr.get(i)[0]+" ");
//				System.out.print(arr.get(i)[1]+" ");
//			}
//			System.out.println();
//		}
		
		System.out.println(arr.size());
		
	}

}
