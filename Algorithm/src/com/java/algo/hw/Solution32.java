package com.java.algo.hw;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution32 {

	static String[][] arr;

	static int[] px = {1,0,-1,0};
	static int[] py = {0,1,0,-1};
	// ��:1, ��:2, ��:3, ��:4
	
	private static int game(int y, int x, int jump) {	//�࿭

		int money = 0;
		int point_y = y;
		int point_x = x;
		String point;
		
		for(int k=0; k<jump; k++) {
			point = arr[point_y][point_x];
			int p = Character.getNumericValue(point.charAt(0));	//����
			int j = Character.getNumericValue(point.charAt(1));	//����ĭ��

			point_y = point_y + py[p-1]*j;
			point_x = point_x + px[p-1]*j;

			// ������ �����ų� ��� ����� ��ݸ�����
			if(arr[point_y][point_x] == null || arr[point_y][point_x].equals("0") || point_y > arr.length || point_y < 1 || point_x > arr.length || point_x < 1){
				return 0;
			}
		}
		
		// ������ ��ǥ�� ������ ���ڿ� 100�� ���� �ݾ��� ���
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

			// ������ ����
			for (int i = 1; i < Y; i++) {
				for (int j = 1; j < X; j++) {
					arr[i][j] = sc.next();
				}
			}


			int[] parts = new int[N * 3];

			// �������� ������ġ(��, ��), Ƚ��
			for (int i = 0; i < N * 3; i++) {
				parts[i] = sc.nextInt();
			}
			

			// ���� ��, ������ ��ǥ��
			int bomb = sc.nextInt();
			for (int i = 0; i < bomb; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				arr[y][x] = "0";
			}

			// ���� ����
			for (int i = 0; i < N*3; i=i+3) {
				// ���� ��, ���� ��, ���� ��
				result += game(parts[i], parts[i + 1], parts[i + 2]);
			}
			
			result -= 1000*N;
			System.out.printf("#%d %d\n", test, result);

		}

	}

}