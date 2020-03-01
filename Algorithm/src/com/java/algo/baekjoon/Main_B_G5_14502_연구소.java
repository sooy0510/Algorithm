package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 14502_연구소
 * 메모리 : 281112KB
 * 시간 : 756ms
 * 길이 : 2654B
 * 풀이
 * 1. 조합, BFS
 * 2. 바이러스 퍼진부분을 -1로 처리해서, 다른 조합을 위해 원래대로 초기화할때 -1만 0으로 수정했음
 *
 */

public class Main_B_G5_14502_연구소 {

	static int MAX = 0;
	static int result;
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] visited_v;
	static int cnt=0;
	static Queue<Virus> virus = new LinkedList<Virus>();
	 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		visited_v = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				if(input == 2) {
					virus.add(new Virus(i,j));
				}
			}
		}
		
		comb(0,0,0);
		
		System.out.println(MAX);
		
	}

	
	private static void comb(int i, int j, int cnt) {
		if(cnt == 3) {
			virus();
			MAX = Math.max(result, MAX);
			return;
		}
		
		if(j == M) {
			i++;
			j=0;
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] != 0)
					continue;
				
					map[r][c] = 1;
					comb(i,j+1,cnt+1);
					map[r][c] = 0;
				}
			}
		}
		

	//상하좌우
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	//바이러스 퍼뜨리기
	private static void virus() {
		Queue<Virus> temp = new LinkedList<Virus>();
		visited_v = new boolean[N][M];
		for (Virus v : virus) {
			visited_v[v.i][v.j] = true;
			temp.add(v);
		}
		
		while(!temp.isEmpty()) {
			Virus v = temp.poll();
			
			for(int d=0; d<4; d++) {
				int next_i = v.i + di[d];
				int next_j = v.j + dj[d];
				
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
					continue;
				
				//벽이아니고, 방문 안했으면
				if(!visited_v[next_i][next_j] && map[next_i][next_j] != 1) {
					visited_v[next_i][next_j] = true;
					map[next_i][next_j] = -1;
					temp.add(new Virus(next_i, next_j));
				}
			}
		}
		
		//최대영역 구하기
		result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					result++;
				}else if(map[i][j] == -1)
					map[i][j] = 0;
			}
		}
		
		
	}

	private static boolean check(int i, int j) {
		if(visited[i][j])
			return false;
		
		if(map[i][j] == 1 || map[i][j] == 2)
			return false;
		
		return true;
	}

}

class Virus{
	int i,j;
	Virus(int i, int j){
		this.i = i;
		this.j = j;
	}
}
