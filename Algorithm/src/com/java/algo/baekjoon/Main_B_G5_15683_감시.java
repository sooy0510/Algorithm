package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 15683_감ㅣ
 * 메모리 : 65600KB
 * 시간 : 316ms
 * 길이 : 3637B
 * 풀이
 * 1. dfs라고는 생각했는데, 감시영역을 어떻게 관리해줘야할지 몰라서 강사님 코드 참고
 * 2. dfs를 호출할때 현재상태를 복제한 맵을 계속 넘겨서 모든 cctv를 판단할떄까지 하나의 map을 공유하도록 했다
 * 3. 다시 풀어볼것
 */

public class Main_B_G5_15683_감시 {

	private static int N,M;
	private static int[][] map;
	private static List<CCTV> list = new ArrayList<>();
	private static int result;
	
	private static class CCTV{
		int i,j,type;
		
		CCTV(int i, int j, int type){
			this.i = i;
			this.j = j;
			this.type = type;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		result = Integer.MAX_VALUE;
		
		//입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					list.add(new CCTV(i, j, map[i][j]));
				}
			}
		}
		dfs(0,map);
		System.out.println(result);
	}

	private static void dfs(int idx, int[][] prev) {
		//모든 cctv다 판단했으면
		if(idx == list.size()) {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(prev[i][j] == 0) {
						cnt++;
					}
				}
			}
			
			result = Math.min(result, cnt);
			return;
		}
		
		int[][] copy = new int[N][M];
		CCTV cctv = list.get(idx);
		int i = cctv.i;
		int j = cctv.j;
		
		switch (cctv.type) {
		case 1:	//4방향 모두 회전하면서 검사
			for(int d=0; d<4; d++) {
				for(int k=0; k<N; k++) {
					copy[k] = Arrays.copyOf(prev[k], M);
				}
				detect(copy,i,j,d);
				dfs(idx+1, copy);	//cctv감시영역을 모두 표시한 copymap
			}
			break;
			
		case 2:	//2방향
			for(int d=0; d<2; d++) {
				for(int k=0; k<N; k++) {
					copy[k] = Arrays.copyOf(prev[k], M);
				}
				detect(copy,i,j,d);		//좌상
				detect(copy,i,j,d+2);	//우하
				dfs(idx+1, copy);	//cctv감시영역을 모두 표시한 copymap
			}
			break;
			
		case 3:	//4방향
			for(int d=0; d<4; d++) {
				for(int k=0; k<N; k++) {
					copy[k] = Arrays.copyOf(prev[k], M);
				}
				detect(copy,i,j,d);		
				detect(copy,i,j,(d+1)%4);	
				dfs(idx+1, copy);	//cctv감시영역을 모두 표시한 copymap
			}
			break;
			
		case 4:	//4방향
			for(int d=0; d<4; d++) {
				for(int k=0; k<N; k++) {
					copy[k] = Arrays.copyOf(prev[k], M);
				}
				detect(copy,i,j,d);		
				detect(copy,i,j,(d+1)%4);	
				detect(copy,i,j,(d+2)%4);	
				dfs(idx+1, copy);	//cctv감시영역을 모두 표시한 copymap
			}
			break;
			
		case 5:	//회전없이 모두 검사
			for(int k=0; k<N; k++) {
				copy[k] = Arrays.copyOf(prev[k], M);
			}
			detect(copy,i,j,0);		
			detect(copy,i,j,1);		
			detect(copy,i,j,2);		
			detect(copy,i,j,3);		
			dfs(idx+1, copy);	//cctv감시영역을 모두 표시한 copymap
			break;
		}
	}

	private static void detect(int[][] cMap, int i, int j, int dir) {
		//dir 0 : 좌,  1: 상, 2: 우, 3 : 하 
		switch (dir) {
		case 0:	//좌
			for(int k=j; k>=0; k--) {
				if(cMap[i][k] == 6) {
					break;
				}
				cMap[i][k] = 9;
			}
			break;
			
		case 1:	//상
			for(int k=i; k>=0; k--) {
				if(cMap[k][j] == 6) {
					break;
				}
				cMap[k][j] = 9;
			}
			break;
			
		case 2:	//우
			for(int k=j; k<M; k++) {
				if(cMap[i][k] == 6) {
					break;
				}
				cMap[i][k] = 9;
			}
			break;
			
		case 3:	//하
			for(int k=i; k<N; k++) {
				if(cMap[k][j] == 6) {
					break;
				}
				cMap[k][j] = 9;
			}
			break;
		}
	}

}
