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
 * 메모리 : 100812KB
 * 시간 : 1034ms
 * 길이 : 3642B
 * 풀이
 * 1. 중복순열
 * 2. 벽돌 내릴때 잘못 생각해서 시간 오래걸림
 *
 */

public class Solution_D_5656_벽돌깨기 {
	private static int N;	
	private static int M;
	private static int K;	//구슬쏜 횟수
	private static int[][] origin;
	private static int[] pick;
	private static int[][] map;
	private static int b;
	private static int MIN;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			origin = new int[N][M];
			map = new int[N][M];
			pick = new int[K];
			visited = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					int input = Integer.parseInt(st.nextToken());
					origin[i][j] = input;
					//블록갯수 세주기
					if(input != 0) {
						b++;
					}
				}
			}
			
			MIN = b;
			//중복순열 만들기
			perm(0,0);
			
			//System.out.println(MIN);
			sb.append("#"+test+" "+MIN+"\n");
		}
		System.out.println(sb);
	}

	private static void perm(int index, int selected) {
		
		if(index == K) {
			//map초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = origin[i][j];
				}
			}
			
			for(int k=0; k<K; k++) {
				int j = pick[k];
			
				for(int i=0; i<N; i++) {
					if(map[i][j] != 0) {
						//visited초기화
						for (int row = 0; row < N; row++) {
							for (int col = 0; col < M; col++) {
								visited[row][col] = false;
							}
						}
						
						shooting(i,j, map[i][j]-1);
						
						//벽돌 내리기
						for(int c=0; c<M; c++) {
							for(int r=N-1; r>=1; r--) {
								if(map[r][c] == 0) {
									for(int idx=r-1; idx>=0; idx--) {
										if(map[idx][c] == 0)continue;
										map[r][c] = map[idx][c];
										map[idx][c] = 0;
										break;
									}
								}
							}
						}
						
//						System.out.println("==========================");
//						for (int r = 0; r < N; r++) {
//							for (int c = 0; c < M; c++) {
//								System.out.print(map[r][c]+" ");
//							}
//							System.out.println();
//						}
//						System.out.println("==========================");
						
						break;
					}
				}
			}
			
			int result=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != 0)
						result++;
				}
			}
			
			MIN = Math.min(MIN, result);
			
			return;
		}
		
		for(int i=0; i<M; i++) {
			if((selected & 1<<i) == 0) {
				pick[index] = i;
				perm(index+1, selected);	//중복가능
			}
		}
	}

	//좌, 우, 상, 하
	static int[] di = {0,0,-1,1};
	static int[] dj = {-1,1,0,0};
	
	private static void shooting(int i, int j, int bomb) {
		Queue<Point9> q = new LinkedList<Point9>();
		
		map[i][j] = 0;
		q.add(new Point9(i, j, bomb));
		
		while(!q.isEmpty()) {
			Point9 now = q.poll();
			int range = now.b;
			
			//제거할것이 없음
			if(range == 0) {
				continue;
			}
			for(int d=0; d<4; d++) {
				
				for(int b=1; b<=range; b++) {
					int next_i = now.i + (di[d] * b);
					int next_j = now.j + (dj[d] * b);
					
					if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
						break;
					
					//0인 칸은 무시
					if(map[next_i][next_j] == 0)
						continue;
					
					//방문했던 곳은 무시
					if(visited[next_i][next_j])
						continue;
					
					int next_b = map[next_i][next_j]-1;
					visited[next_i][next_j] = true;
					map[next_i][next_j] = 0;
					
//					for (int r = 0; r < N; r++) {
//						for (int c = 0; c < M; c++) {
//							System.out.print(map[r][c]+" ");
//						}
//						System.out.println();
//					}
//					System.out.println();
					q.add(new Point9(next_i,next_j,next_b));
				}
			}
		}
		
		
		
		
		
	}
}

class Point9{
	int i,j,b;
	Point9(int i, int j, int b){
		this.i = i;
		this.j = j;
		this.b = b;
	}
}
