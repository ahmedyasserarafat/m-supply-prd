package generics;

public class CircularBufferTypeGeneric<T> {

	private T[] buffer=null;
	private int readBuffer=0;
	private int writeBuffer=0;
	int bufferLength=2;
	public CircularBufferTypeGeneric(int bufferlen) {
		buffer=(T[]) new Object[bufferlen];
		bufferLength=bufferlen;
	}
	
	
	public boolean offer(T val) {
		if(buffer[writeBuffer]!=null) {
			return false;
		}
		buffer[writeBuffer]=val;
		writeBuffer=next(writeBuffer);
		return true;
	}
	
	public T poll() {
		final T val=buffer[readBuffer];
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
