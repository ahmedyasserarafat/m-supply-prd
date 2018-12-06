package thirdTut.java8Features;

public enum AgeLimit {
  MINOR_MAX(17),ADULT_MAX(60);
	
	private int agelimit;

	private AgeLimit(int agelimit) {
		this.agelimit = agelimit;
	}
	
	
}