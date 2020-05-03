package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 5653_줄기세포배양
 * 메모리 : 107084KB
 * 시간 : 265ms
 * 길이 : 3591B
 * 풀이
 * 1. BFS
 * 2. 조건과 배양과정이 많아서 처리하기 어려웠다. 다시 풀것
 */

public class Solution_D_5653_줄기세포배양 {

	static class Cell{
		int i;
		int j;
		int x; //줄기세포의 생명력(처음입력값 보관용)
		
		int life; 	//활성화까지시간 -> 살아있는시간(시간이 지나면서 감소됨)
		int time;	//배양시간(시간이 지나면서 감소됨)
		int flag;	//활성화 상태(0:비활성화, 1:활성화)
		
		Cell(int i, int j, int x, int life, int time, int flag){
			this.i = i;
			this.j = j;
			this.x = x;
			
			this.life = life;
			this.time = time;
			this.flag = flag;
		}
	}
	
	private static int N,M,K;

	//상하좌우
	static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};

	private static List<ArrayList<Cell>> list;//줄기세포생명력 (1<=X<=10)별 저장(인덱스 0~9사용)

	private static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		list = new ArrayList<>();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			//초기화
			list.clear();
			for(int x=0; x<=9; x++) {	//줄기세포생명력 1<=X<=10, 리스트작업시 0~9로 변경
				list.add(new ArrayList<>());
			}
			
			//입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//세로 크기 1<=N<=50
			M = Integer.parseInt(st.nextToken());	//가로 크기 1<=M<=50
			K = Integer.parseInt(st.nextToken());	//배양 시간	1<=K<=300
			
			map = new int[N+K][M+K];	//최대크기
			
			for (int i = (K/2); i < (K/2)+N; i++) {	//배열의 중간을 계산(0,0) -> (K/2, K/2)
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = (K/2); j < (K/2)+M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());//줄기셒포생명력 1<=X<=10
					if(map[i][j] != 0) {
						int idx = map[i][j]-1;//리스트작업시 0부터 9까지(줄기세포생명력 1<=X<=10)로 인덱스화
						list.get(idx).add(new Cell(i,j,map[i][j],map[i][j],K, 0));
						//i:세로, j:가로, x:줄기세포생명력, life:활성화까지시간->살아있는 시간,time:배양시간,flag:비활성화(0)
					}
				}
			}
			
			//처리
			bfs();
			
			//출력
			int cnt = 0;
			for (int i = 0; i < N+K; i++) {
				for (int j = 0; j < M+K; j++) {
					if(map[i][j] != 0 && map[i][j] != -1)
						cnt++;
				}
			}
			
			sb.append("#"+test+" "+cnt+"\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void bfs() {
		Queue<Cell> q = new LinkedList<Cell>();
		for(int x=9; x>=0; x--) {	//생명력 수치가 높은 줄기 세포가 차지함
			if(list.get(x).size() != 0) {
				for(int s=0; s<list.get(x).size(); s++) {
					q.offer(list.get(x).get(s));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Cell c = q.poll();
			if(c.life == 0 && c.flag == 1) {//life==0 && 활성화 상태면 죽은 세포
				map[c.i][c.j] = -1;
				continue;
			}
			
			if(c.time == 0)	continue;	//배양시간 끝남
			if(c.life != 0) {//life != 0 (비활성화, 활성화상태 관계없이 다음작업 수행)
				q.offer(new Cell(c.i,c.j,c.x, c.life-1, c.time-1, c.flag));
				continue;
			}
			
			//life==0(비활성화 끝남) -> life=x, flag=1(활성화됨)
			q.offer(new Cell(c.i, c.j, c.x, c.x, c.time, 1));
			
			//활성화상태면 상,하,좌,우로 동시에 번식
			for(int d=0; d<4; d++) {
				int ni = c.i + dir[d][0];
				int nj = c.j + dir[d][1];
				
				if(0<=ni && ni<N+K && 0<=nj && nj<M+K && map[ni][nj]==0) {
					map[ni][nj] = c.x;	//줄기세포 생명력
					q.offer(new Cell(ni,nj,c.x,c.x,c.time-1,0));//새로 번식된 세포는 비활성화 상태
				}
			}
		}
	}

}
