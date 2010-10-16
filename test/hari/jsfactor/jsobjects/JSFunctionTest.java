package hari.jsfactor.jsobjects;

import org.junit.Assert;
import org.junit.Test;

public class JSFunctionTest {
	@Test
	public void testParsingOfFunctionNames() throws Exception {
		JSFunction jsFunction = JSFunction.getJSFunction("function test()", 0, 0);
		Assert.assertEquals("test", jsFunction.getFunctionName());
		Assert.assertEquals(0, jsFunction.getParameters().size());
		
	}
	
	@Test
	public void testParsingOfFunctionAsVariable() throws Exception {
		JSFunction jsFunction = JSFunction.getJSFunctionFromVariable("var test = function(a,b,c)", 0, 0);
		Assert.assertEquals("test", jsFunction.getFunctionName());
		Assert.assertEquals(0, jsFunction.getParameters().size());
		
	}

	@Test
	public void testParsingOfFunctionWithParameters() throws Exception {
		JSFunction jsFunction = JSFunction.getJSFunction("function test(abc,def, ghi)", 0, 0);
		Assert.assertEquals("test", jsFunction.getFunctionName());
		Assert.assertEquals(3, jsFunction.getParameters().size());
		Assert.assertEquals("abc", jsFunction.getParameters().get(0));
		Assert.assertEquals("def", jsFunction.getParameters().get(1));
		Assert.assertEquals("ghi", jsFunction.getParameters().get(2));
	}
}
