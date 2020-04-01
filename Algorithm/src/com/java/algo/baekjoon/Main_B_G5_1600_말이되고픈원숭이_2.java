package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_1600_말이되고픈원숭이_2 {

	
	static int map[][];
	static int row,col,endX,endY,count;
	
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		count = Integer.parseInt(in.readLine());
		StringTokenizer st= new StringTokenizer(in.readLine(), " ");
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		endX = row-1;
		endY = col-1;
		
		map = new int[row][col];
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(in.readLine()," ");
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(move());
		
	}

	static int[][][] direction = {
			{ {-1,0},{1,0},{0,-1},{0,1} }, // 원숭이의 움직임 (상하좌우)
			{ {-1,-2},{1,-2},{-2,-1},{2,-1},{-2,1},{2,1},{-1,2},{1,2}}, // 말의 움직임(8방)
	};
	private static int move() {
		int moveCnt=0, temp[]=null, x=0,y=0,nx=0,ny=0,cnt=0;
		boolean[][][] visited = new boolean[count+1][row][col]; // 31*200*200 : 1,200,000
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[count][0][0] = true;
		queue.offer(new int[] {count,0,0,0}) ; //남은말의움직임횟수,x,y,이동동작횟수 
		while (!queue.isEmpty()) {
			
			temp  = queue.poll();
			cnt = temp[0];
			x = temp[1];
			y = temp[2];
			moveCnt = temp[3];
			
			if(x==endX && y==endY) return moveCnt;
			
			for (int h = 0; h < 2; h++) { // h:0 ==>  원숭이 ,  1: 말 
				
				if(h==1) { // 말 
					if(cnt==0) break; //  말의 움직임을 다한 경우 
					else cnt--; // 말의 움직임이 남은 경우 횟수 차감 
				}
				
				for (int d = 0; d < direction[h].length; d++) {
					
					nx = x + direction[h][d][0];
					ny = y + direction[h][d][1];
					
					if(nx>=0 && nx<row && ny>=0 && ny<col && map[nx][ny] ==0 && !visited[cnt][nx][ny]) {
						visited[cnt][nx][ny] = true;
						queue.offer(new int[] {cnt,nx,ny,moveCnt+1});
					}
					
				}
			}
		}
		return -1;
	}

}
