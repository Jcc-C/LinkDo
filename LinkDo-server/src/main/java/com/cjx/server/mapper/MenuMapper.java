package com.cjx.server.mapper;

import com.cjx.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CJX
 * @since 2021-08-05
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户ID查询菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenuByAdminId(Integer id);

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
