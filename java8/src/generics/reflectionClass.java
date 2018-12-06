package generics;

public class reflectionClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Injector<String> inj=new Injector<String>().with("Hello World");
		
		Logger logger=(Logger)inj.newInstance(Logger.class,"Hi");
		System.out.println(logger);
		//logger.log();
	}

}
