package com.linuxea.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBlackList<M extends BaseBlackList<M>> extends Model<M> implements IBean {

	public java.lang.String getId() {
		return getStr("id");
	}

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getIp() {
		return getStr("ip");
	}

	public void setIp(java.lang.String ip) {
		set("ip", ip);
	}

	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

}
