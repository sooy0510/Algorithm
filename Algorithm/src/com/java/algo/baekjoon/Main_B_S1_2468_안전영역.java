package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2468_안전영역
 * 메모리 : 53320KB
 * 시간 : 256ms
 * 길이 : 2212B
 * 풀이
 * 1. 비의 양이 바뀔때마다 visited초기화 빼먹지 말기!
 */

/**
 * BFS
 * 1. 입력값의 min, max를 찾는다(min <= rain <= max)
 * 2. 비의 양에 따라 안전영역 수가 달라지니까 min부터 max까지 안전영역의 수를 구하는 for문을 돌린다
 * 	2.1  각 좌표를 돌면서 안전영역이면 queue에 넣고 생각한다 => queue에는 안전영역만 들어가게된다
 *  2.2 queue가 비어서 while문을 나올때마다 안전영역+1을 해준다
 *  2.3 기존의 안전영역 수의 MAX값과 비교&갱신
 * 3. MAX값 출력
 *
 */
public class Main_B_S1_2468_안전영역 {
	
	static int N;
	static int[][] map;//높이 정보
	static boolean[][] visited;
	static int MAX;		//안전영역의 최대수
	static int result;	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		int min = 100;	//1<=높이<=100 
		int max = 1;
		//입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				min = Math.min(min, temp);
				map[i][j] = temp;
				max = Math.max(max, temp);
			}
		}
		
		for(int r=min-1; r<=max; r++) {
			result = 0; //비의 양마다 안전영역의 개수가 다르다
		
			///////////////visit배열 초기화
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					visited[i][j] = false;
				}
			}
			
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					//아직 방문안했고 안전영역이면 탐색시작
					if(!visited[i][j] && map[i][j]>r) {
						bfs(i,j,r);
						MAX = Math.max(MAX, result);
					}
					
				}
			}
		}
		System.out.println(MAX);
	}

	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	private static void bfs(int i, int j, int r) {
		//visited처리해주고, 안전영역이니까 queue에 담는다
		Queue<Area> areas = new LinkedList<Area>();
		visited[i][j] = true;
		areas.add(new Area(i,j));
		
		while(!areas.isEmpty()) {
			Area area = areas.poll();
			
			for(int d=0; d<4; d++) {
				int next_i = area.i + di[d];
				int next_j = area.j + dj[d];
				
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= N) {
					continue;
				}
				
				//방문전 확인
				if(!visited[next_i][next_j] && map[next_i][next_j] > r) {
					visited[next_i][next_j] = true;
					areas.add(new Area(next_i,next_j));
				}
			}
		}
		result++;
	}

}
class Area{
	int i,j;
	Area(int i, int j){
		this.i = i;
		this.j = j;
	}
}
