package my.dubbo.provider.mybatisplus.generator;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author _lizy
 * @version 1.0
 * @description 拼接本地目录，用于生成代码输出
 * @date 2020/10/19 2:14
 */
public class FilePathUtils {


    public static final String USER_DIR = "user.dir";  // 项目文件夹绝对路径（最上层父项目）


    /**
     * @Description
     *      拼接当前项目src\main\java目录绝对路径（本项目MybatisPlusMysqlGenerator适用）
     * @Return java.lang.String
     * @Author _lizy
     * @Date 2020/10/19 2:05
     * @param path 项目所在目录绝对路径，传入path为空， 默认获取user.dir
     */
    public static String getMySrcFolderAbsolutePath(String path) {
        return getSourceFolderAbsolutePath(path, "");
    }


    /**
     * @Description
     *      拼接当前项目绝对路径
     *      注：user.dir获取项目绝对路径（本地最上层父项目），不同环境路径不同，使用前可本类main方法测试修改后续拼接部分
     * @Return java.lang.String
     * @Author _lizy
     * @Date 2020/10/19 2:05
     * @param path  项目所在目录绝对路径，传入path为空，默认获取user.dir
     * @param afterUserDirPath 项目内包路径，传入path为空，获取user.dir
     */
    public static String getSourceFolderAbsolutePath(String path, String afterUserDirPath) {
        return StringUtils.isBlank(path)
                ? ( System.getProperty(USER_DIR)+afterUserDirPath )
                : path;
    }


    /**
     * @Description
     *      包路径转系统路径；
     * @param curPath 已获取绝对路径 D:\work\workspace\
     * @param packageName 后续包路径 com.alibaba.provider
     * @Return java.lang.String
     * @Author _lizy
     * @Date 2020/10/19 1:05
     */
    public static String packageToPath(String curPath, String packageName){
        return StringUtils.isBlank(packageName)
                ? StringUtils.EMPTY
                : (appendPrefix(curPath) + packageName.replaceAll("\\.", "\\"+ File.separator)+ File.separator);
    }

    // 前缀，如果已拼接的路径以"\"结尾，不用再加
    public static String appendPrefix(String curPath) {
        return curPath.endsWith(""+File.separator) ? StringUtils.EMPTY : File.separator;
    }




    /**
     * @Description
     *      链式拼接，性能不好，待优化
     *      是否每次拼接不需要检查已经拼好的是\结尾；
     * @Author _lizy
     * @Date 2020/10/19 3:13
     */
    static class StringBuilderChain  {
        private StringBuilder stringBuilder;

        private StringBuilderChain(){}


        /**
         * @Description
         *     拼接字符串，前后加File.separator即\，并替换.为\
         *     eg: appendPath(my.dubbo.provider) = \my\dubbo\provider\
         *
         * @param packagePath 传入需转换的包字符串  eg：my.dubbo.provider
         * @Return StringBuilderChain
         * @Author _lizy
         * @Date 2020/10/19 3:26
         */
        public StringBuilderChain appendPath(String packagePath) {
            Optional.ofNullable(stringBuilder)
                    .orElse(new StringBuilder(packageToPath(packagePath)))
                    .append(packageToPath(packagePath));    // stringBuilder是否为空，为空新建，否则，拼接转换字符串
            return this;
        }


        /**
         * @Description
         *     拼接字符串，同stringBuilder.append()
         *     eg: appendPath(my.dubbo.provider) = \my\dubbo\provider\
         *
         * @param str
         * @Return my.dubbo.provider.mybatisplus.generator.FilePathUtils.StringBuilderChain
         * @Author _lizy
         * @Date 2020/10/19 3:26
         */
        public StringBuilderChain append(String str) {
            Optional.ofNullable(stringBuilder)
                    .orElse(new StringBuilder(str))
                    .append(str); // stringBuilder是否为空，为空新建，否则，拼接字符串
            return this;
        }

        /**
         * @Description
         *     拼接字符串，同stringBuilder.append()
         *     eg: appendPath(my.dubbo.provider) = \my\dubbo\provider\
         *
         * @param str
         * @Return my.dubbo.provider.mybatisplus.generator.FilePathUtils.StringBuilderChain
         * @Author _lizy
         * @Date 2020/10/19 3:26
         */
        public StringBuilderChain append(String str, Function<String, String> function) {
            Optional.ofNullable(stringBuilder).orElse(new StringBuilder(function.apply(str))).append(function.apply(str)); // stringBuilder是否为空，为空新建，否则，拼接字符串
            return this;
        }

        class FuncPackageToPath implements Function<String, String>{
            @Override
            public String apply(String s) {
                return packageToPath(s);
            }
        }



        /**
         * @Description
         *      拼接项目路径和包路径
         * @param packageName 后续包路径 com.alibaba.provider
         * @Return java.lang.String
         * @Author _lizy
         * @Date 2020/10/19 1:05
         */
        public String packageToPath(String packageName){
            return StringUtils.isBlank(packageName)
                    ? StringUtils.EMPTY
                    : (appendPrefix(stringBuilder.toString()) + packageName.replaceAll("\\.", "\\"+ File.separator)+ File.separator);
        }

        /**
         * @Description
         *      获取实例
         * @Author _lizy
         * @Date 2020/10/19 1:05
         */
        public static StringBuilderChain getInstance(){
            return new StringBuilderChain().setStringBuilder(new StringBuilder());
        }


        @Override
        public String toString() {
            return stringBuilder.toString();
        }

        public StringBuilderChain setStringBuilder(StringBuilder stringBuilder) {
            this.stringBuilder = stringBuilder;
            return this;
        }


        /**
         * @Description
         *      main测试
         * @Author _lizy
         * @Date 2020/10/19 5:05
         */
        public static void main(String[] args) {
//            System.out.println("D:\\study\\workspace.project\\com.example.dubbo\\dubbo\\dubbo-provider\\src\\test\\java"); // idea java目录 手动copy path

            System.out.println(getSourceFolderAbsolutePath("", ""));
            System.out.println(getMySrcFolderAbsolutePath(""));


            String sb = StringBuilderChain.getInstance()
                    .append(getMySrcFolderAbsolutePath(""))
                    .appendPath("my.dubbo.provider")
                    .appendPath("")
                    .appendPath("dao")
                    .appendPath("xml")
                    .append("User").append("Mapper.xml").toString();
            System.out.println(sb.toString());


            StringBuilderChain sbcInstance = StringBuilderChain.getInstance();
            String sb4Func = sbcInstance.append(getMySrcFolderAbsolutePath(""))
                    .appendPath("my.dubbo.provider")
                    .appendPath("")
                    .appendPath("dao")
                    .append("xml", sbcInstance.new FuncPackageToPath())
                    .append("User").append("Mapper.xml").toString();
            System.out.println(sb4Func);
        }

    }














}
