package com.example.test.mapper;

import com.example.test.bean.ReceiverBean;
import com.example.test.bean.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDAO {
    @Insert("insert into user(username,password,gender,email,telephone,introduce,state,registerTime) " +
            "values (#{username},#{password},#{gender},#{email},#{telephone},#{introduce},#{state},#{registerTime})")
    void regist(String username, String password, String gender,
                           String email, String telephone, String introduce,
                           int state, String registerTime);

    @Select("select * from user where id=#{id}")
    UserBean sel(int id);

    @Select("select id,username,password from user where username=#{username}")
    UserBean login(String username);

    @Insert("insert into receiver(receiverAddress,receiverName,receiverPhone,user_id) values(#{editAddress},#{editUser},#{editPhone},#{id})")
    void addAddressDAO(String editUser, String editPhone, String editAddress,int id);

    @Select("select * from receiver where user_id=#{id}")
    List<ReceiverBean> showAddress(int id);

}
