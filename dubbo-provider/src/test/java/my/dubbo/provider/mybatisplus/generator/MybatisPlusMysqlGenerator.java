package my.dubbo.provider.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * @author _lizy
 * @version 1.0
 * @description
 *      **修改OUT_PATH、JDBC相关配置**，运行即可
 *      代码生成到test下auto包内，mapper、xml复制覆盖项目dao包下auto包
 *
 *      quick start：https://baomidou.com/guide/generator.html
 *      类属性参考官网：https://baomidou.com/config/generator-config.html
 * @date 2020/10/18 17:45
 */
public class MybatisPlusMysqlGenerator {

    interface CONFIG{
        /**********************************************************
         *  生成代码目录 - OUT_PATH = 复制src/test/java/my/dubbo/provider/mybatisplus/auto绝对路径
         *  生成表 - TABLES
         *********************************************************/
    //    public static final String OUT_PATH = "D:\\study\\workspace.project\\com.example.cloud\\springcloudalibaba-test\\dubbo-provider\\src\\main\\java\\my\\dubbo\\provider";
        public static final String OUT_PATH = "D:\\study\\workspace.project\\com.example.cloud\\springcloudalibaba-test\\dubbo-provider\\src\\test\\java\\my\\dubbo\\provider\\mybatisplus\\auto";
        public static final String[] TABLES = {"role","user"}; //指定要生成的表（表名），为空表示生成全部表
        public static final String AUTHOR = "lizy ";    //代码生成者
    
    //    public static final String BASE_PACKAGE_NAME = ""; // 添加包路径，生成目录结构 - OUT_PATH\\PARENT_PACKAGE_NAME
    //    public static final String MODULE_NAME = "";   // 模块名称（包名）  - OUT_PATH\\PARENT_PACKAGE_NAME\\MODULE_NAME



        /********************************************************************
         * 是否生成mapper类、mapper.xml、controller、entity、service
         *******************************************************************/
        public static final Boolean IS_GEN_MAPPER = Boolean.TRUE;      // 是否生成mapper类
        public static final Boolean IS_GEN_MAPPER_XML = Boolean.TRUE;  // 是否生成mapper.xml
        public static final Boolean IS_GEN_ENTITY = Boolean.TRUE;      // 是否生成entity
        public static final Boolean IS_GEN_CONTROLLER = Boolean.FALSE;  // 是否生成controller
        public static final Boolean IS_GEN_SERVICE = Boolean.FALSE;     // 是否生成service
    
    
    
        /**********************************************************
         * JDBC相关配置
         *   修改mysql5，mysql8对应驱动
         *   修改用户名、密码
         *********************************************************/
    //    public static final String DRIVER_JDBC = "com.mysql.jdbc.Driver";  // mysql5
    //    public static final String DRIVER_URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8"; // mysql5
        public static final String DRIVER_JDBC = "com.mysql.cj.jdbc.Driver"; // mysql8
        public static final String DRIVER_URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT"; // mysql8
        public static final String MYSQL_USER_NAME = "root";
        public static final String MYSQL_PASSWORD = "123456";
    }



    /**
     * <p>
     * MySQL 生成
     * </p>
     */
    public static void main(String[] args) {

        if(test()){ return; }


        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        tableFillList.add(createField);
        tableFillList.add(modifiedField);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // GlobalConfig全局配置，输出目录、mapper、xml名称
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(FilePathUtils.getMySrcFolderAbsolutePath(CONFIG.OUT_PATH)) // 输出目录
                .setFileOverride(true) // 是否覆盖文件
                .setActiveRecord(true) // 开启 activeRecord 模式
                .setEnableCache(false) // XML 二级缓存
                .setBaseResultMap(false) // XML ResultMap
                .setBaseColumnList(true) // XML columList
                .setAuthor(CONFIG.AUTHOR)
                // .setServiceName("MP%sService")
                // .setServiceImplName("%sServiceImpl")
                // .setControllerName("%sAction")
                .setXmlName("%sMapper").setMapperName("%sMapper");  // 自定义文件命名，注意 %s 会自动填充表实体属性！

        mpg.setGlobalConfig(globalConfig);


        // DataSourceConfig数据源配置，数据库、类型
        DataSourceConfig dsc = new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName(CONFIG.DRIVER_JDBC)
                .setUsername(CONFIG.MYSQL_USER_NAME)
                .setPassword(CONFIG.MYSQL_PASSWORD)
                .setUrl(CONFIG.DRIVER_URL);

        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                // return DbColumnType.BOOLEAN;
                // }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);


        // StrategyConfig表名、实体生成策略, 指定表生成
        StrategyConfig strategyConfig = new StrategyConfig()
//                .setInclude(new String[] { "user" }) // 需要生成的表
//                .setExclude(new String[]{"test"}) // 排除生成的表
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略：下划线转驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel) // 表字段生成策略：下划线转驼峰
                .setEntityLombokModel(true)
                .setRestControllerStyle(false)
                .setTableFillList(tableFillList)
                .setEntityColumnConstant(true)
                .setChainModel(true)
//                .setSuperEntityColumns("id") // entity 父类中生成id
                .setControllerMappingHyphenStyle(true);


        Optional.ofNullable(CONFIG.TABLES).ifPresent(tb -> strategyConfig.setInclude(CONFIG.TABLES));

        mpg.setStrategy(strategyConfig);

        // PackageConfig包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setController("controller")    // 这里是控制器包名，默认 web
                    .setXml("mapper")
                    .setMapper("dao");
        mpg.setPackageInfo(packageConfig);



        // InjectionConfig自定义配置
        String templatePath = "/templates/mapper.xml.vm";   // mybatisplus默认内置模板 velocity
//        String templatePath = "/templates/mapper.xml.ftl"; // 模板 freemarker
        mpg.setCfg( new InjectionConfig() {
                            @Override
                            public void initMap() { //自定义属性注入:abc，在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性，可以在 VM 中使用 cfg.abc 设置的值
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                                this.setMap(map);
                            }
                        }.setFileOutConfigList(
                                Collections.singletonList(new FileOutConfig(templatePath) {
                                    // 自定义输出xml路径及文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                                    @Override
                                    public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
                                        return getXmlPathByEntityName(tableInfo.getEntityName(), packageConfig.getMapper());
                                    }
                                })));


        TemplateConfig templateConfig = new TemplateConfig();

        if(!CONFIG.IS_GEN_CONTROLLER){
            templateConfig.setController("");
        }
        if(!CONFIG.IS_GEN_SERVICE){
            templateConfig.setService("").setServiceImpl("");
        }
        if(!CONFIG.IS_GEN_MAPPER){
            templateConfig.setMapper("");
        }
        if(!CONFIG.IS_GEN_MAPPER_XML){
            templateConfig.setXml("");
        }
        if(!CONFIG.IS_GEN_ENTITY){
            templateConfig.setEntity("");
        }



        mpg.setTemplate( templateConfig   // 关闭默认 xml 生成，调整生成 至 根目录
                        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
//                         .setController("...");
                        // .setEntity("...");
                        // .("...");
                        // .setXsetMapperml("...");
                        // .setService("...");
                        // .setServiceImpl("...");
                );

        // 执行生成
        mpg.execute();
    }

    private static Boolean test() {
//        System.out.println(getXmlPathByEntityName("",""));
//        System.out.println("D:\\\\study\\\\workspace.project\\\\com.example.dubbo\\\\dubbo\\\\dubbo-provider\\\\src\\\\test\\\\java");
//        System.out.println("D:\\work\\Program Files\\jdk1.8.0_131\\".endsWith(File.separator));
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
//        System.out.println(ClassLoader.getSystemResource(""));
//        System.out.println(System.getProperty("user.dir")+"\\dubbo-provider\\src\\main\\java"); // user.dir不同环境路径不同，只用在此处，拼接当前类绝对路径
        return false;
    }

    /**
     * @Description xml目录放到生成mapper目录下
     *
     * @param entityName
     * @Return java.lang.String
     * @Author _lizy
     * @Date 2020/10/18 21:59
     */
    private static String getXmlPathByEntityName(String entityName, String mapperPackageName) {

        StringBuilder sb = new StringBuilder(FilePathUtils.getMySrcFolderAbsolutePath(""));
//        sb.append(FilePathUtils.packageToPath(sb.toString(), BASE_PACKAGE_NAME));
//        sb.append(FilePathUtils.packageToPath(sb.toString(), MODULE_NAME));
        sb.append(FilePathUtils.packageToPath(sb.toString(), mapperPackageName));
        sb.append(FilePathUtils.packageToPath(sb.toString(), "xml"));
        sb.append(entityName).append("Mapper.xml").toString();
        System.out.println(sb.toString());
        return sb.toString();
    }

    



}
