package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 17143_낚시왕
 * 메모리 : 25256KB
 * 시간 : 400ms
 * 길이 : 3391B
 * 풀이
 * 1. 시뮬레이션
 * 2. 이동후에 같은 자리에 상어가 중복된다면 더 큰 상어를 남겨야하기 때문에 이동전 map과 이동후 map,
 * 총 2가지의 map을 이용한다.
 *
 */

public class Main_B_G3_17143_낚시왕 {

	static class Shark{
		int speed,dir,size;

		public Shark(int speed, int dir, int size) {
			super();
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
		}
	}

	static int R,C,M,sum,col;
	static Shark[][] map;
	static final int UP=1, DOWN=2, RIGHT=3, LEFT=4;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//잡은 상어 크기의 함
		sum = col = 0;
		map = new Shark[R][C];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c] = new Shark(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		
		while(col<C){
			// 가까운 상어잡기
			take();
			// 상어이동하며 잡아먹기
			move();
			col++;
		}
		System.out.println(sum);
	}

	private static void move() {
		//움직이기 이전과 움직인 후 두가지 맵이 필요
		Shark[][] temp = new Shark[R][C];
		int k = 0, s=0, a=0, x,y,d;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				Shark curShark = map[i][j];
				if(curShark == null) continue;	//상어없으면 다음 자리로 넘어감
				
				if(curShark.dir == UP || curShark.dir == DOWN) {
					k = i;
					s = curShark.speed%(2*(R-1));	//속도 개선 : 2*(R-1)번 이동하면 제자리
					a = curShark.dir == UP? -1: 1;
					
					//k는 현재위치
					while(s-->0) {
						if(k+a<0 || k+a>=R)a = -a; 	//경계에 닿으면 턴
						k += a;
					}
					
					x = k;	//이동하고 난 후 상어위치
					y = j;
					d = a<0 ? UP : DOWN;
				}else {
					k = j;
					s = curShark.speed%(2*(C-1));	//속도 개선 : 2*(C-1)번 이동하면 제자리
					a = curShark.dir == LEFT? -1: 1;
					
					//k는 현재위치
					while(s-->0) {
						if(k+a<0 || k+a>=C)a = -a; 	//경계에 닿으면 턴
						k += a;
					}
					
					x = i;	//이동하고 난 후 상어위치
					y = k;
					d = a<0 ? LEFT : RIGHT;
				}
				curShark.dir = d;
				//s만큼 이동한 후의 x,y위치에 다른 상어 있는지 체크하여 다른상어가 잇다면 크기 비교하여 큰상어로 남김
				if(temp[x][y] != null) {
					if(temp[x][y].size < curShark.size) {
						temp[x][y] = curShark;
					}
				}else {	//그 위치에 다른 상어가 없으면 자기가 그자리에 넣기
					temp[x][y] = curShark;
				}
			}
		}
		
		
		//변경된 사항들 반영하기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}

	private static void take() {	//낚시왕의 현위치(행 기반)에서 가장 가까운 상어 잡기
		for(int i=0; i< R; i++) {
			if(map[i][col] != null) {	//해당 행에 상어가 있다면
				sum += map[i][col].size;
				map[i][col] = null;	//가까운 한마리의 상어만 잡는거니까 잡았으면 break
				break;
			}
		}
	}

}
