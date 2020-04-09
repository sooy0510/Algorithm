package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로_Kruskal {

	private static int N;
	private static long[][] islands;
	private static double E;
	static int[] parents;
	static int[] rank;
	
	static class Edges implements Comparable<Edges>{
		int v1;
		int v2;
		long cost;
		public Edges(int v1, int v2, long cost) {
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edges o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		//최소신장트리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			islands = new long[N][N];	//0<=X,Y<=1,000,000
			
			StringTokenizer xArr = new StringTokenizer(br.readLine(), " ");
			StringTokenizer yArr = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				islands[i][0] = Long.parseLong(xArr.nextToken());
				islands[i][1] = Long.parseLong(yArr.nextToken());
			}
			
			//정점1 정점2 가중치
			Edges[] edges = new Edges[N*(N-1)/2];
			int cnt = 0;
			for(int i=0; i<N-1; i++) {
				for(int j=i+1; j<N; j++) {
					edges[cnt] = new Edges(i, j, dist(islands[i][0], islands[j][0], islands[i][1], islands[j][1]));
					cnt++;
				}
			}
			
			E = Double.parseDouble(br.readLine());
			
			Arrays.sort(edges);

			parents = new int[N];
			rank = new int[N];
			//Union find
			//각 정점에 대해서 union find연산 준비
			for(int i=0; i<N; i++) 
				makeSet(i);
			
			long result = 0;
			cnt= 0;	//간선 개수
			for(int i=0; i<N*(N-1)/2; i++) {
				int v1 = findSet(edges[i].v1);
				int v2 = findSet(edges[i].v2);
				
				if(v1 == v2)
					continue;
				
				result += edges[i].cost;
				union(v1, v2);
				cnt++;
				if(cnt == N-1)
					break;
			}
			sb.append("#"+test+" "+Math.round(result*E)+"\n");
		}
		System.out.println(sb);
	}

	private static long dist(long x1, long x2, long y1, long y2) {
		return (long)(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
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
