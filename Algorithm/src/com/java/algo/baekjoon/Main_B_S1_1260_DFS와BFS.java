package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1260_DFS와 BFS
 * 메모리 : 20032KB
 * 시간 : 228ms
 * 풀이
 * 1. 인접리스트 문제 -> 입력된 간선정보로 ArrayList를 이용해 인접리스트를 만든다
 * 2. 방문 정점은 작은수부터이기 때문에 sort로 정렬먼저 해줌
 * 3. DFS -> 재귀 이용해서 해결
 * 4. BFS -> Queue 이용해서 해결. visited 처리순서 다시 한번 확인할 것
 * 
 */

public class Main_B_S1_1260_DFS와BFS {

	static int V=0;		//정점 개수
	static int L=0; 	//간선 개수
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[V+1];
		
		//정점수의 크기를 가진 Arraylist배열을 만든다. 
		for(int i=1; i<V+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		//u와 v는 간선으로 이어져있다. 각 정점의 Arraylist에 정점정보들을 추가해줌
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			list[u].add(v);	//정점 u에는 v정점을
			list[v].add(u);	//정점 v에는 u정점을
		}
		
		//작은수부터 방문하기 때문에 sorting해줌
		for(int i=1; i<V+1; i++) {
			Collections.sort(list[i]);
		}

		visited = new boolean[V+1];
		dfs(start);
		System.out.println();
		
		
		visited = new boolean[V+1];
		bfs(start);
		
	}
	
	private static void dfs(int x) {
		//이미 방문했으면 return
		if(visited[x])
			return;
		
		visited[x] = true;
		System.out.print(x + " ");
		//x번째 정점의 인접정보를 모두 탐색
		for(int v : list[x]) {
			if(!visited[v]) {
				dfs(v);
			}
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			//queue 앞부터 하나씩 꺼내서 탐색
			int x = q.poll();
			System.out.print(x+" ");
			for(int v : list[x]) {
				if(!visited[v]) {
					//bfs 실행하기 전(큐에 넣기 전)에 방문처리
					//bfs는 너비 우선 탐색이기 때문에 큐에 중복으로 들어갈수가 있다.
					visited[v] = true;
					q.add(v);
				}
			}

		}
		
		
	}

}
