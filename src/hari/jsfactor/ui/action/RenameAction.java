package hari.jsfactor.ui.action;

import hari.jsfactor.jsobjects.JSFunction;
import hari.jsfactor.scanner.JSDocumentScanner;
import hari.jsfactor.ui.JSFactor;

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
		JSDocumentScanner scanner = jsFactor.getScanner();
		JSFunction[] jsFunctions = scanner.getJSFunctions();
		String selectedText = ((TextSelection) selection).getText();
		JSFunction functionToRenameFunction = null;
		for (JSFunction jsFunction : jsFunctions) {
			if (jsFunction.getFunctionName().equals(selectedText)) {
				functionToRenameFunction = jsFunction;
				break;
			}
		}
		if (functionToRenameFunction == null) {
			MessageDialog.openError(null, "Cannot perform the operation",
					"Select a function to rename");
			return null;
		}

		InputDialog inputDialog = new InputDialog(null, "Enter what to rename",
				"Rename variable " + selectedText + " to ?", selectedText, null);
		inputDialog.open();
		String newName = inputDialog.getValue();
		List<Integer> usages = functionToRenameFunction.getUsages();
		IDocument document = jsFactor.getDocumentProvider().getDocument(
				jsFactor.getEditorInput());
		try {
			document.replace(functionToRenameFunction.getOffset()+"function ".length(),
					newName.length(), newName);

			for (Integer offset : usages) {
				document.replace(offset, newName.length(), newName);
			}
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
