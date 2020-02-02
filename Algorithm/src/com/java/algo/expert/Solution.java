package com.java.algo.expert;

 

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

 

/*

����ϴ� Ŭ�������� Solution �̾�� �ϹǷ�, ������ Solution.java �� ����� ���� �����մϴ�.

�̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� �����غ� �� �ֽ��ϴ�.

*/

class Solution {

	public static void main(String args[]) throws Exception {

		/*

		 * �Ʒ��� �޼ҵ� ȣ���� ������ ǥ�� �Է�(Ű����) ��� input.txt ���Ϸκ��� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�. �������� �ۼ��� �ڵ带

		 * �׽�Ʈ �� ��, ���Ǹ� ���ؼ� input.txt�� �Է��� ������ ��, �� �ڵ带 ���α׷��� ó�� �κп� �߰��ϸ� ���� �Է��� ������ ��

		 * ǥ�� �Է� ��� ���Ϸκ��� �Է��� �޾ƿ� �� �ֽ��ϴ�. ���� �׽�Ʈ�� ������ ������ �Ʒ� �ּ��� ����� �� �޼ҵ带 ����ϼŵ� �����ϴ�.

		 * ��, ä���� ���� �ڵ带 �����Ͻ� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ� ó�� �ϼž� �մϴ�.

		 */

		//System.setIn(new FileInputStream("C://Users//�̼���//Downloads/input2.txt"));

		//System.out.println(System.in.read());

		/*

		 * ǥ���Է� System.in ���κ��� ��ĳ�ʸ� ����� �����͸� �о�ɴϴ�.

		 */

		Scanner sc = new Scanner(System.in);

		int T;
		int[][] buildings = new int[10][1000];
 

		for(int i=0; i<10; i++) {
			T = sc.nextInt();

			for (int test_case = 0; test_case < T; test_case++) {
				buildings[i][test_case] = sc.nextInt();
			}	
		}
		
		
		for(int i=0; i<10; i++) {
			int n = 0;
			int[] arr1 = new int[4];
			for(int j=2; j< buildings[i].length-2; j++) {

				if((buildings[i][j] > buildings[i][j-1]) && (buildings[i][j] > buildings[i][j-2]) && (buildings[i][j] > buildings[i][j+1]) && (buildings[i][j] > buildings[i][j+2])) {

					arr1[0] = buildings[i][j-1];
					arr1[1] = buildings[i][j-2];
					arr1[2] = buildings[i][j+1];
					arr1[3] = buildings[i][j+2];

					Arrays.sort(arr1);
					int h = arr1[3];
					n += buildings[i][j] - h;
				}

			}
			System.out.println();
			System.out.printf("#%d %d", i+1,n);
		}

	}

}