package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1194_달이차오른다, 가자!
 * 메모리 : 16092KB
 * 시간 : 108ms
 * 길이 : 2573B
 * 풀이
 * 1. BFS
 * 2. 키가 있는지 없는지 확인하기 위해 방문체크 배열을 3차원으로 만들기
 * 3. 키 주울때 : OR연산
 * 	    짝 맞는 키가 있는지 확인 : AND연산으로 확인
 */

public class Main_B_G1_1194_달이차오른다가자 {
	private static int N,M;
	private static char[][] map;
	private static int MIN;
	private static boolean[][][] v;
	
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		//키를 가지고 갈때 3차원 방문체크가 필요함
		//A~F까지의 키를 가지고 있는지의 여부
		v = new boolean[N][M][1<<6];
		
		String input;
		int si = 0,sj = 0;
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
		
		int result = bfs(si,sj);
		
		System.out.println(result);
	}

	
	//상하좌우
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int bfs(int si, int sj) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(si,sj,0,0));
		v[si][sj][0] = true;	//키가 하나도 없다(모든 자리가 0이다)
		map[si][sj] = '.';
		
		Point cur;
		int ni, nj, key;
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
				
				if(ni < 0 || nj < 0 || ni >= N || nj >= M)
					continue;
				
				//벽이면 무시
				if(map[ni][nj] == '#') 
					continue;
				
				//방문했던곳이면 무시(키가 있으면 무시)
				if(v[ni][nj][key])
					continue;
				
				//열쇠있으면 줍기
				if(map[ni][nj] >= 'a' && map[ni][nj] <= 'f') {
					key |= (1 << (map[ni][nj]-'a'));	
				}
				
				//문인데 키가 없으면 무시
				if(map[ni][nj] >= 'A' && map[ni][nj] <= 'Z') {
					if((key & (1 << (map[ni][nj] - 'A'))) == 0) {	//열쇠없으면 못감
						continue;
					}
				}
				
				//방문체크하고 큐에 재삽입
				v[ni][nj][key] = true;
				queue.offer(new Point(ni,nj, key, cur.cnt+1));
			}
		}
		return -1;
	}
}
