package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 16236_아기상어
 * 메모리 : 24896KB
 * 시간 : 168ms
 * 길이 : 2904B
 * 풀이
 * 1. 시뮬, BFS
 */

public class Main_B_G5_16236_아기상어 {

	private static int N;
	private static int[][] map;
	private static Queue<Fish> q;

	static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}}; //상좌하우
	private static int result;
	private static int weight;
	private static int[][] distance;
	private static int cnt;
	private static PriorityQueue<Fish> fish;
	
	static class Fish implements Comparable<Fish>{
		int i,j,dist;
		Fish(int i, int j, int dist){
			this.i = i;
			this.j = j;
			this.dist = dist;
		}
		@Override
		public int compareTo(Fish o) {//거리, i, j순
			if(this.dist == o.dist) {
				if(this.i == o.i) {
					return Integer.compare(this.j, o.j);
				}
				return Integer.compare(this.i, o.i);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		q = new LinkedList<Fish>();
		
		weight = 2;	//초기 크기2
		result = 0; //생존 시간
		cnt = 0;	//먹은 물고기
		
		int temp;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp == 9) { //아기 상어 위치
					map[i][j] = 0;
					q.add(new Fish(i,j,0));
				}
			}
		}
		
		
		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		
		Fish shark;
		int ni, nj;
		
		while(true) {
			fish = new PriorityQueue<Fish>();
			distance = new int[N][N]; 	//상어에서 물고기까지의 거리를 저장할거임
			
			while(!q.isEmpty()) {
				shark = q.poll();
				
				for(int d=0; d<4; d++) {
					ni = shark.i + dir[d][0];
					nj = shark.j + dir[d][1];
					
					if(ni < 0 || nj < 0 || ni >= N || nj >= N)
						continue;
					
					if(map[ni][nj] <= weight && distance[ni][nj] == 0) {
						distance[ni][nj] = distance[shark.i][shark.j] + 1;
						
						if(map[ni][nj] >= 1 && map[ni][nj] <= 6 && map[ni][nj] < weight) {//상어 크기보다 작은 물고기
							q.add(new Fish(ni,nj,distance[ni][nj]));
							fish.add(new Fish(ni,nj,distance[ni][nj]));
							continue;
						}
						
						//상어와 크기같으면 이동만
						q.add(new Fish(ni,nj,distance[ni][nj]));
					}
				}
			}
			
			//해당되는 물고기들이 없으면 종료
			if(fish.size() == 0) {
				return;
			}
			
			Fish start = fish.poll();
			
			map[start.i][start.j] = 0;
			result += start.dist;	//이동 거리
			cnt += 1;	//먹은 물고기
			if(weight == cnt) {
				weight++;
				cnt = 0;
			}
			
			q.add(new Fish(start.i, start.j, 0));
		}
	}

}
