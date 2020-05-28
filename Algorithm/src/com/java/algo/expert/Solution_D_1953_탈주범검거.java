package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1953_탈주범검거
 * 메모리 : 26452KB
 * 시간 : 157ms
 * 길이 : 3372B
 * 풀이
 * 1. BFS
 * 2. 다음 Point의 파이프가 현재 파이프와 연결되는지 확인 
 */

public class Solution_D_1953_탈주범검거 {

	private static int N,M,si,sj,L;
	private static int[][] map;
	private static boolean[][] visited;
	private static Queue<Point> queue;
	private static int result;
	
	private static class Point{
		int i,j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			si = Integer.parseInt(st.nextToken());
			sj = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			escape(si,sj);
			sb.append("#"+test+" "+result+"\n");
		}
		System.out.println(sb.toString());
	}

	//상하좌우
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	private static void escape(int si, int sj) {
		visited = new boolean[N][M];
		queue = new LinkedList<>();
		visited[si][sj] = true;
		queue.add(new Point(si,sj));
		
		int time = 0;
		
		Point cur;
		int i,j,type;
		while(!queue.isEmpty()) {
			if(time == L) {	//탈출시간 다됐으면 종료
				return;
			}
			
			int size = queue.size();
			
			while(size --> 0) {
				cur = queue.poll();
				result++;
				
				for(int d=0; d<4; d++) {
					i = cur.i;
					j = cur.j;
					type = map[cur.i][cur.j];
					
					if(type >= 1 && type <= 7) {
						switch (type) {
						case 1://상하좌우
							for(int k=0; k<4; k++) {
								check(i,j,k);
							}
							break;
							
						case 2://상하
							check(i,j,0); check(i,j,1); break;
							
						case 3://좌우
							check(i,j,2); check(i,j,3); break;
							
						case 4://상우
							check(i,j,0); check(i,j,3); break;
							
						case 5://하우
							check(i,j,1); check(i,j,3); break;
							
						case 6://하좌
							check(i,j,1); check(i,j,2); break;
							
						case 7://상좌
							check(i,j,0); check(i,j,2); break;
						}
					}
				}
			}
			time++;
		}
	}

	private static void check(int i, int j, int d) {
		int ni, nj;
		
		ni = i + dir[d][0];
		nj = j + dir[d][1];
		
		if(inIn(ni,nj) && !visited[ni][nj]) {
			//map[ni][nj]가 map[i][j]와 이어지는지 확인해야함
			int next = map[ni][nj];
			switch (d) {
			case 0:	//상
				if(next == 1 || next == 2 || next == 5 || next == 6)
					go(ni,nj); break;
				
			case 1:	//하
				if(next == 1 || next == 2 || next == 4 || next == 7) 
					go(ni,nj); break;
				
			case 2:	//좌
				if(next == 1 || next == 3 || next == 4 || next == 5) 
					go(ni,nj); break;
					
			case 3:	//우
				if(next == 1 || next == 3 || next == 6 || next == 7) 
					go(ni,nj); break;
			}
		}
	}

	private static void go(int ni, int nj) {
		visited[ni][nj] = true;
		queue.add(new Point(ni,nj));
	}

	private static boolean inIn(int i, int j) {
		return (i >= 0 && j >= 0 && i < N && j < M);
	}

}
