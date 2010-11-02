package hari.jsfactor.ui;

import java.util.HashMap;
import java.util.Map;

public class JSKeywordContentAssist {

	public static Map<String,String> keywordsContentAssistant =new HashMap<String, String>();
	
	static
	{
		keywordsContentAssistant.put("if", "if(condition){\n\t}");
		keywordsContentAssistant.put("while", "while(condition){\n\t}");
		keywordsContentAssistant.put("do", "do{\n}while(condition)");
		keywordsContentAssistant.put("for", "for(var i=0;condition;operation){\n\t}");
		keywordsContentAssistant.put("function", "function nameHere(parametersHere){\n}");
		keywordsContentAssistant.put("switch", "switch(condition){\n\t" +
				"case option1: break\n\t" +
				"default:\n\t" +
				"}");
	}
}
