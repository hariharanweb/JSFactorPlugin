package hari.jsfactor.jsobjects;

import junit.framework.Assert;

import org.junit.Test;

public class JSVariableTest {

	@Test
	public void testShouldParseVariableNameAndType() throws Exception {
		IJSObject variable = JSVariable.getJSVariable("var testVariable = 10;", 0, 0);
		Assert.assertTrue(variable instanceof JSVariable);
		Assert.assertEquals("testVariable",((JSVariable) variable).getVariableName());
		Assert.assertEquals("10",((JSVariable) variable).getVariableValue());
	}
	
	@Test
	public void testShouldParseVariableAsFunction() throws Exception {
		IJSObject variable = JSVariable.getJSVariable("var myFunc = function(a,b) {", 0, 0);
		Assert.assertTrue(variable instanceof JSFunction);
		Assert.assertEquals("myFunc",((JSFunction) variable).getFunctionName());
		Assert.assertEquals(2,((JSFunction) variable).getParameters().size());
		Assert.assertEquals("a",((JSFunction) variable).getParameters().get(0));
		Assert.assertEquals("b",((JSFunction) variable).getParameters().get(1));
	}
}
