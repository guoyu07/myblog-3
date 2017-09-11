package com.linuxea.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseKind<M extends BaseKind<M>> extends Model<M> implements IBean {

	public java.lang.String getId() {
		return getStr("id");
	}

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getName() {
		return getStr("name");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

}
