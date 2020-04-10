package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G4_1197_최소스패닝트리{

	private static int V,E;
	static int[] parents;
	static int[] rank;

	static class Edges implements Comparable<Edges>{
		int v1;
		int v2;
		int cost;
		public Edges(int v1, int v2, int cost) {
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edges o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		Edges[] edges = new Edges[E];
		int cnt = 0;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			edges[i] = new Edges(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edges);
		
		parents = new int[V+1];
		rank = new int[V+1];
		//Union find
		//각 정점에 대해서 union find연산 준비
		for(int i=0; i<V; i++) 
			makeSet(i);
		
		long result = 0;
		cnt= 0;	//간선 개수
		for(int i=0; i<E; i++) {
			int v1 = findSet(edges[i].v1);
			int v2 = findSet(edges[i].v2);
			
			if(v1 == v2)
				continue;
			
			result += edges[i].cost;
			union(v1, v2);
			cnt++;
			if(cnt == V-1)
				break;
		}
		System.out.println(result);
	}
	
	
	private static void union(int x, int y) {
		//두 개의 식별자를 받아서 두개의 집합을 하나로 합침
		int px = findSet(x);	//누가 어느팀인지 찾음
		int py = findSet(y);
		//누가 부모일지를 골라야함. 계층균형을 위해선 더 짧은걸 자식으로 만들어야함
		if(rank[px] > rank[py]) {	//py가 더 짧으면
			parents[py] = px;	//py의 부모가 px가 됨
		}else {
			parents[px] = py; 	//px가 더 짧으면 px의 부모가 py가 됨
			if(rank[px] == rank[py]){	//같은 레벨 두개가 뭉치면 레벨이 하나 더 올라가야함
				rank[py]++;	//py의 랭크를 한층 높여줌
			}
		}
	}

	private static int findSet(int x) {
		if (x == parents[x])
			return x;
		else {
			// path compression
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}

	private static void makeSet(int x) {
		parents[x] = x;
	}

}
