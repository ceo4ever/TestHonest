package test1;

import java.util.ArrayList;
import java.util.Random;


/**
 * 고양이, 강아지, 호랑이, 돌고래, 금붕어, 앵무새의 울음소리를 출력하는 프로그램 작성해주세요
 * a. 금붕어는 울음소리가 없다.
 * b. 돌고래는 초음파를 사용해서 인간의 가청 범위를 벗어난다.
 * c. 앵무새는 직전의 동물의 울음소리를 따라한다.
 *    i. 울음소리는 인간의 가청 범위의 소리만 낼 수 있다.
 *    ii. 직전의 동물이 없거나 혹은 낼 수 없는 소리의 경우 자기 고유의 소리를 낸다.
 * d. 테스트는 Main 클래스에서 랜덤으로 20 마리의 동물을 생성하여 소리를 낼 수 있게 해준다.
 *
 *
 * << 로직 설명 
 * 1.동물들에 대해서 기본 객체를 생성함
 * 2.그 객체를 ArrayList에 추가하여 랜덤하게 호출을 함
 * 3.랜덤으로 20마리를 생성함
 *   - 앵무새만 빼고 나머지 동물들은 기본 객체를 그대로 사용함 
 *   - 앵무새가 뽑인 경우에는 이전 동물에 따라 소리가 변경되므로 객체를 새롭게 생성함
 *   - 직전 동물이 앵무새인 경우에는 앵무새 고유의 소리를 낸다
 */

public class RunMain {

	public static void main(String[] args) {
		
		//1.생성할 동물들의 기본 객체를 생성함
		Animal cat = new Animal("고양이", "야옹야옹");
		Animal dog = new Animal("강아지", "멍멍");
		Animal tiger = new Animal("호랑이", "어~흥~");
		Animal dolphin = new Animal("돌고래", "초음파(피휘이~피휘이~)");
		Animal fish = new Animal("금붕어");
		Animal bird = new Animal("앵무새", "쪼루~쪼루~쪼루~");
		
		ArrayList<Animal> aniList = new ArrayList<Animal>();
		aniList.add(cat);
		aniList.add(dog);
		aniList.add(tiger);
		aniList.add(dolphin);
		aniList.add(fish);
		aniList.add(bird);
		
		//2.랜덤으로 동물을 20마리 생성
		//  - 앵무새만 빼고 나머지 동물들은 기본 객체를 그대로 사용함
		//  - 앵무새가 뽑인 경우에는 객체를 새롭게 생성함
	    Random random = new Random();
	    Animal befAni = null;
	    Animal tempAni = null;
	    ArrayList<Animal> showAniList = new ArrayList<Animal>();
	    for(int i = 0; i < 20; i++){
	    	tempAni = aniList.get(random.nextInt(6));
	    	//앵무새인 경우
	    	//직전 동물이 없는 경우와 울음소리가 없는 경우에는 앵무새 원래 소리를 낸다
	    	//직적 동물이 있고 울음소리가 있으면 그 동물의 울음소리를 따라한다
	        if("앵무새".equals(tempAni.getName())){
	        	tempAni = new Animal("앵무새", "쪼루~쪼루~쪼루~"); //앵무새 객체를 새로 생성함
	        	
	        	if(befAni != null){
	        		if(!"앵무새".equals(befAni.getName()) && "Y".equals(befAni.getCryYesNo())){
	        			tempAni.setCrySound(befAni.getCrySound());
		        	}
	        	}
	        		
	        }
	    	showAniList.add(i, tempAni);
	    	befAni = tempAni;
	    }
	    
	    //3.20마리 동물을 출력함
	    for(int j=0; j < showAniList.size(); j++){
	    	System.out.print("["+(j+1)+"]");
	    	showAniList.get(j).callAnimal();
	    	showAniList.get(j).doCry();
	    }
	}

}
