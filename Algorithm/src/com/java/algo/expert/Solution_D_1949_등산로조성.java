package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1949_등산로조성
 * 메모리 : 25364KB
 * 시간 : 135ms
 * 길이 : 2204B
 * 풀이
 * 1. DFS
 * 2. return을 뺐더니 모두 해결되는 매직. 
 * return을 하면 자신을 호출한 함수로 돌아가게 되고, 같이 진행되던 다른 dfs가 종료되서 그런건가..?
 */

public class Solution_D_1949_등산로조성 {

	private static int N,K;
	private static int[][] map;
	private static boolean[][] visited;
	private static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			int MAX = 0;
			result = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > MAX) {
						MAX = map[i][j];
					}
				}
			}

			//dfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == MAX) {
						//visited초기화
						reset();
						dfs(i,j,1,true);
					}
				}
			}
			
			System.out.println("#"+test+" "+result);
		}
	}

	private static void reset() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; //상하좌우
	private static void dfs(int i, int j, int cnt, boolean isPos) {
		//System.out.println("isPos : "+isPos+" / i : "+i +" / j : "+j+" / cnt : "+cnt);
		visited[i][j] = true;
		
		for(int d=0; d<4; d++) {
			int ni = i + dir[d][0];
			int nj = j + dir[d][1];
			
			if(ni < 0 || nj < 0 || ni >= N || nj >= N)
				continue;
			
			if(visited[ni][nj]) continue;
			
			if(map[ni][nj] < map[i][j]) {
				dfs(ni,nj, cnt+1, isPos);
			}else {
				if(isPos == true) {	//깎는건 한번만 가능
					//최대 k만큼 깎을수 있음
					for(int k=1; k<=K; k++) {
						if(map[ni][nj]-k < map[i][j] && map[ni][nj]-k >= 0) {
							map[ni][nj] -= k;
							dfs(ni, nj, cnt+1, false);
							map[ni][nj] += k;
						}else { //깎아도 갈 수 없을경우
							result = Math.max(result, cnt);
							//return;
						}
					}
				}else {	//이미 한번 깎은경우
					result = Math. max(result, cnt);
					//return;
				}
			}
		}
		visited[i][j] = false;
	}

}
