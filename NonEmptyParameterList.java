final class NonEmptyParameterList extends ParameterList
{
	String id;
	ParameterList parameterList;
	
	NonEmptyParameterList(String s, ParameterList pl)
	{
		id = s;
		parameterList = pl;
	}
}