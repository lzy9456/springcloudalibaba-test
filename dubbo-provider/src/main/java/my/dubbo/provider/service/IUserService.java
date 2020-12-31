package my.dubbo.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import my.dubbo.provider.entity.User;
import my.dubbo.provider.entity.dto.UserDto;


/**
 * @Description
 *
 * @Author _lizy
 * @Date 2020/10/19 22:39
 */
public interface IUserService {
    User getUser(Integer id);
    Integer update(UserDto userDTO);
    IPage<User> getUserPage(UserDto userDTO);

//    Integer countByName(String name);
//    List<User> findByName(String name);
}
