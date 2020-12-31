package my.dubbo.provider.entity.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lizy
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDto extends Model<RoleDto> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer roleType;

    private String address;


    public static final String NAME = "name";

    public static final String ROLETYPE = "roletype";

    public static final String ADDRESS = "address";

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public Integer getId() {
        return id;
    }

    public RoleDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public RoleDto setRoleType(Integer roleType) {
        this.roleType = roleType;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RoleDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public static RoleDto newRD() {
        return new RoleDto();
    }

}
