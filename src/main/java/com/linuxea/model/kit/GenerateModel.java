package com.linuxea.model.kit;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import com.linuxea.config.JfinalSystem;

import javax.sql.DataSource;


public class GenerateModel {

	/**
	 * 部分功能使用 Db + Record 模式实现，无需生成 model 的 table 在此配置
	 */
	private static String[] excludedTable = {
	};

	/**
	 * 重用 JFinalClubConfig 中的数据源配置，避免冗余配置
	 */
	public static DataSource getDataSource() {
		DruidPlugin druidPlugin = JfinalSystem.getDruidPlugin();
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}

	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.linuxea.model.base";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath()
				+ "/src/main/java/com/linuxea/model/base";

		System.out.println("输出路径：" + baseModelOutputDir);

		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.linuxea.model";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir + "/..";

		// 创建生成器
		Generator gen = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// 设置数据库方言
		gen.setDialect(new MysqlDialect());
		// 添加不需要生成的表名
		for (String table : excludedTable) {
			gen.addExcludedTable(table);
		}
		// 设置是否在 Model 中生成 dao 对象
		gen.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gen.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		// gernerator.setRemovedTableNamePrefixes("t_");
		// 生成
		gen.generate();
	}
}
