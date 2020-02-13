package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 3289_서로소집합
 * 메모리 : 100223KB
 * 실행시간 : 413ms
 * 풀이 
 * 1. 서로소집합 실습
 * - makeSet
 * - findSet
 * - Union
 * 
 * 2. Union에서 값 1개만 옮기는것이 아니라, 하나의 집합을 모두 옮겨야함을 주의!
 *
 */

public class Solution_D4_3289_서로소집합 {

	static int n;
	static int m;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parents = new int[n+1];
			Arrays.fill(parents, -1); //make-set
			
			sb.append("#"+test+" ");
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int info = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(info == 0) {//합집합
					union(a,b);
				}else {
					if(findSet(a) == findSet(b)) { //같은집합이면 1,아니면 0
						sb.append(1);
					}else {
						sb.append(0);
					}
				}
				
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	private static int findSet(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}

}
