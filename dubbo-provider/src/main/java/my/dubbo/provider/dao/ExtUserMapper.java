package my.dubbo.provider.dao;

import my.dubbo.provider.dao.auto.UserMapper;
import my.dubbo.provider.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author _lizy
 * @version 1.0
 * @description TODO
 * @date 2020/10/21 13:43
 */
public interface ExtUserMapper extends UserMapper {

    @Select("select count(*) from user where name like CONCAT('%',#{name},'%')")
    Integer countByName(String name);

    List<User> findByName(String name);


}
