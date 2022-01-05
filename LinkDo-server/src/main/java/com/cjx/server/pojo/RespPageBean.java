package com.cjx.server.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 含分页公用返回对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {


    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据
     */
    private List<?> data;
}
