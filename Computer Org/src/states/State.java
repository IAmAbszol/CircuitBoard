package states;

public enum State {
	PREFERRED, OTHER;

	private static char _preferredSymbol = '1';
	private static char _otherSymbol = '0';
	
	public static char display(State state){
		
		if (state == PREFERRED)return _preferredSymbol;
		else return _otherSymbol;
	}
	
	public static void preferredSymbol(char symbol){
		
		_preferredSymbol = symbol;
	}
	
	public static char preferredSymbol(){
		
		return _preferredSymbol;
	}
	
	public static void otherSymbol(char symbol){
		_otherSymbol = symbol;
	}
	
	public static char otherSymbol(){
		return _otherSymbol;
	}
	
}
