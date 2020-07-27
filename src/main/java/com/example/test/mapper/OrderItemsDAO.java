package com.example.test.mapper;

import com.example.test.bean.OrderItemsBean;
import com.example.test.bean.ReceiverBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderItemsDAO {
    @Insert("insert into orderitems(product_id,buynum,user_id) values(#{id},#{num},#{uid})")
    void insertBuyNums(int id, int num,int uid);

    @Select("select buynum from orderitems where product_id=#{id} and user_id=#{uid}")
    List<OrderItemsBean> alertNums(int id,int uid);

    @Update("update orderitems set buynum=#{num} where product_id=#{id} and user_id=#{uid} and order_id is null")
    void updateBuyNums(int id,int num,int uid);

    @Select("SELECT orderitems.product_id,products.`name`,products.price,orderitems.buynum FROM products JOIN orderitems ON products.id=orderitems.product_id where user_id=#{uid}")
    List<OrderItemsBean> selOrder(int uid);

    @Update("update orderitems set buynum = #{num} where product_id=#{id}")
    void updateToOrderItems(int id, int num);

    @Select("SELECT * FROM `receiver` WHERE user_id=#{user_id} LIMIT 0,1;")
    ReceiverBean defaultAddress(int user_id);

    @Select("SELECT * FROM receiver WHERE id=#{id} and user_id=#{uid};")
    ReceiverBean selectAddress(int id,int uid);
}
