package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 3190_뱀
 * 메모리 : 16692KB
 * 시간 : 104ms
 * 길이 : 2558B
 * 풀이
 * 1. 뱀을 Queue로 관리
 *
 */

public class Main_B_S1_3190_뱀 {

	static int N,K,D;
	static int time=0;
	static int dir=0;
	static int[][] map;
	static Queue<TurnPoint> queue = new LinkedList<TurnPoint>();
	static Queue<Snake> snake = new LinkedList<Snake>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
				
		map = new int[N+1][N+1];
		
		StringTokenizer st;
		
		//시작점
		snake.add(new Snake(1, 1));
		dir = 2;	//방향은 오른쪽으로 시작
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			map[i][j] = 1;
		}
		
		D = Integer.parseInt(br.readLine());
		
		for(int i=0; i<D; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			queue.add(new TurnPoint(t, d));
		}
		
		dummy(1,1);
		System.out.println(time);
		
	}

	//상=1,우=2,하=3,좌=4
	static int[] di = {0,-1,0,1,0};
	static int[] dj = {0,0,1,0,-1};
	
	private static void dummy(int i, int j) {
		time++;
		
		//해당 방향으로 한칸가기
		int head_i = i + di[dir];
		int head_j = j + dj[dir];
		
		//벽에 부딪치면 게임 종료
		if(head_i < 1 || head_i >= N+1 || head_j < 1 || head_j >= N+1) {
			return;
		}
		
		
		Snake head = new Snake(head_i, head_j);
		
		for (Snake s : snake) {
			if(s.i == head_i && s.j == head_j) {
				return;
			}
		}
		
		
		//몸만 앞으로 감
		if(map[head_i][head_j] == 0) {
			snake.offer(head);
			//기존 몸있는 곳은 빼기
			snake.poll();
		}//사과면 몸길이 추가
		else if(map[head_i][head_j] == 1) {
			snake.offer(head);
			//사과는 사라짐
			map[head_i][head_j] = 0;
		}
		
//		for (Snake s : snake) {
//			map[s.i][s.j] = 2;
//		}
//		
//		System.out.println(time);
//		for (int ii = 1; ii < N+1; ii++) {
//			for (int jj = 1; jj < N+1; jj++) {
//				System.out.print(map[ii][jj]+ " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		for (Snake s : snake) {
//			map[s.i][s.j] = 0;
//		}
		
		//방향 전환 시간이 될때
		if(!queue.isEmpty()) {
			TurnPoint tp = queue.peek();
			if(tp.t == time) {
				if(tp.d == 'D') {
					//오른쪽으로 90도
					dir++;
					if(dir == 5) {
						dir = 1;
					}
				}else {
					//왼쪽으로 90도
					dir--;
					if(dir == 0) {
						dir = 4;
					}
				}
				queue.poll();
			}
		}
		
		dummy(head_i, head_j);
	}

}

class TurnPoint{
	int t;
	char d;
	TurnPoint(int t, char d){
		this.t = t;
		this.d = d;
	}
}

class Snake{
	int i,j;
	Snake(int i, int j){
		this.i = i;
		this.j = j;
	}
}
