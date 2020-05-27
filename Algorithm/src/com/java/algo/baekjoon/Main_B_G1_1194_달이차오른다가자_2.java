package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1194_달이차오른다, 가자!(2차)
 * 메모리 : 15872KB
 * 시간 : 104ms
 * 길이 : 2369B
 * 풀이
 * 1. bfs
 * 2. 다시 푸니까 비트마스킹 사용이 전보다는 익숙해졌다.
 */

public class Main_B_G1_1194_달이차오른다가자_2 {

	private static int N,M;
	private static char[][] map;
	private static boolean[][][] visited;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	private static class Point{
		int i,j,key,cnt;
		Point(int i, int j, int key, int cnt){
			this.i = i;
			this.j = j;
			this.key = key;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception{
		//열쇠 있는지 없는지 체크해야되므로 3차원 방문체크를 해줘야함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][1<<6];	//a~f까지의 열쇠
		
		String input;
		int si=0, sj=0;
		for(int i=0; i<N; i++) {
			input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '0') {
					si = i;
					sj = j;
				}
			}
		}
		
		int result = go(si,sj);
		System.out.println(result);
	}

	private static int go(int si, int sj) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(si,sj,0,0));
		visited[si][sj][0] = true;
		map[si][sj] = '.';
		
		Point cur;
		int ni, nj;
		int key;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			//출구
			if(map[cur.i][cur.j] == '1') {
				return cur.cnt;
			}
			
			for(int d=0; d<4; d++) {
				ni = cur.i + dir[d][0];
				nj = cur.j + dir[d][1];
				key = cur.key;
				
				if(ni < 0 || nj < 0 || ni >= N || nj >=M)
					continue;
				
				//1. 벽
				if(map[ni][nj] == '#')
					continue;
				
				//2. 방문햇던곳(키가 이미있으면)
				if(visited[ni][nj][key])
					continue;
				
				//3. 키 있으면 줍기
				if(map[ni][nj] >= 'a' && map[ni][nj] <= 'f') {
					key |= (1 << (map[ni][nj]-'a'));
				}
				
				//4. 문인데 키 없으면 무시
				if(map[ni][nj] >= 'A' && map[ni][nj] <= 'F') {
					if((key & (1 << (map[ni][nj]-'A'))) == 0) {
						continue;
					}
				}
				
				//방문체크하고 큐에 재삽입
				visited[ni][nj][key] = true;
				queue.add(new Point(ni,nj,key,cur.cnt+1));
					
			}
		}
		
		return -1;
	}

}
