package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2252_줄세우기(2차)
 * 메모리 : 54780KB
 * 시간 : 660ms
 * 길이 : 1913B
 * 풀이
 * 1. 위상정렬
 */

public class Main_B_G2_2252_줄세우기_2 {

	private static int N,M;
	private static int x,y;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] list = new ArrayList[N+1];
		//진입차수 배열 
		int[] indegree = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		//입력
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			list[x].add(y);
			indegree[y]++;	//x가 선행되어야함
		}
		
		//진입차수가 0인 것들을 큐에 넣기
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		if(q.size() == 0) {
			System.out.println("사이클존재");
			return;
		}
		
		//큐돌리면서 현재 정점이랑 이어져있는 정점의 진입차수 감소 -> 0되면 출력리스트에 넣어주기
		ArrayList<Integer> result = new ArrayList<Integer>();
		Integer cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			result.add(cur);	//큐에 들어갈수잇는건 진입차수0인애들뿐
			
			for (int idx : list[cur]) {
				indegree[idx]--;
				
				if(indegree[idx] == 0) {
					q.offer(idx);
				}
			}
		}
		
		//큐가 비었는데 정점이 남아있으면 사이클 존재
		if(result.size() != N) {
			System.out.println(" 사이클 존재");
			return;
		}
		
		//순서대로 출력
		for (int r : result) {
			System.out.print(r+" ");
		}
	}
}
