package com.cjx.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.server.pojo.MenuRole;
import com.cjx.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CJX
 * @since 2021-08-05
 */
public interface IMenuRoleService extends IService<MenuRole> {


    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
