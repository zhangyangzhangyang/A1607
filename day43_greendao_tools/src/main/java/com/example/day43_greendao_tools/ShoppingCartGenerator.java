package com.example.day43_greendao_tools;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * Created by 张样 on 2016/11/9.
 */
public class ShoppingCartGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema("cart",1,"com.example.day43_shoppingcart.dao");
        Entity shopingCart = schema.addEntity("ShopingCart");
        shopingCart.addIdProperty().primaryKey().autoincrement();
        shopingCart.addStringProperty("goodsName");
        shopingCart.addFloatProperty("goodsPrice");
        shopingCart.addLongProperty("goodsId");
        shopingCart.addIntProperty("goodsNum");
        try {
            new DaoGenerator().generateAll(schema,"../MyApplication1/day43_shoppingcart/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
