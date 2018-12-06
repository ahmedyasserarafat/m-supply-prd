package store.oauth;

import java.io.Serializable;

public class AuthenticatedUserDetails implements Serializable
{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private String userNo;
private String firstName;
private String middleName;
private String lastName;
private String loginId;
private String gender;
private String dob;	
private String emaiId;
private String marital_Satatus;
private String mobileNo;	
private String nationality;	
private String city;
private String address1;
private String address2;	

public String getUserNo()
{
	return userNo;
}
public void setUserNo(String userNo)
{
	this.userNo = userNo;
}
public String getLoginId()
{
	return loginId;
}
public void setLoginId(String loginId)
{
	this.loginId = loginId;
}
public String getMobileNo()
{
	return mobileNo;
}
public void setMobileNo(String mobileNo)
{
	this.mobileNo = mobileNo;
}
public String getNationality()
{
	return nationality;
}
public void setNationality(String nationality)
{
	this.nationality = nationality;
}
public String getAddress1()
{
	return address1;
}
public void setAddress1(String address1)
{
	this.address1 = address1;
}
public String getAddress2()
{
	return address2;
}
public void setAddress2(String address2)
{
	this.address2 = address2;
}
public String getCity()
{
	return city;
}
public void setCity(String city)
{
	this.city = city;
}
public String getGender()
{
	return gender;
}
public void setGender(String gender)
{
	this.gender = gender;
}
public String getMarital_Satatus()
{
	return marital_Satatus;
}
public void setMarital_Satatus(String marital_Satatus)
{
	this.marital_Satatus = marital_Satatus;
}
public String getDob()
{
	return dob;
}
public void setDob(String dob)
{
	this.dob = dob;
}

public String getFirstName()
{
	return firstName;
}
public void setFirstName(String firstName)
{
	this.firstName = firstName;
}
public String getMiddleName()
{
	return middleName;
}
public void setMiddleName(String middleName)
{
	this.middleName = middleName;
}
public String getLastName()
{
	return lastName;
}
public void setLastName(String lastName)
{
	this.lastName = lastName;
}
public String getEmaiId()
{
	return emaiId;
}
public void setEmaiId(String emaiId)
{
	this.emaiId = emaiId;
}
}

