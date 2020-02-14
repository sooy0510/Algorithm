package com.java.algo.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_J_1863_종교 {

	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt=0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	//학생 수
		int m = Integer.parseInt(st.nextToken());	//종교의 쌍
		
		parents = new int[n+1];
		Arrays.fill(parents, -1);	//루트노드 -1로 초기화
		
		for(int s=0; s<m; s++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			union(i,j);
		}
		
		

		//부모노드만 세기
		for(int i=1; i<=n; i++) {
			if(parents[i] < 0) {
				cnt++;
			}
		}
		
		
		System.out.println(cnt);
	}
	
	//루트노드 찾기
	private static int findSet(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	//합치기
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;	//이미 같은 집합
	}

}
