package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 
 * 2105_디저트카페
 * 메모리 : 29388KB
 * 시간 : 161ms
 * 길이 : 2398B
 * 풀이
 * 1. DFS
 * 2. 디저트 중복처리랑 방문처리를 디저트배열로 한번에 관리하기
 * => 디저트를 먹었다는것은 방문을 했다는 뜻
 * 3. 한 정점을 방문하고 그 정점을 기준으로 사방탐색을 끝내면 다시 되돌아가야하기 때문에 방문해제처리를 해줘야함
 *
 */

public class Solution_D_2105_디저트카페 {

	private static int[][] map;
	private static int N;
	static int max;		//디저트를 먹은 최대 개수
	static int sr,sc;		//시작좌표
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		// 입력처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
		
		//max를 0으로 초기화
		max = 0;
		// N을 입력받아 N*N배열을 생성
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		// N boolean 타입의 visit 배열을 생성
		visited = new boolean[101];	//먹은 디저트 중복 체크, 방문 체크
		//데이터 읽기
		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 탐색(dfs)
		//	 	N*N을 반복돌면서 i,j번째의 까페 부터 투어
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 시작 좌표를 i,j로 설정
				sr = i;
				sc = j;
				// set을 초기화
				// visit배열을 초기화
				Arrays.fill(visited, false);
				// dfs 탐색(i,j,d)
				dfs(i,j,0,1);
			}
		}
		// 출력 max==0?-1:max
		System.out.println("#"+test+" "+(max==0?-1:max));
		}	
	}

	//우하, 좌하, 좌상, 우상
	static int[] di = {1,1,-1,-1};
	static int[] dj = {1,-1,-1,1};
	private static void dfs(int i, int j, int dir, int cnt) {
		//	    현재 노드에 대한 방문 표시
		visited[map[i][j]] = true;
		//	    set에 현재 노드를 추가
//	    현재 방향에서부터 <4반목하면서
		for(int d=dir; d<4; d++) {
			int next_i = i + di[d];
			int next_j = j + dj[d];
		//	     다음좌표가 시작 좌표이고 cnt>=4이상인지 검사 => 한바퀴를 탐색
			if(next_i == sr && next_j == sc && cnt >=4) {
				//	      cnt를 max값과 비교해서 갱신
				if(max < cnt) {
					max = cnt;
					return;
				}
			}
		//	     다음좌표가 경계내에 있고 방문한 적이 없고, 처음 먹어보는 디저트인지 검사
			if(next_i > -1 && next_i < N && next_j>-1 && next_j < N 
					&& !visited[map[next_i][next_j]]) { //처음먹어보는 디저트인지 => 방문처리
				//	      다음노드로 이동 => 재귀 호출(dfs)
				dfs(next_i, next_j, d, cnt+1);
			}
				
		}
//	    visit배열에서 현재 노드의 대한 방문 표시한 것을 해제
		visited[map[i][j]] = false;
	}

}
