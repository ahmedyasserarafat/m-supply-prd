package thirdTut.java8Features;

public class Claim {
	 
    public enum PRODUCT_TYPE { MOTOR, HOUSEHOLD, TRAVEL}
 
    private PRODUCT_TYPE productType;
 
    public Claim(PRODUCT_TYPE productType) {
        this.productType = productType;
    }
 
    public PRODUCT_TYPE getProductType() {
        return productType;
    }
 
    public void setProductType(PRODUCT_TYPE productType) {
        this.productType = productType;
    }
 
}