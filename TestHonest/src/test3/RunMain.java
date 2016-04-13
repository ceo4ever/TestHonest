package test3;


/**
 * 문제와 관련하여 상환 도중 대출자가 중도상환 하는 경우에 상환 스케줄이 변경 되어 출력하는 프로그램을 작성 해주세요
 * a. 기본적인 구조는 2 번 문제와 동일하며 대출자가 전체 대출기간 중 ⅔ 가 경과된 이후 납입일에 잔여 원금의 60%를 상환하는 경우에 대한 경우를 상정
 * b. 출력은 중도상환 전 매 월별 상환금액(원금, 이자) 출력 후 중도상환 이후에 변경된 월별 상환금액(원금, 이자) 출력
 *
 *<< 로직 설명 
 *0.대출기간은 1년단위로만 전제함 (즉, 12개월, 24개월, 36개월,...)
 *1.원금은 균등하게 상환함
 *2.이자는 월이자로 계산을 함 (무조건 12개월로 나눔)
 *3.이자는 일자리에서 절사 처리를 함
 *4.마지막 회차 상환 원금에 자투리 원금을 처리함
 *
 *<< 검증
 *- 상환 원금을 모두 합쳤을때에 대출원금과 같은지 확인함 
 *- 이자는 적수를 계산하여 대략적 평균 이자를 계산해서 대출금리하고 비슷한지를 검증함
 *-  a.한달을 30일로 전제로 함 
 *
 *<< 호출 설명
 *java test3.RunMain  대출금액 대출이자 대출기간
 * ==> java test3.RunMain 20000000 4.8 12
 */
public class RunMain {

	public static void main(String[] args) {
		int MONTHLY_COUNT   = 30; //한달 일수
		int LOAN_TERM_COUNT = 12; //대출기간
		
		if(args.length != 3){
			System.out.println("입력 정보를 확인바랍니다(입력예시:대출금 대출금리(%) 대출기간(개월수) => 200000 2.5 12");
			System.exit(-1);
		}
		
		long   paramAmt     = Long.parseLong(args[0]);     //대출금
		double paramIntRate = Double.parseDouble(args[1]); //대출금리
		int    paramPeriod  = Integer.parseInt(args[2]);   //대출기간
		
	    if(paramPeriod % LOAN_TERM_COUNT != 0){
	    	System.out.println("대출기간을 확인 바랍니다.1년 단위로만 가능합니다");
	    	System.out.println("예) 12개월, 24개월, 36개월, 48개월");
			System.exit(-1);
	    }
		
		long   amtRepayAfter = paramAmt;
		
	    long   totalAmt = 0, totalIntAmt = 0;
	    long   sumLoanAmt=0; //대출기간동안 적수 합계
	    
	    long repayAmt=0, intAmt=0; //상환원금, 상환이자
	    long amtMidRepayAfter=0;   //중도 상환후 잔액
	    
	    System.out.println(">>> 상환 계산 조건");
	    System.out.println("1.원금은 균등하게 상환함");
	    System.out.println("2.이자는 무조건 1/12로 계산함");
	    System.out.println("3.이자는 일자리에서 절사\n");
	    System.out.println("4.중도상환 시점(전체대출기간*2/3)은 소수점 이하 절사하여 구함\n");
	    System.out.println("5.중도상환 이후에는 당시 원금잔액을 대출원금으로 인식하고 남은 기간을 대출기간으로 상환처리를 함\n");
    	System.out.println("============== 상환 일정 ==============");
    	//System.out.println("회차  상환원금      상환이자     상환후원금");
    	System.out.println("회차  상환원금      상환이자");
	    for(int i = 0; i < paramPeriod; i++){
	    	//적수 누계 금액
	    	sumLoanAmt += (amtRepayAfter * MONTHLY_COUNT); //일수를 무조건 30일로 가정함
	    	
	    	int midPeriod = (int)(paramPeriod*2/3); //중도상환 시점 : 소수점 이하는 모두 절사를 함
	    	int lastPeriod = paramPeriod - midPeriod;
	    	
	    	if(i+1 == midPeriod){
	    		//중도 상환 처리
	    		//다시 대출잔액의 60%를 상환함
	    		repayAmt = amtRepayAfter * 60/100;
	    		repayAmt -= repayAmt % 10 ; //일자리에서 절사 
	    		
	    		amtMidRepayAfter = amtRepayAfter - repayAmt; //중도상환 후 원금
	    	}else if(i+1 > midPeriod){
	    		//중도상환 이후 회차 상환 처리
	    		//매월 상환 원금
		    	repayAmt = amtMidRepayAfter / lastPeriod;
		    	repayAmt -= repayAmt % 10 ; //일자리에서 절사 
		    	
		        //마지막 회차에 자투리 금액을 원금에 합침
		        if((i+1) == paramPeriod){
		        	repayAmt = repayAmt + (amtMidRepayAfter - repayAmt * lastPeriod);
		        }
	    	}else{
	    		//중도상환 이전 회차 상환 처리
	            //매월 상환 원금
		    	repayAmt = paramAmt / paramPeriod;
		    	repayAmt     -= repayAmt % 10 ; //일자리에서 절사 
	    	}
	    	
	    	//매월 상환 이자금액
	    	intAmt   = doInterest(amtRepayAfter, paramIntRate);
            
	    	//상환후원금
	    	amtRepayAfter -= repayAmt;
	    	
	    	//출력
	    	//System.out.format("%2d%10d%15d%15d   %n",i+1, repayAmt, intAmt, amtRepayAfter);
	    	System.out.format("%2d%10d%15d   %n",i+1, repayAmt, intAmt);
	        
	    	totalAmt += repayAmt;
	    	totalIntAmt += intAmt;
	    }
	    
	    //검증 
	    long avgLoanAmt = sumLoanAmt / (paramPeriod * MONTHLY_COUNT); //연평균 일일 대출금액
	    double avgIntRate = (double)totalIntAmt / (double)avgLoanAmt * 100; //연평균 대출금리
	    avgIntRate = avgIntRate / (paramPeriod / LOAN_TERM_COUNT);
	    
	    System.out.println("\n\n============== 검증 ==============");
	    System.out.format("상환원금합계:%10d 상환이자합계:%10d   %n",totalAmt, totalIntAmt);
	    System.out.format("적수        :%10d 평균대출금액:%10d 평균이자:%.2f %n%n", sumLoanAmt, avgLoanAmt, avgIntRate);
	}
	
	

    /**
     * 이자 계산
     * 무조건 1/12로 계산을 함
     * 일자리 이하 절사
     * @param amt
     * @param intRate
     * @return
     */
    private static long doInterest(long amt, double intRate){
        long intAmt = (long) (amt * intRate/100 / 12);
        intAmt -= intAmt % 10;
    	
    	return intAmt;
    }

}
