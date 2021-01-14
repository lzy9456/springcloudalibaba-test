package my.dubbo.genricclass;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import my.dubbo.provider.entity.User;
import my.dubbo.provider.entity.dto.UserDto;

/**
 * 满减
 */
@Slf4j
public class UserDaoImpl extends BaseDao<UserDto, User> implements IDao<UserDto, User> {


    public UserDto getById(int id){
        return toDto(new User().setId(id).setName("lziy").setAge(23));
    }


    public static void main(String[] args) {
        UserDto dto = new UserDaoImpl().getById(1);
        log.info("{}: {}",dto.getClass().getSimpleName(), JSON.toJSONString(dto));
    }

}
