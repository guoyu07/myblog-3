package com.linuxea.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public java.lang.String getId() {
		return getStr("id");
	}

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getUserName() {
		return getStr("user_name");
	}

	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}

	public java.lang.String getUserPassword() {
		return getStr("user_password");
	}

	public void setUserPassword(java.lang.String userPassword) {
		set("user_password", userPassword);
	}

}
