package hari.jsfactor.ui.action;

import hari.jsfactor.jsobjects.JSFunction;
import hari.jsfactor.jsobjects.JSVariable;
import hari.jsfactor.scanner.JSDocumentScanner;
import hari.jsfactor.ui.JSFactor;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class RenameAction extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (!(selection instanceof TextSelection)
				|| !(editor instanceof JSFactor)) {
			MessageDialog.openError(null, "Cannot perform the operation",
					"This operation is valid only on JSFactor editor");
			return null;
		}
		JSFactor jsFactor = (JSFactor) editor;
		String selectedText = ((TextSelection) selection).getText();

		if(!renameFunctionUsages(jsFactor, selectedText)){
			renameVariableUsages(jsFactor, selectedText);
		}
		return null;
	}

	private void renameVariableUsages(JSFactor jsFactor, String selectedText) {
		JSDocumentScanner scanner = jsFactor.getScanner();
		JSVariable[] jsVariables = scanner.getJSVariables();
		JSVariable variableToRename = null;
		
		for (JSVariable jsVariable : jsVariables) {
			if(jsVariable.getVariableName().equals(selectedText)){
				variableToRename = jsVariable;
				break;
			}
		}
		if (variableToRename == null) {
			MessageDialog.openError(null, "Cannot perform the operation",
					"Select a function to rename");
			return;
		}
		
		InputDialog inputDialog = new InputDialog(null, "Enter what to rename",
				"Rename " + selectedText + " to ?", selectedText, null);
		inputDialog.open();
		String newName = inputDialog.getValue();
		List<Integer> usages = variableToRename.getUsages();
		IDocument document = jsFactor.getDocumentProvider().getDocument(
				jsFactor.getEditorInput());
		
		if(!selectedText.equals(newName)){
			try {
				
				for(int i=usages.size()-1;i>=0;i--)
				{
					document.replace(usages.get(i)+1, variableToRename.getVariableName().length(), newName);
				}
				
				document.replace(variableToRename.getOffset()+"var ".length(),
						selectedText.length(), newName);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	private boolean renameFunctionUsages(JSFactor jsFactor, String selectedText) {
		JSDocumentScanner scanner = jsFactor.getScanner();
		JSFunction[] jsFunctions = scanner.getJSFunctions();
		JSFunction functionToRenameFunction = null;
		for (JSFunction jsFunction : jsFunctions) {
			if (jsFunction.getFunctionName().equals(selectedText)) {
				functionToRenameFunction = jsFunction;
				break;
			}
		}
		if (functionToRenameFunction == null) {
			return false;
		}

		InputDialog inputDialog = new InputDialog(null, "Enter what to rename",
				"Rename " + selectedText + " to ?", selectedText, null);
		inputDialog.open();
		String newName = inputDialog.getValue();
		List<Integer> usages = functionToRenameFunction.getUsages();
		IDocument document = jsFactor.getDocumentProvider().getDocument(
				jsFactor.getEditorInput());
		
		if(!selectedText.equals(newName)){
			try {
				
				for(int i=usages.size()-1;i>=0;i--)
				{
					document.replace(usages.get(i), functionToRenameFunction.getFunctionName().length(), newName);
				}
				
				document.replace(functionToRenameFunction.getOffset()+"function ".length(),
						selectedText.length(), newName);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}
}
