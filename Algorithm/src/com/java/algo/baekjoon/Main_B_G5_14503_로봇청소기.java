package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 14503_로봇청소기
 * 메모리 : 13308KB
 * 시간 : 88ms
 * 길이 : 2711B
 * 풀이
 * 1. 시뮬, DFS
 * 2. 후진처리 잘못해서 시간 오래걸림
 * -> 현재 바라보는 방향 그대로 후진을 해야되는데, stack에서 뽑아와서 왓던길을 되돌아감
 *
 */

public class Main_B_G5_14503_로봇청소기 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int r;
	private static int c;
	private static int d;
	static Stack<Point5> stack = new Stack<Point5>();
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		goClean(r,c);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 2)
					result++;
			}
		}
		
		
		System.out.println(result);
	}

	//북, 동, 남, 서
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,1,0,-1};
	//DFS
	private static void goClean(int r, int c) {
		//현재 위치 청소
		if(map[r][c] == 0) {
			map[r][c] = 2;
			stack.push(new Point5(r,c));
		}
		
		
		while(!stack.isEmpty()) {
			Point5 now = stack.peek();
			
			int cnt = 0;
			//왼쪽방향부터 탐색
			for(int dir=(d+3)%4; cnt < 4; dir=(dir+3)%4) {
				int next_i = now.i + di[dir];
				int next_j = now.j + dj[dir];
				
				if(map[next_i][next_j] == 1 || map[next_i][next_j] == 2) {
					//벽이거나, 청소한 상태면 그 방향으로 회전하고 계속 왼쪽방향으로 탐색
					//d = (dir+3)%4;
					d = dir;
					cnt++;
					continue;
				}
				
				if(map[next_i][next_j] == 0) {
					//아직 청소하지 않은 공간이면, 회전한 다음, 한칸 전진후, stack에 추가
					d = dir;
					map[next_i][next_j] = 2;
					stack.push(new Point5(next_i, next_j));
					break;
				}
			}
			
			//네 방향 모두 청소가 이미 되어있거나 벽이면, 바라보는 방향 유지한 채로 한 칸 후진하고 다시 왼쪽부터 탐색
			if(cnt == 4) {
				int next_i = now.i - di[d];
				int next_j = now.j - dj[d];
				if(map[next_i][next_j] == 1) {
					return;
				}
				stack.pop();
				stack.push(new Point5(next_i, next_j));
				continue;
			}
		}
		
	}

}

class Point5{
	int i,j;
	Point5(int i, int j){
		this.i = i;
		this.j = j;
	}
}
