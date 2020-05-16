import java.util.HashMap;

abstract class Exp{
    
    void printParseTree(String indent){

    }

    abstract Val Eval(HashMap<String, Val> state);
}