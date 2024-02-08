package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface  UserMapper {
    /**
     * 根据openid查询用户数据
     * @param openid
     * @return {@link User}
     */
    @Select("select * from user where openid=#{openid}")
    public User getByOpenid(String openid);

    /**
     * 插入用户数据
     * @param user
     */
    void insert(User user);

    @Select("select  * from user where id=#{id}")
    User getById(Long id);

    /**
     * 根据动态条件统计用户数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
