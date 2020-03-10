package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 5656_벽돌깨기
 * 메모리 : 84328KB
 * 시간 : 180ms
 * 길이 : 3459B
 * 풀이
 *
 */


// 1. 중복순열 => 구슬의 위치 결정
// 2. 위치가 결정되면 설명대로 simulation
// 3. 남은 벽돌의 최소값 찾기
// 최적화 팁! =>  중간에 더이상 구슬을 안던질 경우 : 벽돌이 이미 다 깨졌을 경우
public class Solution_D_5656_벽돌깨기_2 {

	private static int T,N,C,R;
	private static int[][] map;
	private static int brickCnt;	//현재 맵에서 가지고 있는 벽돌의 개수
	private static int ans;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int test = 1; test<=T; test++) {
			sb.append("#").append(test).append(" ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			brickCnt = 0;
			
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<C; j++) {
					int input = Integer.parseInt(st.nextToken());
					map[i][j] = input;
					if(input != 0)brickCnt++;
				}
			}
			
			
			ans = brickCnt;
			//구슬을 하나씩 떨어뜨리자..좌표가 달라져야 함 => 중복순열
			//벽돌의 개수도 전달, map전달
			dropMarble(N, brickCnt,map);
			sb.append(ans);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dropMarble(int r, int brickCnt, int[][] map) {
		if(r==0) {
			//솔루션 사용
			ans = Math.min(ans, brickCnt);
			return;
		}
		
		//컬럼 순회
		for (int c = 0; c < C; c++) {
			// 1.map의 복제
			int[][] cloned = cloneMap(map);
			
			// 2.해당컬럼의 맨 처음 벽돌 가져오기
			Brick first = getFirstBrick(c, cloned);
			
			// 2-1 null --> continue
			if(first == null)continue;
			
			// 3. 구슬을 떨어뜨려서 벽돌깨기 --> 깨진 벽돌 개수 리턴
			int broken = crash(first, cloned);
			
			// 3-1 이미 다 벽돌이 깨졌다면?? 정답 =0, 종료
			if(broken >= brickCnt) {
				ans = 0;
				return;
			}
			
			// 4. 화면 정리
			cleanMap(cloned);
			
			// 5. 다음 샷 발사
			dropMarble(r-1, brickCnt-broken, cloned);
		}
	}

	private static void cleanMap(int[][] map) {
		for(int c=0; c<C; c++) {
			for(int r = R-1, nr = R - 1; r >= 0; r--) {
				if(map[r][c] != 0) {
					int temp = map[r][c];
					map[r][c] = 0;
					map[nr--][c] = temp;
				}
			}
		}
	}

	//상,하,좌,우
	static int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
	
	private static int crash(Brick first, int[][] map) {
		int broken = 0;
		
		//얻어맞은 벽돌 삭제
		map[first.row][first.col] = 0;
		broken++;
		
		//주변 벽돌에 영향 주기
		for(int p=1; p<first.pow; p++) {
			//사방 탐색
			for(int d=0; d<dirs.length; d++) {
				int nr = first.row + dirs[d][0] * p;
				int nc = first.col + dirs[d][1] * p;
				
				if(isIn(nr, nc) && map[nr][nc] != 0) {
					broken += crash(new Brick(nr, nc, map[nr][nc]), map);
				}
			}
		}
		
		return broken;
	}

	private static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	private static Brick getFirstBrick(int c, int[][] map) {
		for(int r=0; r<R; r++) {
			if(map[r][c] != 0) {
				return new Brick(r, c, map[r][c]);
			}
		}
		return null;
	}

	private static int[][] cloneMap(int[][] map) {
		int[][] temp = new int[R][C];
		for(int r=0; r<R; r++) {
			temp[r] = map[r].clone();	//deep copy, 새 객체
			//temp[r] = map[r];	//swallow copy : 단순 레퍼런스 복사
		}
		return temp;
	}
	
	static class Brick{
		int row, col, pow;
		Brick(int row, int col, int pow){
			this.row = row;
			this.col = col;
			this.pow = pow;
		}
		@Override
		public String toString() {
			return "Brick [row=" + row + ", col=" + col + ", pow=" + pow + "]";
		}
	}
}

