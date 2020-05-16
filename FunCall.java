import java.util.*;

final class FunCall extends FunExp
{
	Id func;  // identifier "func" may be a variable or a user-defined function name
	
	FunCall(Id i, ExpList e)
	{
		func = i;
		expList = e;
	}

	String getFunOp()
	{
		return func.id;
	}
	
	Val Eval(HashMap<String,Val> state)
        {
        Val fVal = func.Eval(state);

        if(fVal == null){
            return null;
        }

        if(fVal.getClass() != FunVal.class){
            // Some error message
            return null;
        }

        FunDef fd = Parser.funcMap.get(((FunVal) fVal).funName);

        ParameterList pl = fd.header.parameterList;
        Exp body = fd.exp;
        ExpList arguments = expList;
        HashMap<String, Val> funCallState = new HashMap<>();

        while(arguments instanceof NonEmptyExpList && pl instanceof NonEmptyParameterList){
            Val eVal = ((NonEmptyExpList) arguments).e.Eval(state);
            String paramName = ((NonEmptyParameterList) pl).id;

            if(eVal == null){
                // Some error message
                return null;
            }

            funCallState.put(paramName, eVal);
            arguments = ((NonEmptyExpList) arguments).el;
            pl = ((NonEmptyParameterList) pl).parameterList;
        }

        if(arguments instanceof NonEmptyExpList){
            System.out.println("Error: too many arguments for function: " + func.id);
            return null;
        }
        if(pl instanceof NonEmptyParameterList){
            System.out.println("Error: insufficient # of arguments for function: " + func.id);
            return null;
        }

        return body.Eval(funCallState);
	}   
}