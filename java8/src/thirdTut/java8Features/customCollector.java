package thirdTut.java8Features;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class customCollector {
public static void main(String[] args) {
	Set<Claim> claims = new HashSet<>();
	
	claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
	claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
	claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
	 
	claims.add(new Claim(Claim.PRODUCT_TYPE.HOUSEHOLD));
	claims.add(new Claim(Claim.PRODUCT_TYPE.HOUSEHOLD));
	 
	ClaimProductTypeCollector<Claim> claimProductTypeCollector = new ClaimProductTypeCollector();
	claimProductTypeCollector.getRequiredTypes().add(Claim.PRODUCT_TYPE.MOTOR);
	claimProductTypeCollector.getRequiredTypes().add(Claim.PRODUCT_TYPE.HOUSEHOLD);
	Map oneClaimPerProductType = claims.stream().collect(claimProductTypeCollector);
	System.out.println(oneClaimPerProductType);
}
}