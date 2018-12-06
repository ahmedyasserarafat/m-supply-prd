package com.plural.java8;
public class Java8Tester {

   public static void main(String args[]) {
	   FourWheeler vehicle = new Car();
      vehicle.print();
      vehicle.cacskat();
   }
}

interface Vehicle {

   default void print() {
      System.out.println("I am a vehicleeee!");
   }
  
   static void blowHorn() {
      System.out.println("Blowing horn!!!");
   }
   void cacskat();
}

interface FourWheeler   {

   default void print() {
      System.out.println("I am a four wheeler!");
   }
  
   void cacskat();
}

class Car implements FourWheeler,Vehicle {

    public void print() {
     Vehicle.super.print();
     FourWheeler.super.print();
      Vehicle.blowHorn();
      System.out.println("I am a car!");
   }

	@Override
	public void cacskat() {
		System.out.println("cacskat");
		
	}

	



	


}
