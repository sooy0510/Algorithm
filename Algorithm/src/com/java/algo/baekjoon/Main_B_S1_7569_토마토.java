package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 7569_토마토
 * 메모리 : 122752KB
 * 시간 : 600ms
 * 길이 : 2507B
 * 풀이
 * 1. BFS
 *
 */

public class Main_B_S1_7569_토마토 {

	private static int M,N,H;
	private static int[][][] box;
	private static Queue<int[]> queue;
	private static int tomatoes, empty;
	static int result;
	
	//상,하,좌,우,위,아래
	static int[][] dir = {{-1,0,0},{1,0,0},{0,-1,0},{0,1,0},{0,0,1},{0,0,-1}};
	private static boolean[][][] visited; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());	//가로
		N = Integer.parseInt(st.nextToken());	//세로	
		H = Integer.parseInt(st.nextToken());	//높이
		
		box = new int[N][M][H];
		int temp = 0;

		queue = new LinkedList<int[]>();
		visited = new boolean[N][M][H];
		tomatoes = 0; empty = 0;
		
		for(int h=0; h<H; h++) {
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					temp = Integer.parseInt(st.nextToken());
					box[i][j][h] = temp;
					if(temp == 1) {	//익어있는 토마토
						tomatoes++;
						visited[i][j][h] = true;
						queue.add(new int[]{i,j,h});
					}else if(temp == -1) { //빈칸
						empty++;
					}
				}
			}
		}
		
		
		result = 0;
		//저장될 때부터 모든 토마토가 익어있는 상태인지 검사
		if(tomatoes+empty == N*M*H) {
			System.out.println(0);
			return;
		}
		
		//bfs
		int size = 0;
		int i,j,h;
		int ni,nj,nh;
		while(!queue.isEmpty()) {
			size = queue.size();
			while(size --> 0) {
				int[] cur = queue.poll();
				i = cur[0]; j = cur[1]; h = cur[2];
				
				for(int d=0; d<6; d++) {
					ni = i + dir[d][0];
					nj = j + dir[d][1];
					nh = h + dir[d][2];
					
					if(ni < 0 || nj < 0 || nh < 0 || ni >= N || nj >= M || nh >= H) {
						continue;
					}
					
					if(box[ni][nj][nh] == 0 && !visited[ni][nj][nh]) {
						box[ni][nj][nh] = 1;
						visited[ni][nj][nh] = true;
						queue.add(new int[] {ni,nj,nh});
					}
				}
			}
			result++;
		}
		
		//토마토 다 익었는지 체크, 다 익었으면 result 출력 아니면 -1출력
		for(int z=0; z<H; z++) {
			for(int x=0; x<N; x++) {
				for(int y=0; y<M; y++) {
					if(box[x][y][z] == 0) {	//익지 않은 토마토가 있으면
						System.out.println(-1);
						return;
					}
				}
			}
		}
		
		//큐의 마지막 레벨에 들어갔던 토마토는 이미 익은토마들이므로 -1 해줌
		System.out.println(result-1);
	}

}
