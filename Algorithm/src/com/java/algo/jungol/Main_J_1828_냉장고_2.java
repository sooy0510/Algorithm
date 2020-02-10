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


public class Main_J_1828_냉장고_2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		ArrayList<Point> result = new ArrayList<Point>();
		
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
			for(int j=i+1; j<N; j++) {
				if(arr[j][0] < arr[i][0]) {
					int[] temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;					
				}
			}
			
		}
		
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
					
					int[] ss = {a1, a2, b1, b2};
					Arrays.sort(ss);
					
					result.get(j).x = ss[1];
					result.get(j).y = ss[2];
					
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
		
		System.out.println(result.size());
		
	}

}