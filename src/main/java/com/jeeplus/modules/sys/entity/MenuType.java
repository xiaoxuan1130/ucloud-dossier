package com.jeeplus.modules.sys.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author gongtao
 * @version 2018-10-30 17:01
 **/
public class MenuType implements Serializable {

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 菜单类型
     */
    private String menuTypeCode;

    public MenuType() {
    }

    public MenuType(String menuId, String menuTypeCode) {
        this.menuId = menuId;
        this.menuTypeCode = menuTypeCode;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuTypeCode() {
        return menuTypeCode;
    }

    public void setMenuTypeCode(String menuTypeCode) {
        this.menuTypeCode = menuTypeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuType menuType = (MenuType) o;
        return Objects.equals(menuId, menuType.menuId) &&
                Objects.equals(menuTypeCode, menuType.menuTypeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, menuTypeCode);
    }

    @Override
    public String toString() {
        return "MenuType{" +
                "menuId='" + menuId + '\'' +
                ", menuTypeCode='" + menuTypeCode + '\'' +
                '}';
    }
}
