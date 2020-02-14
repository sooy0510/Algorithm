package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G5_17471_게리멘더링_ing {

	static boolean[] visited;
	static ArrayList<Integer>[] list;
	static int N;
	static int[] people;
	static int[] area_A;
	static int[] area_B; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		
		//인구수
		people = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		//System.out.println(Arrays.toString(people));
		
		
		//정점수의 크기를 가진 Arraylist배열을 만든다
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		
		//인접리스트 만들기
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());

			for(int j=0; j<count; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
//		for(int i=1; i<N+1; i++) {
//			System.out.println(list[i]);
//		}
		
		//두 선거구 나누기(순열)
		//선거구는 적어도 1개의 구역은 있어야한다.
		visited = new boolean[N+1];
		for(int cnt=1; cnt<N+1; cnt++) {	//cnt는 area_A에서 선택할 구역 수
			permutation(1, cnt);
		}
	}

	private static void permutation(int index, int cnt) {	
		// index : 처리해야할 위치, cnt = 뽑아야하는 수(기저조건)
		
	}

}
