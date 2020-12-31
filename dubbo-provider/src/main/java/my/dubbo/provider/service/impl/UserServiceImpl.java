package my.dubbo.provider.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springaop.log.LogAnno;
import com.example.springaop.redis.RedisAnno;
import my.dubbo.provider.dao.auto.UserMapper;
import my.dubbo.provider.entity.User;
import my.dubbo.provider.entity.dto.UserDto;
import my.dubbo.provider.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author _lizy
 * @version 1.0
 * @description user service
 * @date 2020/10/17 20:50
 */
@DubboService(version = "1.0.0")
public class UserServiceImpl implements IUserService {


    /**
     * @Description
     *      @Resource extUserMapper,byName注入，须严格命名属性名,userMapper会报错
     *      Autowired byType注入
     * @param null
     * @Return
     * @Author _lizy
     * @Date 2020/10/21 17:25
     */
    @Autowired
    private UserMapper userMapper;


    @Override
    @SentinelResource(value = "getUser")
    @RedisAnno("#id")
    @LogAnno
    public User getUser(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }


    @Override
    public IPage<User> getUserPage(UserDto userDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(userDTO.getAge()), User::getAge, userDTO.getAge());
        queryWrapper.eq(Objects.nonNull(userDTO.getId()), User::getId, userDTO.getId());
        queryWrapper.eq(Objects.isNull(userDTO.getNameLike()) && Objects.nonNull(userDTO.getName()), User::getName, userDTO.getName());
        queryWrapper.like(Objects.nonNull(userDTO.getNameLike()), User::getName, userDTO.getNameLike());

        return userMapper.selectPage(userDTO.getPage(), queryWrapper);
    }


//    @Override
//    public Integer countByName(String name) {
//        return userMapper.countByName(name);
//    }
//
//    @Override
//    public List<User> findByName(String name) {
//        return userMapper.findByName(name);
//    }


    @Override
    public Integer update(UserDto userDTO) {

        User user = userMapper.selectById(userDTO.getId());
        user.setName(userDTO.getName());
        return userMapper.updateById(user);

    }
}
