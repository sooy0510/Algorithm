package com.java.algo.expert;

import java.util.LinkedList;
import java.util.Scanner;

/*
 * 4013_특이한자석
 * 메모리 : 27988KB
 * 시간 : 172ms
 * 길이 : 1502B
 * 풀이
 * 1. 자석번호에 따라 어느방향으로 돌릴지 결정하는 코드가 바로 생각나지 않았다. 다시 풀어보기
 */

public class Solution_D_4013_특이한자석 {
	static LinkedList<Integer>[] magnet;
	static int K; 	//자석을 임의로 돌리는 횟수 K
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test=1; test<=T; test++) {
			K = sc.nextInt();
			magnet = new LinkedList[5];	//1,2,3,4 자석
			
			for(int i=1; i<=4; i++) {
				magnet[i] = new LinkedList<>();
				for(int j=0; j<8; j++) {
					magnet[i].add(sc.nextInt());	//i번 자석의 톱니정보 입력받기
				}
			}
			
			for(int k=0; k<K; k++) {	//k번 자석을 돌리기
				int mNum = sc.nextInt();	//몇번 자석을?
				boolean dir = sc.nextInt()==1?true:false;	//1이면 시계 true, 아니면 반시계 false
				
				//자기 양옆을 회전시킬지 말지 결정
				
				//자기 왼쪽에 자석이 있는 애들 => 회전시켜야할 자석은 mNum-1번 자석이고 6번 index를 확인해야됨
				if(mNum > 1) {
					action(mNum, 6, dir);
				}
				
				//2,3번은 mNum < 4인 조건에서 한번 더 들기때문에 방향 한번 반대로
				//중간에 낀 2,3번 자석은 rotate 두번 당하니까 한번 무효시키기
				if(mNum > 1 && mNum < 4) {
					rotate(mNum, !dir);	//반대방향으로 한번 돌려놔서 원상복구
				}
				
				if(mNum < 4) {	//자기 오른쪽에 자석이 있는 애들
					action(mNum, 2, dir);
				}
			}
			
			//점수계산
			int ans = 0;
			for(int i=1; i<=4; i++) {	//s극이 1
				ans += (magnet[i].get(0) * (1<<(i-1)));
			}
			System.out.println("#"+test+" "+ans);
		}
	}
	
	static void action(int mNum, int idx, boolean dir) {	//자석번호, 오른쪽 왼쪽(2,6), 시계/반시계
		int mNext = mNum + (idx == 2? +1 : -1); 	//오른쪽 톱니바퀴를 돌려야하면 +1, 왼쪽이면 -1 => 내가 영향을 미칠 다음 자석이 됨
		//현재자석의 2번은 다음자석 6번, 현재자석의 6번은 다음자석 2번
		if(mNext > 0 && mNext < 5 && magnet[mNum].get(idx) != magnet[mNext].get((idx+4)%8)) {
			action(mNext, idx, !dir);	//옆자석 번호, 왼오 방향은 유지, 시계/반시계 방향은 반대
		}
		rotate(mNum, dir);	//옆 자석을 돌릴지 말지 확인하고 났으면 이제 현재자석도 돌리기
	}
	
	static void rotate(int mNum, boolean dir) {	//mNum번 자석을 true:시계, false:반시계
		if(dir) {	//시계방향
			magnet[mNum].addFirst(magnet[mNum].removeLast());	//맨 뒤에 있는걸 때서 앞으로 밀어넣음
		}else {
			magnet[mNum].addLast(magnet[mNum].removeFirst());	//맨 앞에 있는걸 맨 뒤에 붙이기
		}
	}
}

