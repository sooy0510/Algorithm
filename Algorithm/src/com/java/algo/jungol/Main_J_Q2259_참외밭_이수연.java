package com.java.algo.jungol;

import java.util.Scanner;

public class Main_J_Q2259_���ܹ�_�̼��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();
		int[] arr = new int[6];
		
		int h =0;
		int w =0;
		int hh = 0;
		int ww = 0;
		
		for(int i=0; i<6; i++) {
			sc.nextInt();
			arr[i] = sc.nextInt();
		}
		
		// ��ü �簢���� ����, �ʺ�
		for(int i=0; i<6; i++) {
			if(i%2==0) {
				if(w < arr[i]) {
					w = arr[i];
				}
			}else {
				if(h < arr[i]) {
					h = arr[i];
				}
			}
		}
		
		// �Ѻ�(array[i])�� �������� array[i-1]+array[i+1]�� ��ü �簢���� 
		// �ʺ� �Ǵ� ������ ��� �� �簢�� �ǹ�
		for(int i=0; i<6; i++) {
			if(i%2 == 0) {
				if(h == arr[(i+5)%6] + arr[(i+1)%6]) {
					ww = arr[i];
				}
			}else {
				if(w == arr[(i+5)%6] + arr[(i+1)%6]) {
					hh = arr[i];
				}
			}
		}
		System.out.println((w * h - ww * hh) * cnt);
	}

}
