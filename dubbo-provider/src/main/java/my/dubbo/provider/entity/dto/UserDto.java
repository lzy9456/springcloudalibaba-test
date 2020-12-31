package my.dubbo.provider.entity.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * @author _lizy
 * @version 1.0
 * @description 用户
 * @date 2020/10/17 20:53
 */
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String nameLike;
    private Integer age;
    private Page page;





    public UserDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public UserDto setNameLike(String nameLike) {
        this.nameLike = nameLike;
        return this;
    }

    public UserDto setPage(Page page) {
        this.page = page;
        return this;
    }

    public UserDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public static UserDto newUD() {
        return new UserDto();
    }
}
