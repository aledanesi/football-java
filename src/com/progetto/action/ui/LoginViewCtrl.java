package com.progetto.action.ui;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.progetto.manager.impl.UserCredentialManagerImpl;

/**
 * @author zkessentials store
 * 
 *         This is the controller for the login view as referenced in index.zul
 * 
 */
public class LoginViewCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = 5730426085235946339L;
	
	@Wire
	private Textbox nameTxb, passwordTxb;
	
	@Wire
	private Label mesgLbl;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		if (UserCredentialManagerImpl.getIntance().isAuthenticated()) {
			Executions.getCurrent().sendRedirect("index.jsp");
		}
		nameTxb.setFocus(true);

	}
	
	@Listen("onOK=#passwordTxb")
	public void onOK() {
		doLogin();
	}
	
	@Listen("onClick=#confirmBtn")
	public void confirm() {
		doLogin();
	}

	private void doLogin() {
		UserCredentialManagerImpl mgmt = UserCredentialManagerImpl.getIntance();
		mgmt.login(nameTxb.getValue(), passwordTxb.getValue());
		if (mgmt.isAuthenticated()) {
			Executions.getCurrent().sendRedirect("index.jsp");
		} else {
			mesgLbl.setValue("Your User Name or Password is invalid!");
		}
	}

}
