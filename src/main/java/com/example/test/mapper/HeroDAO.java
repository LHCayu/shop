package com.example.test.mapper;

import com.example.test.bean.HeroBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeroDAO {
    @Select("select id,name from hero limit #{start},5")
    List<HeroBean> selectAllHero(int start);

    @Select("select count(*) from hero")
    int countHero();

    @Update("update hero set name = #{name} where id=#{id}")
    void updateHero(int id, String name);

    @Insert("insert into hero (name) values(#{name})")
    void insertHero(String name);

    @Delete("delete from hero where id=#{id}")
    void delete(int id);
}
