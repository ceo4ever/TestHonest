package test1;

public class Animal {
	private String name;
	private String crySound;
    private String cryYesNo; //울음소리가 있는 여부 : 울음소리 없다(N), 울림소리 있음 (Y)
	
	public Animal(String name, String crySound){
		this.name = name;
		this.crySound = crySound;
		this.cryYesNo = "Y";
	}
	
	public Animal(String name){
		this.name = name;
		this.crySound = "울음소리 없음";
		this.cryYesNo = "N";
	}
	
	public void doCry(){
		System.out.println(crySound);
	}
	
	public void callAnimal(){
		System.out.print(" "+name+" - ");
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCrySound() {
		return crySound;
	}


	public void setCrySound(String crySound) {
		this.crySound = crySound;
	}

	public String getCryYesNo() {
		return cryYesNo;
	}

	public void setCryYesNo(String cryYesNo) {
		this.cryYesNo = cryYesNo;
	}
}
