package my.dubbo.genricclass;

import com.example.utils.BeanCovert;
import com.example.utils.GenricUtils;

/**
 * dto、do转换公共方法toDto、toDo
 */
public abstract class BaseDao<T, D> implements IDao<T, D> {


    /**
     * 根据BaseDao<T,D>泛型<T,D>, 获取子类具体dto或do类转换<T即dto, D即do>
     * @param doo
     * @return dto
     */
    public T toDto(D doo) {
        return BeanCovert.transf(doo, GenricUtils.getGenricClassIdx1(this.getClass()));
    }

    public D toDo(T dto) {
        return BeanCovert.transf(dto, GenricUtils.getGenricClassIdx2(this.getClass()));
    }

}
