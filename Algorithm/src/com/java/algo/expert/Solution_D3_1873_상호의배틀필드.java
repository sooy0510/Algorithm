package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1873_상호의 배틀필드
 * 메모리 : 19744KB
 * 실행시간 : 109ms
 * 코드길이 : 3108
 * 풀이
 * 1. 델타 이용하기
 * 2. 입력되는 map정보에서 전차 위치 찾기
 * 3. 입력되는 명령정보에 따라, 전차 방향바꿔주고 move메서드로 전차움직이기
 * 4. move()
 * - 새로 이동할 자리에 현재 전차 갖다 놓기
 * - 원래였던 자리 평지로 바꾸기
 * - 전차 위치 갱신
 * => 전차 갖다 놓는 과정과 전차 위치 갱신 혼동하지 말것!
 * 5. shoot()
 * - 벽 만날때까지 날아가야 하므로 while문
 *
 */

public class Solution_D3_1873_상호의배틀필드 {

	static char[][] map;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int x=0, y=0;
	static int next_x;
	static int next_y;
	static int dir;
	static StringBuilder sb = new StringBuilder();;
	
	static int H;
	static int W;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Solution1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); 
			W = Integer.parseInt(st.nextToken()); 
			
			map = new char[H][W];
			
			//전차위치
			int[] pos = new int[2];
			
			for(int i=0; i<H; i++) {
				String input = br.readLine();
				map[i] = input.toCharArray();
				for(int j=0; j<W; j++) {
					switch (map[i][j]) {
					case '^':
					case 'v':
					case '<':
					case '>':
						pos[0] = i;
						pos[1] = j;
						break;
					}
				}
			}
			
			
			int N = Integer.parseInt(br.readLine()); //명령개수
			String info = br.readLine();
			
			for(int i=0; i<N; i++) {
				char m = info.charAt(i);
				
				switch (m) {
				case 'U':
					map[pos[0]][pos[1]] = '^';
					move(pos,0);
					break;

				case 'D':
					map[pos[0]][pos[1]] = 'v';
					move(pos,1);
					break;
					
				case 'L':
					map[pos[0]][pos[1]] = '<';
					move(pos,2);
					break;
					
				case 'R':
					map[pos[0]][pos[1]] = '>';
					move(pos,3);
					break;

				default:	//S : 포탄발사
					shoot(pos);
					break;
				}
			}
			
			
			
			sb.append("#"+test+" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					//System.out.print(map[i][j]);
					sb.append(map[i][j]);
				}
				//System.out.println();
				sb.append("\n");
			}
			//System.out.println();
			
		}
		System.out.println(sb);
		
	}

	private static void shoot(int[] pos) {
		int dir = 0;
		// 현재 전차 모양보고 방향을 결정
		switch (map[pos[0]][pos[1]]) {
		case '^':
			dir = 0;
			break;
			
		case 'v':
			dir = 1;
			break;
			
		case '<':
			dir = 2;
			break;
			
		case '>':
			dir = 3;
			break;
		}
		
		int next_y = pos[0];
		int next_x = pos[1];
		
		while(true) {
			next_y = next_y + dy[dir];
			next_x = next_x + dx[dir];
			
			if(next_x < 0 || next_x >= W || next_y < 0 || next_y >= H) {
				return;
			}
			
			//벽돌벽 만나면 뿌심
			if(map[next_y][next_x] == '*') {
				map[next_y][next_x] = '.';
				return;
			}else if(map[next_y][next_x] == '#'){	//강철벽 만나면 종료
				return;
			}
			
		}
	
	}

	private static void move(int[] pos, int dir) {
		int next_x;
		int next_y;
		
		
		next_x = pos[1] + dx[dir];
		next_y = pos[0] + dy[dir];
		if(next_x < 0 || next_x >= W || next_y < 0 || next_y >= H) {
			return;
		}
		
		// 평지라면 그 칸으로 이동
		if(map[next_y][next_x] == '.') {
			//새로 이동하는 자리에 현재 전차 갖다 놓기
			map[next_y][next_x] = map[pos[0]][pos[1]];
			
			//원래였던 자리 평지로 바꿈
			map[pos[0]][pos[1]] = '.';	//지나간 자리
			
			//전차 위치 갱신
			pos[0] = next_y;
			pos[1] = next_x;
		}
		
	}

}
