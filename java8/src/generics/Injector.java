package generics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import sun.awt.SunHints.Value;

public class Injector<K> {
	
	private Map<Class<?>,Object> ss=new HashMap<>();
	
	public Injector<K> with(K val) {
		
		ss.put(val.getClass(), val);
		return this;
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T newInstance(Class<T> ins,String... params) {
		Object rt=ss.computeIfAbsent(ins, (key)->{ 
			return this.createNewInstance(ins, params);
			
		});
		return (T) rt;
		
		
	}
	
	
	
	public Object createNewInstance(Class<?> clazz,String... params) {
		Object object =null;
		System.out.println(String.join(",", params));
		Objects.requireNonNull(clazz);
		try {
		Constructor<?>[] ctors = clazz.getConstructors();
		if(ctors.length==0) {
			throw new IllegalArgumentException("No Constructors Are Present");
		}
		 object = clazz.getConstructor(new Class[] {String.class}).newInstance(String.join(",", params));
	
	}catch(Exception E) {
		
	}
	return object;
	}

}
