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
 * 2146_다리만들기(2차)
 * 메모리 : 19200KB
 * 시간 : 188ms
 * 길이 : 3208B
 * 풀이
 * 1. BFS
 * 2. 섬을 List로 모아, 각 섬마다 구할수있는 다리의 최소값을 비교
 */

public class Main_B_G3_2146_다리만들기_2 {

	private static int N;
	private static int[][] map;
	private static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};	//상좌하우
	private static List<Point> islands;
	private static int islandNum;
	private static boolean[][] visited;
	private static int MIN;
	
	static class Point{
		int i,j,d,num;	
		Point(int i, int j, int d, int num){
			this.i = i;
			this.j = j;
			this.d = d;		//다리길이
			this.num = num;	//섬 번호
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());	//지도크기 N<=100
		map = new int[N][N];
		visited = new boolean[N][N];
		islands = new ArrayList<Point>();
		MIN = Integer.MAX_VALUE;

		//입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//섬 넘버링
		islandNum = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					setIslandNum(i,j);
					islandNum++;
				}
			}
		}
		
		//가장 짧은 다리 구하기
		for (Point island : islands) {
			//visited 초기화
			for (boolean[] row : visited) {
				Arrays.fill(row, false);
			}
			getShortDist(island);
		}
		
		System.out.println(MIN);
	}

	private static void getShortDist(Point island) {
		Queue<Point> q = new LinkedList<Point>();
		visited[island.i][island.j] = true;
		q.add(island);
		
		Point cur;
		int ni, nj;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			//더 이상 찾을필요없음
			if(cur.d >= MIN){
				break;
			}
			
			for(int d=0; d<4; d++) {
				ni = cur.i + dir[d][0];
				nj = cur.j + dir[d][1];
				
				if(isIn(ni,nj) && !visited[ni][nj]) {
					visited[ni][nj] = true;	//일단 방문했으니까 방문처리
					
					if(map[ni][nj] == cur.num) {	//같은 섬
						continue;
					}else if(map[ni][nj] == 0) {		//다리 만들기
						q.add(new Point(ni,nj,cur.d+1,cur.num));
						continue;
					}else if(map[ni][nj] != cur.num) {	//다른섬 만나면 종료
						MIN = Math.min(MIN, cur.d);
						return;
					}
				}
			}
		}
		
	}

	private static void setIslandNum(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		map[i][j] = islandNum;
		q.add(new Point(i,j,0,islandNum));
		islands.add(new Point(i,j,0,islandNum));
		
		Point cur;
		int ni, nj;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int d=0; d<4; d++) {
				ni = cur.i + dir[d][0];
				nj = cur.j + dir[d][1];
				
				if(isIn(ni, nj)) {
					if(map[ni][nj] == 1) {
						map[ni][nj] = islandNum;
						//islands 리스트에 넣기
						islands.add(new Point(ni,nj,0,islandNum));
						q.add(new Point(ni,nj,0,islandNum));
					}
				}
				
			}
		}
	}

	
	//지도 범위내에 있는지 체크
	private static boolean isIn(int ni, int nj) {
		return (ni >= 0 && nj >= 0 && ni < N && nj < N);
	}

}
