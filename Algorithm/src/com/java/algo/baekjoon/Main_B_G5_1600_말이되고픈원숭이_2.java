package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1600_말이되고픈원숭이(2차)
 * 메모리 : 86740KB
 * 시간 : 476ms
 * 길이 : 2094B
 * 풀이
 * 1. BFS
 * 2. visited를 2차원으로 관리했더니 계속 메모리 초과
 * 3. 3차원배열로 방문체크하는건 생각못해봐서 새로운 문제
 *
 */

public class Main_B_G5_1600_말이되고픈원숭이_2 {

	private static int K,N,M;
	private static int[][] map;
	private static int end_i,end_j;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());	//0<=K<=30
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());	//0<=M<=200
		N = Integer.parseInt(st.nextToken());	//0<=N<=200
		
		map = new int[N][M];
		end_i = N-1;
		end_j = M-1;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = st.nextToken().charAt(0)-'0';
			}
		}
		System.out.println(move());
	}

	static int[][][] dir = {	//[2][?][2]
			{{0,1},{1,0},{0,-1},{-1,0}},	//우하좌상
			{{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}}	//말 이동
	};
	
	private static int move() {
		int[] temp = null;
		int i,j,ni,nj,cnt,moveCnt; //현재 i,j, 다음 i,j, 남은 말 이동 수, 총 이동수
		boolean visited[][][] = new boolean[K+1][N][M];	//31*200*200
		Queue<int[]> queue = new LinkedList<int[]>();	//말이동수, i, j, 이동동작횟수
		visited[K][0][0] = true;	
		queue.offer(new int[] {K,0,0,0});
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			cnt = temp[0];
			i = temp[1];
			j = temp[2];
			moveCnt = temp[3];
			
			if(i == end_i && j == end_j) {
				return moveCnt;
			}
			
			for(int h=0; h<2; h++) {
				if(h == 1) {	//말
					if(cnt == 0)break; 	//말 움직임이 안남아잇는경우
					else cnt--;
				}
				
				for(int d=0; d<dir[h].length; d++) {
					ni = i + dir[h][d][0];
					nj = j + dir[h][d][1];
					
					if(ni < 0 || nj < 0 || ni >= N || nj >= M)
						continue;
					
					if(map[ni][nj] ==0 && !visited[cnt][ni][nj]) {
						visited[cnt][ni][nj] = true;
						queue.offer(new int[] {cnt, ni, nj, moveCnt+1});
					}
					
				}
			}
		}
		return -1;
	}
}
