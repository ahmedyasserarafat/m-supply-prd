package thirdTut.java8Features;

public enum ChineseMenu {
 SOUP_CHICKEN(20),
 SOUP_VEG(15);
	
	private int value;

	private ChineseMenu(int price) {
		this.value = price;
	}
	public int getPrice(ChineseMenu menu) {
		return menu.value;
	}
	
	public void setValue(int value) {
		this.value=value;
	}
}
