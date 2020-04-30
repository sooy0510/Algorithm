package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_16234_인구이동 {

	private static int N,L,R;
	private static int[][] map;
	private static boolean[][] visited;
	private static boolean[][] newVisited;

	//상하좌우
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int count;	//이동한 횟수
	private static int sum, result, change;
	
	private static class Dot{
		int i,j;
		Dot(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		newVisited = new boolean[N][N];
		count = 0;
		sum = 0;
		result = 0;
		change = 0;
	
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dfs
		while(true) {
			int temp = change;
			visited = new boolean[N][N];
			newVisited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(newVisited[i][j] == true) {
						continue;
					}
					
					sum = 0;
					count = 0;
					solve(i,j);
					if(count <= 1) {	//하나만 방문했을 때 맵리셋 필요 x
						visited[i][j] = newVisited[i][j];
					}else {
						resetMap();
					}
				}
			}
			result++;
			if(temp == change) break;
		}
	
		System.out.println(result-1);
	}


	private static void resetMap() {
		// TODO Auto-generated method stub
		
	}


	private static void solve(int i, int j) {
		// TODO Auto-generated method stub
		
	}
}

