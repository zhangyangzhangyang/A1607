package com.example.day43_greendao_tools;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.DaoGenerator;

/**
 * Created by 张样 on 2016/11/9.
 */
public class GreenDaoGenerator {
    public static void main(String[] args) {
        //创建模板
        /**
         *参数一：当前模板的名称，会作为自动生成的java类的名称前缀
         * 二：当前数据库的版本号
         * 三：自动生成java类的包名
         */
        Schema schema = new Schema("android1607",1,"com.example.day43_greendao.dao");
        //创建表
        //参数：是表名和Javabean对象的名称
        Entity userEntity = schema.addEntity("User");
        //添加主键，并且自动增长
        userEntity.addIdProperty().primaryKey().autoincrement();
        //添加name字段
        userEntity.addStringProperty("name").notNull();
        //添加sex字段
        userEntity.addStringProperty("sex");
        //执行自动生成
        /**
         * 参数1：当前需要自动生成的数据库的模板对象
         * 参数2：自动生成的代码的目录
         */
        try {
            new DaoGenerator().generateAll(schema,"../MyApplication1/day43_greendao/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
