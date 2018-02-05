package cn.e3mall.bean;

import java.io.Serializable;
import java.util.List;

public class EasyUiPageBean implements Serializable {
    public Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer toatl) {
        this.total = toatl;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public List<?> rows;

}
