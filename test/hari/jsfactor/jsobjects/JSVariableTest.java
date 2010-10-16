package hari.jsfactor.jsobjects;

import junit.framework.Assert;

import org.junit.Test;

public class JSVariableTest {

	@Test
	public void testShouldParseVariableNameAndType() throws Exception {
		JSVariable variable = JSVariable.getJSVariable("var testVariable = 10;", 0, 0);
		Assert.assertEquals("testVariable",variable.getVariableName());
		Assert.assertEquals("10",variable.getVariableValue());
	}
}
