package thirdTut.java8Features;

import generics.ageComp;

public class EnusmExample {

	public static void main(String[] args) {
		
    //System.out.println(Polor.BLUE);
  
    for(Polor p:Polor.values()) {
   	System.out.println(p +"--"+p.getRgbValues());
    }
    for(Polor p:Polor.values()) {
   	System.out.println(p +"-||||-"+p.valueOf(p.toString()).getRgbValues() +"9999 ");
    }
    
    //System.out.println("0000 "+Polor.valueOf("GREEN"));
   //System.out.println(ageAll(AgeLimit.MINOR_MAX));
   //System.out.println(AgeLimit.valueOf("ADULT_MAX"));
    
    ChineseMenu.SOUP_CHICKEN.setValue(30);
    for(ChineseMenu menu:ChineseMenu.values()) {
       	System.out.println(menu.toString() +"-||||-"+menu.getPrice(menu));
        }
    
    
    
    
	}
	
	public static String ageAll(AgeLimit agelimit) {
		switch (agelimit) {
		case ADULT_MAX:
			return Polor.GREEN.getRgbValues();
			
			
		case MINOR_MAX:
			return Polor.BLUE.getRgbValues();
			

		default:
			break;
		}
		return null;
		
	}

}


	
	enum Polor{ 
		
		
		RED("rr"), GREEN("gg"), BLUE("bb"); 
		
		private String annt;

		private Polor(String annt) {
			this.annt = annt;
		}
		
		public String getRgbValues() {
			return annt;
		}
		
		};
	

