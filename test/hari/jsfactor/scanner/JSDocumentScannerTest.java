package hari.jsfactor.scanner;

import hari.jsfactor.jsobjects.JSFunction;

import org.junit.Assert;
import org.junit.Test;

public class JSDocumentScannerTest {

	@Test
	public void testParsingOfFunctionNames() throws Exception {
		JSDocumentScannerStub scannerStub = new JSDocumentScannerStub();
		JSFunction jsFunction = scannerStub.getFunctionDefinition("function test()", 0, 0);
		Assert.assertEquals("test", jsFunction.getFunctionName());
		Assert.assertEquals(0, jsFunction.getParameters().size());
		
	}

	@Test
	public void testParsingOfFunctionWithParameters() throws Exception {
		JSDocumentScannerStub scannerStub = new JSDocumentScannerStub();
		JSFunction jsFunction = scannerStub.getFunctionDefinition("function test(abc,def, ghi)", 0, 0);
		Assert.assertEquals("test", jsFunction.getFunctionName());
		Assert.assertEquals(3, jsFunction.getParameters().size());
		Assert.assertEquals("abc", jsFunction.getParameters().get(0));
		Assert.assertEquals("def", jsFunction.getParameters().get(1));
		Assert.assertEquals("ghi", jsFunction.getParameters().get(2));
	}

	private class JSDocumentScannerStub extends JSDocumentScanner{
		@Override
		public JSFunction getFunctionDefinition(String content, int offset, int length) {
			return super.getFunctionDefinition(content, offset, length);
		}
	}
}
