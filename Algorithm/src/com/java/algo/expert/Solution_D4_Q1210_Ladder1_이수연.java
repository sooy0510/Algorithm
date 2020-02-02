package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution_D4_Q1210_Ladder1_�̼��� {
	static int x = 98;
	static int y = 0;
	static int up = 0;
	static int left = 0;
	static int right = 0;
	static int[][] arr = new int[100][100]; // �Է¹迭

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		final int T = 10;
		int idx = 0; // ��½� ���̽����̽� ��ȣ
		StringTokenizer st;
		char p = 'u';

		int end = 0;

		for (int test_case = 1; test_case <= T; test_case++) {
			// Arrays.fill(arr[0],0);
			idx = Integer.parseInt(br.readLine());

			for (int i = 0; i < arr.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < arr[0].length; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 2) {
						end = j;
					}
				}
			}


			// Ž��
			x = 98;
			y = end;

			while (x != 0) {

				getVal();
				if (p == 'u') {
					if (left == 0 && right == 0) { // left, right �Ѵ� 0�̸� ���θ� ����
						x--;
						p = 'u';
					} else if (left == 1 && right == 0) { // left
						y--;
						p = 'l';
					} else { // right
						y++;
						p = 'r';
					}
				} else if (p == 'l') {
					if (left == 0) { // up
						x--;
						p = 'u';
					} else { // left
						y--;
						p = 'l';
					}
				} else {
					if (right == 0) {
						x--;
						p = 'u';
					} else { // right
						y++;
						p = 'r';
					}

				}

			}

			bw.write("#" + test_case+" "+y+"\n");

		}
		
		bw.flush();
		bw.close();
	}

	private static void getVal() {
		// �ǳ�(y=0�̰ų� 99�� �����..?
		if (y == 0) { // ��, �����ʹۿ� Ž�� ����
			up = arr[x - 1][y];
			left = 0;
			right = arr[x][y + 1];
		} else if (y == 99) {
			up = arr[x - 1][y];
			left = arr[x][y - 1];
			right = 0;
		} else {
			up = arr[x - 1][y - 1];
			left = arr[x][y - 1];
			right = arr[x][y + 1];
		}
	}
}