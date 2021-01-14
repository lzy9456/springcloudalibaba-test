package my.dubbo.provider.entity.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author _lizy
 * @version 1.0
 * @description 用户
 * @date 2020/10/17 20:53
 */
@Data
@Accessors(chain = true)
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String nameLike;
    private Integer age;
    private Page page;




    public static UserDto newUD() {
        return new UserDto();
    }
}
