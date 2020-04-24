package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_16234_인구이동 {

	private static int N,L,R;
	private static int[][] map;
	private static int[][] visited;
	private static int ans;
	private static int idx;

	private static class Dot{
		int i,j;
		Dot(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new int[N][N];
		ans = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(!start()) {
			System.out.println("hi");
			//ans++;
		}
		System.out.println(ans);
	}

	//상하좌우
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static boolean start() {
		int ni, nj;
		int diff;
		boolean isStop = true;
		
		//visited 초기화
			for(int v=0; v<N; v++) {
				//Arrays.fill(visited[v], false);
				Arrays.fill(visited[v], 0);
			}
		
		idx = -1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int d=0; d<4; d++) {
					ni = i + dir[d][0];
					nj = j + dir[d][1];
					
					if(ni >= 0 && nj >= 0 && ni < N && nj < N) {
						diff = Math.abs(map[i][j]-map[ni][nj]);
						if(diff >= L && diff <= R) {
							System.out.println(i+" / "+j);
							isStop = false;
							bfs(ni, nj, idx);
						}
					}
				}
				//System.out.println();
			}
		}
		return isStop;
	}
	
	private static void bfs(int i, int j, int idx) {
		double people = 0;
		int cnt = 0;
		int diff;
		
		Queue<Dot> queue = new LinkedList<Dot>();
		queue.add(new Dot(i,j));
		visited[i][j] = idx;
		people += map[i][j];
		cnt++;
		
		//연합 생성
		Dot cur;
		int ni,nj;
		int size;
		
		while(!queue.isEmpty()) {
			//people = 0;
			//cnt = 0;
			size = queue.size();
			while(size --> 0) {
				cur = queue.poll();
				//people += map[cur.i][cur.j];
				//cnt++;
				
				for(int d=0; d<4; d++) {
					ni = cur.i + dir[d][0];
					nj = cur.j + dir[d][1];
					
					if(ni >= 0 && nj >= 0 && ni < N && nj < N && visited[ni][nj] == 0) {
						diff = Math.abs(map[cur.i][cur.j]-map[ni][nj]);
						if(diff >= L && diff <= R) {
							people += map[ni][nj];
							cnt++;
							visited[ni][nj] = idx;
							queue.add(new Dot(ni,nj));
						}
					}
				}				
			}
			//연합 인구수 구하기
			int result = (int)Math.floor(people/cnt);
			
			//인구이동
			move(result, idx);
			people = 0;
			cnt = 0;
			idx--;
		}
		ans++;
		
	}

	private static void move(int result, int idx) {
		for(int v=0; v<N; v++) {
			System.out.println(Arrays.toString(visited[v]));
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println();
		//visited = true인 나라들만 바꿔주기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j] == idx) {
					map[i][j] = result;
				}
			}
		}
	}

}

