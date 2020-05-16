public class BoolVal extends Val{
    boolean val;

    BoolVal(boolean v){
        val = v;
    }

    public String toString(){
		return String.valueOf(val);
	}

	Val cloneVal(){
		return new BoolVal(val);
	}

	float floatVal(){
	    return val ? 1.0f : 0.0f;
	}

	boolean isNumber(){
		return false;
	}

	boolean isZero(){
		return false;
	}
}