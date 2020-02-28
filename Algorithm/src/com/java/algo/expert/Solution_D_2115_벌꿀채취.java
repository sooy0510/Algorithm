package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 2115_벌꿀채취
 * 메모리 : 23436KB
 * 시간 : 153ms
 * 길이 : 2734
 * 풀이
 * 1. 순열과 부분집합이용
 * 2. 부분집합은 비트마스킹을 사용
 *
 */

public class Solution_D_2115_벌꿀채취 {

	static int N,M,C;
	static int[][] map;
	static boolean[][] visited;
	static int[][] pos;
	static int result;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			result = 0;
			map = new int[N][N];
			visited = new boolean[N][N];
			pos = new int[2][2];	//일꾼은 두명
		
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//일꾼위치의 순열
			getPosPerm(0,0,0);
			sb.append("#"+test+" "+result+"\n");
		}
		System.out.println(sb);
	}

	private static void getPosPerm(int r, int c, int cnt) {	//선택할 일꾼의 수
		if(cnt == 2) {
			crop();
		}else {
			//순열만들기 끝난 위치에서 새로운 순열만들기
			for(int i=r; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(check(i,j)) {
						//범위만큼 방문처리
						for(int k=j; k<j+M; k++) {
							visited[i][k] = true;
						}
						pos[cnt][0] = i;
						pos[cnt][1] = j;
						getPosPerm(r, c, cnt+1);
						
						//다음 순열을 위해 초기화
						for(int k=j; k<j+M; k++) {
							visited[i][k] = false;
						}
						pos[cnt][0] = 0;
						pos[cnt][1] = 0;
					}
				}
			}
		}
		
	}
	
	//최댓값을 찾는 집합
	private static void crop() {
		int total = 0;
		total += max(pos[0]);
		total += max(pos[1]);
		result = Math.max(total, result);
	}

	private static int max(int[] honey) {
		int max = 0;
		int sum = 0, pow = 0;	//sum = 꿀벌양, pow = 채취량
		
		for(int i = honey[1]; i<honey[1]+M; i++) {
			sum += map[honey[0]][i];
			pow += map[honey[0]][i] * map[honey[0]][i];
		}
		
		if(sum<=C) {
			//최대 벌꿀량 안넘으면 pow리턴
			return pow;
		}
		
		//최대 벌꿀량 넘었을 경우, 부분집합을 구해야함
		for(int i=0, size = 1<<M; i<size; i++) {
			sum = 0;
			pow = 0;
			for(int j=0; j<M; j++) {
				if((i & 1<<j) != 0) {
					sum += map[honey[0]][honey[1]+j];
					pow += map[honey[0]][honey[1]+j] * map[honey[0]][honey[1]+j];
				}
			}
			if(sum<=C) {
				max = Math.max(max, pow);
			}
		}
		return max;
	}
	
	private static boolean check(int r, int c) {
		if(c+M > N) return false;
		
		//범위안에서 한곳이라도 방문한적 있으면 false리턴
		for(int j=c; j<c+M; j++) {
			if(visited[r][j])return false;
		}
		
		return true;
	}

}
