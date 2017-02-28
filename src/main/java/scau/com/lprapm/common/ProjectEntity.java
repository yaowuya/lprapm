package scau.com.lprapm.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public class ProjectEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<?> list;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "list=" + list +
                '}';
    }
}
