package com.cjx.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cjx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CJX
 * @since 2021-08-05
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 通过用户ID查询菜单列表
     * @return
     */
    List<Menu> getMenuByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusByRole();

    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> getAllMenus();
}
