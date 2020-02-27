package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 14499_주사위굴리기
 * 메모리 : 13480KB
 * 시간 : 88ms
 * 길이 : 3144B
 * 풀이
 * 1. dice배열의 (3,1)위치는 (1,3)과 똑같은 아랫면이므로 (1,3)을 임의로 추가해서 주사위 도면을 구현했다
 * 2. 동,서 방향일때는 열중심 이동이므로, (1,3)의 값을 (3,1)에 복사하고,
 * 	   남,북 방향일때는 행중심 이동이므로, (3,1)의 값을 (1,3)에 복사해서 주사위 아랫면의 값을 항상 같게 관리햇다
 */

public class Main_B_G5_14499_주사위굴리기 {

	static int[][] dice;
	static int[][] map;
	static int M,N,K;
	static int i,j;
	static int top, bottom;		//주사위 윗면, 아랫면
	static Queue<Integer> commands = new LinkedList<Integer>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		i = Integer.parseInt(st.nextToken());
		j = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		
		map = new int[N][M];
		map[i][j] = 0;	//주사위 놓은 칸은 항상 0
		dice = new int[4][4];
		top = dice[1][1];
		bottom = dice[3][1];
		
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0; m<M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		// 동=1, 서=2, 북=3, 남=4
		st = new StringTokenizer(br.readLine());
		for(int k=0; k<K; k++) {
			commands.add(Integer.parseInt(st.nextToken()));
		}

		dice();
		System.out.println(sb);
	}

	//동, 서, 북, 남
	static int[] di = {0,0,0,-1,1};
	static int[] dj = {0,1,-1,0,0};
	
	private static void dice() {
		int dir;
		while(!commands.isEmpty()) {
			// 동=1, 서=2, 북=3, 남=4
			dir = commands.poll();
			//System.out.println("command : "+dir);
			int next_i = i + di[dir];
			int next_j = j + dj[dir];
			//System.out.println("next_i = "+next_i+" / next_j = "+next_j);
			
			//범위 벗어나면 명령무시
			if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
				continue;
			
			i = next_i;
			j = next_j;
			
			//주사위 도면 바꿔주기
			int tmp;
			switch (dir) {
			case 1:
				tmp = dice[1][3];
				for (int d = 2; d >= 0; d--) {
					dice[1][d+1] = dice[1][d];
				}
				dice[1][0] = tmp;
				
				//동,서 는 dice[1][3]을 dice[3][1]에 복사
				dice[3][1] = dice[1][3];
				break;
				
			case 2:
				tmp = dice[1][0];
				for (int d = 0; d < 3; d++) {
					dice[1][d] = dice[1][d+1];
				}
				dice[1][3] = tmp;
				
				//동,서 는 dice[1][3]을 dice[3][1]에 복사
				dice[3][1] = dice[1][3];
				break;
				
			case 3:
				tmp = dice[0][1];
				for (int d = 0; d < 3; d++) {
					dice[d][1] = dice[d+1][1];
				}
				dice[3][1] = tmp;
				
				//남,북은 dice[3][1]을 dice[1][3]에 복사
				dice[1][3] = dice[3][1];
				break;
				
			case 4:
				tmp = dice[3][1];
				for (int d = 2; d >= 0; d--) {
					dice[d+1][1] = dice[d][1];
				}
				dice[0][1] = tmp;
				
				//남,북은 dice[3][1]을 dice[1][3]에 복사
				dice[1][3] = dice[3][1];
				break;
			}
			
			top = dice[1][1];
			bottom = dice[1][3]; //또는 dice[3][1]

			//이동한 칸의 수가 =0 -> 주사위의 바닥면에 쓰여있는 수가 칸에 복사
			if(map[next_i][next_j] == 0) {
				map[next_i][next_j] = bottom;
			}else {	
				//0이 아니면 => 칸에 쓰여있는 수가 주사위의 바닥면으로 복사되고, 칸에 쓰이는 수는 0
				dice[3][1] = map[next_i][next_j];
				dice[1][3] = map[next_i][next_j];
				map[next_i][next_j] = 0;
			}

			sb.append(dice[1][1]+"\n");
		}
	}

}
