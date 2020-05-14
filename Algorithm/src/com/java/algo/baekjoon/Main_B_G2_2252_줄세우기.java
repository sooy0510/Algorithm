package com.java.algo.baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_B_G2_2252_줄세우기 {

	private static int N,M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
//		진입차수 관리(인접리스트, 유향그래프)
		int[] indegree = new int[N+1];
		for(int i=0; i<M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			list[x].add(y);
			indegree[y]++;
		}
//		진입차수가 0인것들 큐에 삽입
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		if(q.size() == 0) {
			System.out.println("사이클 존재");	//위상정렬은 사이클 존재하면 안됨
			return;
		}
		
//		큐에서 하나빼서 연결된애들 진입차수 1씩 감소, 다시 0인것들은 큐에 삽입(진입차수가 0인거니까)
//		큐에서 하나씩 뺄때 그 내용을 리스트로 정리
		ArrayList<Integer> result = new ArrayList<>();
		Integer cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			result.add(cur);	
			for ( int idx : list[cur]) {
				indegree[idx]--;
				if(indegree[idx] == 0) {
					q.offer(idx);
				}
			}
		}
		
		//정점이 아직 남아있는데도(result.size() 적음) 
		//큐가  먼저 비면(진입차수가 0인정점없음) 사이클 존재로 위상정렬 불가
		if(result.size() != N) {
			System.out.println(" 사이클 존재");
			return;
		}
		
		for (Integer idx : result) {
			System.out.print(idx+" ");
		}
	}
}
