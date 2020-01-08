
package cn.xp1997.xp.sys.mbg;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class MBG {
    public static void main(String[] args) {

        String globAuthor = "xp";//作者
        String globOutPutDir = "/src/main/java";//输出文件路径

        String dataBasesIp = "120.77.253.48";
        String dataBasesName = "xpBlog";
        String dataBasesUsername = "root";
        String dataBasePassword = "1096150741";


        String moduelName = "blog";   // 模块名
        String parentName = "cn.xp1997.xp";


        String strategyTableName = "xp_article_common_bind";//表名






        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + globOutPutDir);  // 指定生成的代码放在某个文件夹的下面
        gc.setAuthor(globAuthor);   // 指定作者是谁
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置    指定连接的数据库
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://"+dataBasesIp+":3306/"+dataBasesName+"?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(dataBasesUsername);
        dsc.setPassword(dataBasePassword);
        mpg.setDataSource(dsc);

        // 包配置
        // 域名.公司名.项目名.模块名
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduelName);
        pc.setParent(parentName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        //  是否生成xml文件
        List<FileOutConfig> focList = new ArrayList();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(false); // 启用lombok增加实体类的get，set方法简化代码；如果不启用可以改为false
        strategy.setRestControllerStyle(true);
        // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(strategyTableName);  // 表名
        //strategy.setSuperEntityColumns("id");
        //strategy.setSuperEntityClass();
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setEntityTableFieldAnnotationEnable(true);//生成字段注解
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }
}

