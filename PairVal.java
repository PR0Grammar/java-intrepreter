public class PairVal extends Val{
    Val first;
    Val second;

    PairVal(Val f, Val s){
        first = f;
        second = s;
    }
    
    public String toString()
	{
		return "pair("+first+", "+second+")";
	}

	Val cloneVal()
	{
		return this;
	}

    // not used
	float floatVal()
	{
		return 0.0f;
	}

	boolean isNumber()
	{
		return false;
	}

	boolean isZero()
	{
		return false;
	}
}