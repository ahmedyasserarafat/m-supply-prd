package generics;

public class Logger {
  private String value;
  
	
	public Logger(String value) {
	super();
	System.out.println("val "+value);
	this.value = value;
	
}


	public Logger() {
		System.out.println("Empty invocation");
	}


	public void log() {
		System.out.println(value);
	}
}
