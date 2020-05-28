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
 * 17142_연구소3
 * 메모리 : 43036KB
 * 시간 : 260ms
 * 길이 : 3460B
 * 풀이
 * 1. 조합, bfs
 */

public class Main_B_G4_17142_연구소3 {

	private static int N,M;
	private static int[][] map;
	private static List<Virus> virus;
	private static Virus[] pick;
	private static int INF = Integer.MAX_VALUE;
	private static int MIN = Integer.MAX_VALUE;
	
	private static class Virus{
		int i,j,cnt;
		Virus(int i, int j, int cnt){
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Virus [i=" + i + ", j=" + j + ", cnt=" + cnt + "]";
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
					virus.add(new Virus(i,j,3));
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
			int result = bfs(pick);
			if(result != -1) {
				MIN = Math.min(MIN, result);
			}
			
			return;
		}
		
		for(int i=index; i<virus.size(); i++) {
			pick[count] = virus.get(i);
			pickVirus(i+1, count+1);
		}
	}

	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	private static int bfs(Virus[] pVirus) {
		//2까지 쓰고있으므로 시간은 3 = 0 으로 생각함
		int[][] cMap = new int[N][N];
		//맵 복사
		for(int i=0; i<N; i++) {
			cMap[i] = Arrays.copyOf(map[i], N);
		}
		
		boolean[][] visited = new boolean[N][N];
		Queue<Virus> queue = new LinkedList<Virus>();
		for(int i=0; i<pVirus.length; i++) {
			queue.add(pVirus[i]);
			visited[pVirus[i].i][pVirus[i].j] = true;
			cMap[pVirus[i].i][pVirus[i].j] = 3;
		}
		
		Virus cur;
		int ni,nj;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			for(int d=0; d<4; d++) {
				ni = cur.i + dir[d][0];
				nj = cur.j + dir[d][1];
				
				if(ni < 0 || nj < 0 || ni >= N || nj >= N)
					continue;
				
				//벽
				if(cMap[ni][nj] == 1)
					continue;
				
				//방문햇으면
				if(visited[ni][nj])
					continue;
				
				//0이면 빈칸 => 바이러스 전파
				if(cMap[ni][nj] == 0) {
					cMap[ni][nj] = cur.cnt+1;
					visited[ni][nj] = true;
					//큐에 추가
					queue.add(new Virus(ni,nj,cur.cnt+1));
				}
				
				//2는 비활성화 바이러스 => 활성화 바이러스로 변환
				if(cMap[ni][nj] == 2) {
					cMap[ni][nj] = 3;
					visited[ni][nj] = true;
					queue.add(new Virus(ni,nj,cur.cnt+1));
				}
			}
		}
		
		int result = 0;
		
		//가장 큰 수 고르기
		boolean isOk = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cMap[i][j] == 0) {	//퍼지지 못한곳
					return -1;
				}
				result = Math.max(cMap[i][j], result);
			}
		}
		
		return result-3;
	}

}
