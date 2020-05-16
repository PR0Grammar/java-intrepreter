import java.util.*;

final class Id extends Exp
{
	String id;
	
	Id(String s)
	{
		id = s;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " " + id);
	}
	
	Val Eval(HashMap<String,Val> state){
        Val e = state.get(id);
        
        // Return variable
        if(e != null){
            return e;
        }
        
        // is user defined function name
        if(Parser.funcMap.get(id) != null){
            return new FunVal(id);
        }

        // Neither
        System.out.println("Error: variable "+ id +" does not have a value");
        return null;
	}
}