package com.java.algo.baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 7576_토마토
 * 메모리 : 121768KB
 * 시간 : 628ms
 * 코드길이 : 2662B
 * 풀이 
 * 1. BFS(단계마다 확인하는 방법 꼭 기억하기.!)
 * 
 */

public class Main_B_S1_7576_토마토 {

	static int M;	//가로 칸의 수
	static int N;	//세로 칸의 수
	static int[][] box;
	static int result=-1;
	static Queue<int[]> queue;
	
	//상하좌우
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		queue = new LinkedList<int[]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input == 1) {	//1 발견하면 다 큐에 넣어주기
					int[] in = {i,j};
					queue.offer(in);
				}
				box[i][j] = input;
			}
		}
		
		check(); 	//토마토 다 익엇는지 확인
		if(result == 0) {
			System.out.println(result);	//0출력
			return;
		}
			
		//토마토 뒤집기 시작(BFS)
		changeTomato();
		
		
		if(result == -1)
			System.out.println(result);
		else {
			System.out.println(result);
		}
		
	}

	private static void changeTomato() {
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size --> 0) {
				int[] out = queue.poll();
				if(box[out[0]][out[1]] ==1) {	//익은 토마토면 인접 토마토 뒤집기
					for(int d=0; d<4; d++) {
						int next_row = out[0] + dy[d];
						int next_col = out[1] + dx[d];
						
						if(next_row < 0 || next_row >= N || next_col < 0 || next_col >= M) {
							continue;
						}
						
						//안 익은 토마토 들었으면 익은 토마토로 바꾸기
						if(box[next_row][next_col] == 0) {
							box[next_row][next_col] = 1;
							//result++;
							
							
							int[] next = {next_row, next_col};
							queue.add(next);
						}
					}
				}
			}
			//한 턴 끝날때마다 +1
			result++;
			
		}
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(box[i][j] == 0) {	//익지 않은 토마토
					result = -1;
					return;
				}
			}
		}
		
	}

	private static void check() {
		int no=0; 	//빈칸 수
		int to1=0;	//익은 토마토
		int to2=0;	//안익은 토마토
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(box[i][j] == -1) {
					no++;
				}else if(box[i][j] == 1) {
					to1++;
				}else {
					to2++;
				}
			}
		}
		
		//모든 토마토가 익어있으면 0출력
		if((M*N-no) == (to1 + to2)) {
			if(to2 == 0)
				result = 0;
		}
	}

}
