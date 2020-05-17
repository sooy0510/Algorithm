package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2636_치즈
 * 메모리 : 16144KB
 * 시간 : 128ms
 * 길이 : 4037B
 * 풀이
 * 1. BFS
 * 2. 치즈의 가장자리를 찾아주는 방법
 */

public class Main_B_G5_2636_치즈 {

	private static int N,M;
	private static int[][] map;
	private static boolean[][] visited;
	private static Queue<Point> meltingQueue = new LinkedList<Point>();
	private static int time;
	private static int result;	 //남아있는 치즈조각 개수

	private static class Point{
		int i,j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	//상하좌우
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		time = 0;
		result = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	//치즈인것들 모두 큐에 담기
			}
		}
		
		while(true) {
			init();
			//바깥 공기를 -1로 바꾼다
			setOut(0,0);
			
			//치즈의 가장자리 구역을 찾고, melting queue에 저장
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 1 && isEdge(i,j)) {
						setMelting(i,j);
					}
				}
			}
			
			//녹일 치즈가 더이상 없으면 종료
			if(meltingQueue.isEmpty()) break;
			
			//모든 치즈가 녹기 한 시간 전 남은 치즈면적 = 마지막으로 녹아야할 치즈 면적
			result = meltingQueue.size();
			
			//melting
			melting();
			
			//시간
			time++;
		}
		
		System.out.println(time);
		System.out.println(result);
	}

	private static boolean isEdge(int i, int j) {
		//한면이라도 -1이면 true 리턴
		
		for (int d = 0; d < 4; d++) {
            int ni = i + dir[d][0];
            int nj = j + dir[d][1];

            if (map[ni][nj] == -1) {
                return true;
            }
        }
		return false;
	}

	private static void melting() {
		//melting queue에 있는 녹을예정인 치즈들 모두-1로 만들기
		Point cur;
		while(!meltingQueue.isEmpty()) {
			cur = meltingQueue.poll();
			map[cur.i][cur.j] = -1;
		}
	}

	//녹일 영역 표시
	private static void setMelting(int i, int j) {
		Queue<Point> cheese = new LinkedList<Point>();
		cheese.add(new Point(i,j));
		visited[i][j] = true;
		
		Point cur;
		int ni,nj;
		
		while(!cheese.isEmpty()) {
			cur = cheese.poll();
			
			if(isEdge(cur.i,cur.j)) {
				//가장자리면 녹일예정이란 표시로 2로 만들고(바로 녹이면 다음 탐색에 영향), melting queue에 넣기
				map[cur.i][cur.j] = 2;
				meltingQueue.add(new Point(cur.i, cur.j));
			}

			//가장자리를 알아내기 위해 계속 탐색하면서 치즈를 큐에 담아주기
			for(int d=0; d<4; d++) {
				ni = cur.i + dir[d][0];
				nj = cur.j + dir[d][1];
				
				if(ni < 0 || nj < 0 || ni >= N || nj >= M)
					continue;
				
				if(!visited[ni][nj] && map[ni][nj] == 1) {
					visited[ni][nj] = true;
					cheese.add(new Point(ni,nj));
				}
			}
		}
	}

	//visited배열 초기화
	private static void init() {
		for (boolean[] arr : visited) {
			Arrays.fill(arr, false);
		}
	}

	private static void setOut(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] visitedAir = new boolean[N][M];
		
		visitedAir[i][j] = true;
		map[i][j] = -1;
		queue.add(new Point(i,j));
		
		Point cur;
		int ni,nj;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			for(int d=0; d<4; d++) {
				ni = cur.i + dir[d][0];
				nj = cur.j + dir[d][1];
				
				if(ni < 0 || nj < 0 || ni >= N || nj >= M)
					continue;
				
				if(visitedAir[ni][nj])
					continue;
				
				if(map[ni][nj] <= 0) {
					visitedAir[ni][nj] = true;
					map[ni][nj] = -1;
					queue.add(new Point(ni,nj));
				}
			}
		}
	}

}
