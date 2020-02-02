package com.java.algo.hw;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution32 {

	static String[][] arr;

	static int[] px = {1,0,-1,0};
	static int[] py = {0,1,0,-1};
	// 동:1, 남:2, 서:3, 북:4
	
	private static int game(int y, int x, int jump) {	//행열

		int money = 0;
		int point_y = y;
		int point_x = x;
		String point;
		
		for(int k=0; k<jump; k++) {
			point = arr[point_y][point_x];
			int p = Character.getNumericValue(point.charAt(0));	//방향
			int j = Character.getNumericValue(point.charAt(1));	//점프칸수

			point_y = point_y + py[p-1]*j;
			point_x = point_x + px[p-1]*j;

			// 함정에 빠지거나 경계 벗어나면 상금못받음
			if(arr[point_y][point_x] == null || arr[point_y][point_x].equals("0") || point_y > arr.length || point_y < 1 || point_x > arr.length || point_x < 1){
				return 0;
			}
		}
		
		// 마지막 좌표에 적어진 숫자에 100을 곱한 금액이 상금
		money = Integer.parseInt(arr[point_y][point_x])*100;
		return money;

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("Solution32.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int Y;
		int X;
		int N;
		for (int test = 1; test <= T; test++) {
			int result = 0;
			Y = sc.nextInt() + 1;
			X = sc.nextInt() + 1;
			N = sc.nextInt();
			arr = new String[Y][X];

			// 숫자판 정보
			for (int i = 1; i < Y; i++) {
				for (int j = 1; j < X; j++) {
					arr[i][j] = sc.next();
				}
			}


			int[] parts = new int[N * 3];

			// 참가자의 시작위치(행, 열), 횟수
			for (int i = 0; i < N * 3; i++) {
				parts[i] = sc.nextInt();
			}
			

			// 함정 수, 함정의 좌표들
			int bomb = sc.nextInt();
			for (int i = 0; i < bomb; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				arr[y][x] = "0";
			}

			// 게임 시작
			for (int i = 0; i < N*3; i=i+3) {
				// 시작 행, 시작 열, 점프 수
				result += game(parts[i], parts[i + 1], parts[i + 2]);
			}
			
			result -= 1000*N;
			System.out.printf("#%d %d\n", test, result);

		}

	}

}