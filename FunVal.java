final class FunVal extends Val
{
	String funName;

	FunVal(String f){
        funName = f;
    }

    public String toString(){
        return funName;
    }
    
    Val cloneVal(){
        return new FunVal(funName);
    }

    // Not used
	float floatVal(){
        return 0.0f;
    }
        
	boolean isNumber(){
        return false;
    }

	boolean isZero(){
        return false;
    };
}