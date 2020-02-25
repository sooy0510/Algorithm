package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 11724_연결요소의개수
 * 메모리 : 120460KB
 * 시간 : 528ms
 * 
 *
 */

public class Main_B_S3_11724_연결요소의개수 {

	static int N,M;
	static int[][] arr;
	static boolean[] visited;
	static int result=0;
	static Queue<Point2> queue = new LinkedList<Point2>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];	//정점에 대한 visit관리
		
		//인접행렬 만들기
		for(int s=0; s<M; s++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			arr[i][j] = 1;
			arr[j][i] = 1;
		}
		
		
		for(int i=1; i<N+1; i++) {
			for(int j=i+1; j<N; j++) {
				if(!visited[i] && arr[i][j] == 1) {
					//방문처리 먼저 해줄것
					visited[i] = true; 
					queue.add(new Point2(i,j));
					bfs();
				}
				
			}
		}
		
		
		for(int i=1; i<N+1; i++) {
			if(!visited[i])
				result++;
		}
		
		System.out.println(result);
		
	}

	private static void bfs() {
		while(!queue.isEmpty()) {
			Point2 now = queue.poll();
			
			for(int c=1; c<N+1; c++) {
				if(!visited[c] && arr[now.i][c] == 1) {
					visited[c] = true;
					queue.add(new Point2(c,now.i));
				}
			}
			
		}
		result++;
	}

}

class Point2{
	int i,j;
	Point2(int i, int j){
		this.i = i;
		this.j = j;
	}
}
