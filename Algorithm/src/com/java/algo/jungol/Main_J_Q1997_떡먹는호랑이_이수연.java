package com.java.algo.jungol;

import java.util.Scanner;

public class Main_J_Q1997_���Դ�ȣ����_�̼��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();	//�ҸӴϰ� �Ѿ�� ��
		int K = sc.nextInt();	//�� ����
		
		int[] arr = new int[D+1];
		
		// x,y �Ѵ� 0�̸� ��� �迭 ���� 0�̹Ƿ� ù°���� ������ 0����
		for(int x=0; x<100; x++) {
			arr[1] = x; 
			for(int y=1; y<100; y++) {
				arr[2] = y;
				
				// D°�� ������ ���ؼ� arr[D]�� �ֱ�
				for(int i=3; i<D+1; i++) { 
					arr[i] = arr[i-2]+arr[i-1];
				}
				
				// ���� : ��°�� �������� ù°�� ���������� ũ��, �������� 10�̻��̰� K���϶� ���
				if((x<y) &&(arr[D] >= 10) && (arr[D] == K)) {
					System.out.println(x);
					System.out.println(y);
					return;
				}
			}
		}
	}
}
