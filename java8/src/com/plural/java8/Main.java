package com.plural.java8;


// A Simple Java program to demonstrate  
// method overriding in java 
  
// Base Class 
interface Parent 
{ 
    void show(); /*{ System.out.println("Parent's show()"); } */
} 
  
// Inherited class 
class Child implements Parent
{ 
    // This method overrides show() of Parent 
    public void show() { System.out.println("Child's show()"); } 
} 

class Child1 extends Child 
{ 
    // This method overrides show() of Parent 
    @Override
	public void show() { System.out.println("1 Child's show()");super.show(); } 
} 
  
// Driver class 
class Main 
{ 
    public static void main(String[] args) 
    { 
        // If a Parent type reference refers 
        // to a Parent object, then Parent's 
        // show is called 
        Parent obj1 = new Child(); 
        obj1.show(); 
  
        // If a Parent type reference refers 
        // to a Child object Child's show() 
        // is called. This is called RUN TIME 
        // POLYMORPHISM. 
        Parent obj2 = new Child1(); 
        obj2.show(); 
    } 
} 