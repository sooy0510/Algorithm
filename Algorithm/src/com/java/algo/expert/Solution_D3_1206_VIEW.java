package com.java.algo.expert;

 

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

 

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/

class Solution_D3_1206_VIEW {

	public static void main(String args[]) throws Exception {

		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를
		 * 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때
		 * 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */

		//System.setIn(new FileInputStream("C://Users//이수연//Downloads/input2.txt"));

		//System.out.println(System.in.read());

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
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