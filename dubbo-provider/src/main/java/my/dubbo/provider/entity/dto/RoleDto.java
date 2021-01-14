package my.dubbo.provider.entity.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
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


    public static RoleDto newRD() {
        return new RoleDto();
    }

}
