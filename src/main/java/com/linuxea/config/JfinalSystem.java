package com.linuxea.config;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.linuxea.interceptor.ExceptionInterceptor;
import com.linuxea.model._MappingKit;

/**
 * Created by Linuxea on 2017-09-10.
 */
public class JfinalSystem extends JFinalConfig {

    static{
        PropKit.use("sqlConfig.properties");
    }

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.JSP);
    }

    @Override
    public void configRoute(Routes me) {

    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin druidPlugin = getDruidPlugin();
        me.add(druidPlugin);
        ActiveRecordPlugin arp=new ActiveRecordPlugin(druidPlugin);
        arp.setShowSql(PropKit.getBoolean("devMode"));
        arp.setDialect(new MysqlDialect());
        _MappingKit.mapping(arp);
        me.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new ExceptionInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {

    }


    /**
     * druid 插件复用
     * @return
     */
    public static DruidPlugin getDruidPlugin(){
        String url = PropKit.get("url");
        String userName = PropKit.get("userName");
        String password = PropKit.get("password");
        DruidPlugin druidPlugin = new DruidPlugin(url, userName, password);
        return druidPlugin;
    }
}