package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 17142_연구소3(refac)
 * 메모리 : 46140KB
 * 시간 : 260ms
 * 길이 : 2968B
 * 풀이
 * 1. 조합, bfs
 * 2. visited 제거 => 시간 늘어남
 */

public class Main_B_G4_17142_연구소3_refac {

	private static int N,M;
	private static int[][] map;
	private static List<Virus> virus;
	private static Virus[] pick;
	private static int INF = Integer.MAX_VALUE;
	private static int MIN = Integer.MAX_VALUE;
	
	private static class Virus{
		int i,j;
		Virus(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());	//연구소 크기 4<=N<=50
		M = Integer.parseInt(st.nextToken());	//바이러스 개수 1<=M<=10
		
		map = new int[N][N];
		virus = new ArrayList<>();
		pick = new Virus[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {	//바이러스
					virus.add(new Virus(i,j));
				}
			}
		}
		
		//M개의 바이러스 뽑기
		pickVirus(0,0);
		System.out.println((MIN == INF ? -1 : MIN));
	}

	private static void pickVirus(int index, int count) {
		
		if(count == M) {
			//바이러스 퍼뜨리기
			bfs(pick);
			return;
		}
		
		for(int i=index; i<virus.size(); i++) {
			pick[count] = virus.get(i);
			pickVirus(i+1, count+1);
		}
	}

	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	private static int bfs(Virus[] pVirus) {
		int[][] cMap = new int[N][N];
		//맵 복사
		for(int i=0; i<N; i++) {
			cMap[i] = Arrays.copyOf(map[i], N);
		}
		
		Queue<Virus> queue = new LinkedList<Virus>();
		for(int i=0; i<pVirus.length; i++) {
			queue.add(pVirus[i]);
			cMap[pVirus[i].i][pVirus[i].j] = 9;	//쓰지않는 수 9로 셋팅
		}
		
		Virus cur;
		int ni,nj;
		int time = 0;
		while(!queue.isEmpty()) {
			if(check(cMap)) {	//바이러스가 모두 퍼졌으면
				if(MIN > time)
					MIN = time;
				break;
			}
			
			
			int size = queue.size();
			while(size --> 0){
				cur = queue.poll();
				for(int d=0; d<4; d++) {
					ni = cur.i + dir[d][0];
					nj = cur.j + dir[d][1];
					
					if(ni < 0 || nj < 0 || ni >= N || nj >= N)
						continue;
					
					//벽
					if(cMap[ni][nj] == 1)
						continue;
					
					//0이면 빈칸 => 바이러스 전파, 2는 비활성화 바이러스 => 활성화 바이러스로 변환
					if(cMap[ni][nj] == 0 || cMap[ni][nj] == 2) {
						cMap[ni][nj] = 9;
						//큐에 추가
						queue.add(new Virus(ni,nj));
					}
				}
			}
			time++;
			
		}
		return time;
	}

	private static boolean check(int[][] copy) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(copy[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
