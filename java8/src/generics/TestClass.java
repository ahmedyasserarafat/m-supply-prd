package generics;

import java.awt.event.TextEvent;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

public class TestClass {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		// TODO Auto-generated method stub
		
		//Logger logger=new Logger();
		Class clazz=Person.class;
		System.out.println(clazz.getConstructors());
		Method[] methods = clazz.getMethods();
		Field[] fields=clazz.getFields();
		Constructor[] cons = clazz.getConstructors();
		Class[] intf = clazz.getInterfaces();
		
		
		
		Method method = Person.class.getMethod("setName", new Class[] {String.class});
		Method method1 = Person.class.getMethod("getName");
		Person pp=new Person("Hi", 70);
		Person jj=Person.class.getConstructor(new Class[] {String.class,Integer.TYPE}).newInstance("Hi3",72);
		System.out.println(pp +"  ===  "+jj);
		Object returnValue = method.invoke(jj, "Hello");
		System.out.println(" ll "+method1.invoke(jj));
		
		
		String[] strings = new String[3];
		Class stringArrayClass = strings.getClass();
		Class stringArrayComponentType = stringArrayClass.getComponentType();
		System.out.println(stringArrayComponentType);
		
		Class stringArrayClass1 = Array.newInstance(String.class, 0).getClass();
		System.out.println("is array: " + stringArrayClass1.isArray());
		
		
		int[] intArray = (int[]) Array.newInstance(int.class, 3);

		Array.set(intArray, 0, 123);
		Array.set(intArray, 1, 456);
		Array.set(intArray, 2, 789);

		System.out.println("intArray[0] = " + Array.get(intArray, 0));
		System.out.println("intArray[1] = " + Array.get(intArray, 1));
		System.out.println("intArray[2] = " + Array.get(intArray, 2));
		/*
		Constructor<Person> constructor = Person.class.getConstructor(new Class[] {String.class,Integer.TYPE});
		Class[] paramaTypes=constructor.getParameterTypes();
		
		
		Person myObject = (Person)
		        constructor.newInstance("HH",89);
		System.out.println(myObject);
		
		for(Class pts : paramaTypes){
		    System.out.println("Params Types = " + pts.getName());
		}
		
		Person pp=new Person("Hi", 70);
		Field field = clazz.getField("name");
		Object ff=field.get(pp);
		field.set(pp, ff);
		System.out.println(pp);*/
		
		/*Field field = clazz.getField("name");
		System.out.println(field.getName() +"--"+field.getType());
		*/
		/*for(Class ints : intf){
		    System.out.println("Interface = " + ints.getName());
		}
		for(Constructor method : cons){
			    System.out.println("cons = " + method.getName());
			}
		for(Method method : methods){
		   // System.out.println("method = " + method.getName());
		}
		
		
		for(Field fld : fields){
		   // System.out.println("Field = " +fld.getName());
		}*/
		
		
	}

}
