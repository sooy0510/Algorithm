package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 
 * 1258_행렬찾기
 * 메모리 : 23984KB
 * 실행시간 : 144ms
 * 풀이
 * 1. DFS로 안풀어도 되는데 배웠으니까 써봤음(행렬안에 0이 없기떄문에  ㄱ 방향으로 찾아도됨)
 * 2. 탐색방향에 따라 end좌표가 달라져서 end좌표와 현재 가능한 좌표를 계속 비교해줘야하는데 몰라서 많이 해맸다
 *
 */

public class Solution_D4_1258_행렬찾기 {

	static int N; 	//배열 크기
	static int[][] arr;	//화학물질 정보
	static boolean[][] visited;
	
	//우하상좌
	static int[] dy = {0,1,-1,0};
	static int[] dx = {1,0,0,-1};
	
	static int end_y = 0;
	static int end_x = 0;
	
	public static void main(String[] args) throws Exception {
		/**
		 * 문제 조건
		 * 1. 모든 행렬을 찾아서 저장
		 * 2. 크기(행 x 열)이 작은 순서대로 출력
		 * 3. 크기(행 x 열)이 같다면 행이 작은 순으로 출력
		 * 4. 사각형이 대각선으로 맞닿아 있을 경우 0이 없을 수도 있다.
		 */
		
		System.setIn(new FileInputStream("Solution1258.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		ArrayList<int[]> list;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			list = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			
			// 행렬 찾기
			// 이중 for문 돌면서(화학물질 현황) dfs로 탐색
			// 찾은 배열(행과 열 크기)은 Arraylist에 저장
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && arr[i][j] !=0) {
						end_y = 0;
						end_x = 0;
						findArray(i,j);
						int[] insert = new int[2];
						insert[0] = end_y-i+1;
						insert[1] = end_x-j+1;
						list.add(insert);
					}
				}
			}
			
			Collections.sort(list, new Comparator<int[]>(){
				@Override
				public int compare(int[] o1, int[] o2) {
					int gop1 = o1[0] * o1[1];
					int gop2 = o2[0] * o2[1];
					
					if(gop1 == gop2) {
						return o1[0]-o2[0];
					}else {
						return gop1-gop2;
					}
				}
			});
			
			sb.append("#"+test+" "+list.size()+" ");
			for(int i=0; i<list.size(); i++) {
				for(int j=0; j<2; j++) {
					sb.append(list.get(i)[j]+" ");
				}
			}
			sb.append("\n");
			
		}
		
		System.out.println(sb);
		
		
	}

	private static void findArray(int row, int col) {
		if(row < 0 || row >= N || col < 0 || col >= N) {
			return;
		}
		
		if(visited[row][col]) {
			return;
		}
		
		
		if(arr[row][col] == 0) {
			return;
		}
		
		visited[row][col] = true;
		
		// 탐색의 방향에 따라 분기해줘야함
		// 현재 상하좌우로 탐색. 만약 상까지 가고 끝나면 end_y는 기존 end_y보다 더 작아진다
		if(row > end_y) {
			end_y = row;
		}
				
		if(col > end_x) {
			end_x = col;
		}
		//////////////////////////////////////////////////////
		
		for(int d=0; d<4; d++) {
			int next_row = row + dy[d];
			int next_col = col + dx[d];
			
			findArray(next_row, next_col);
		}
		
	}

}
