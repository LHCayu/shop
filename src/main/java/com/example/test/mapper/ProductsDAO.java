package com.example.test.mapper;

import com.example.test.bean.ProductsBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductsDAO {
    @Select("select * from products")
    List<ProductsBean> sel();

    @Select("select * from products where id=1")
    ProductsBean sid();

    //排序
    @Select("select * from products where category=#{screen} order by convert(name USING gbk) COLLATE gbk_chinese_ci asc")
    List<ProductsBean> orderByName(String screen);

    @Select("select * from products where category=#{screen} order by price")
    List<ProductsBean> orderByPriceLowHigh(String screen);

    @Select("select * from products where category=#{screen} order by price DESC")
    List<ProductsBean> orderByPriceHighLow(String screen);

    //筛选
    @Select("select * from products WHERE category=#{screen}")
    List<ProductsBean> screenBycategory(String screen);

    //输出全部商品的排序
    @Select("select * from products order by convert(name USING gbk) COLLATE gbk_chinese_ci asc")
    List<ProductsBean> orderByNameALL();

    @Select("select * from products order by price")
    List<ProductsBean> orderByPriceLowHighALL();

    @Select("select * from products order by price DESC")
    List<ProductsBean> orderByPriceHighLowALL();
}
