package com.java.algo;

 

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
		

		/*

		 * ���� ���� �׽�Ʈ ���̽��� �־����Ƿ�, ������ ó���մϴ�.

		 */

 

		for(int i=1; i<=10; i++) {
			T = sc.nextInt();
			//T = System.in.read();
			//System.out.println(T);
			int[] buildings = new int[T];
			int n = 0;
			int[] arr1 = new int[4];

			for (int test_case = 0; test_case < T; test_case++) {

				//buildings[test_case] = System.in.read();
				buildings[test_case] = sc.nextInt();
				
				//for(int l=0; l<buildings.length; l++) { System.out.println(buildings[l]); }
			}	

			for(int j=2; j< T-2; j++) {

				if((buildings[j] > buildings[j-1]) && (buildings[j] > buildings[j-2]) && (buildings[j] > buildings[j+1]) && (buildings[j] > buildings[j+2])) {

					arr1[0] = buildings[j-1];
					arr1[1] = buildings[j-2];
					arr1[2] = buildings[j+1];
					arr1[3] = buildings[j+2];

					Arrays.sort(arr1);
					//System.out.println(Arrays.toString(arr1));
					int h = arr1[3];
					//System.out.println(h);
					n += buildings[j] - h;
					//System.out.println(n);

				}

			}
			//}	
			//System.out.println(Arrays.toString(arr1));
			System.out.printf("# %d %d\n", i,n);
		}

	}

}