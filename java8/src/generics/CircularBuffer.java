package generics;

public class CircularBuffer {

	private Object[] buffer=null;
	private int readBuffer=0;
	private int writeBuffer=0;
	int bufferLength=2;
	public CircularBuffer(int bufferlen) {
		buffer=new Object[bufferlen];
		bufferLength=bufferlen;
	}
	
	
	public boolean offer(Object val) {
		if(buffer[writeBuffer]!=null) {
			return false;
		}
		buffer[writeBuffer]=val;
		writeBuffer=next(writeBuffer);
		return true;
	}
	
	public Object poll() {
		final Object val=buffer[readBuffer];
		if(val!=null) {
			buffer[readBuffer]=null;
			readBuffer=next(readBuffer);
			
		}
		
		
		return val;
	}
	
	public int next(int index) {
		return (index+1)%bufferLength;
	}
	
}
