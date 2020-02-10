package com.java.algo.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_J_1828_냉장고2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		ArrayList<Point> result = new ArrayList<Point>();
		
		//LinkedList<Integer[]> list = new LinkedList<>();
		boolean isSame = false;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int min = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			arr[i][0] = min;
			arr[i][1] = max;
		}
		
		// sorting
		for(int i=0; i<N-1; i++) {
			if(arr[i+1][0] < arr[i][0]) {
				int[] temp = arr[i+1];
				arr[i+1] = arr[i];
				arr[i] = temp;
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<2; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		for(int i=0; i<N; i++) {
			if(i==0) {
				//처음에 일단 냉장고 한대 등록
				result.add(new Point(arr[0][0], arr[0][1]));
			}
			
			int a1= arr[i][0];
			int b1= arr[i][1];
			
			for(int j=0; j<result.size(); j++) {
				int a2 = result.get(j).x;
				int b2 = result.get(j).y;
				
				if(a1 >= a2 && a1 <= b2) {
					//같은 냉장고 사용가능
					isSame = true;
					result.get(j).x = a1;
					result.get(j).y = b2;
					
					break;
				}
			}
			
			if(isSame) {
				isSame=false;
				continue;
			}else {
				isSame=false;
				result.add(new Point(a1,b1));
			}
		}
		
		
//		for(int i=0; i<result.size(); i++) {
//			System.out.print(result.get(i).x+" ");
//			System.out.print(result.get(i).y);
//			System.out.println();
//		}
		
		
		System.out.println(result.size());
		
	}

}

class Point{
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}