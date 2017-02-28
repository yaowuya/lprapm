package scau.com.lprapm.entity;

public class Menu {
    private Integer menuId;

    private String menuName;

    private String menuIClass;

    private String menuHref;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuIClass() {
        return menuIClass;
    }

    public void setMenuIClass(String menuIClass) {
        this.menuIClass = menuIClass == null ? null : menuIClass.trim();
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref == null ? null : menuHref.trim();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuIClass='" + menuIClass + '\'' +
                ", menuHref='" + menuHref + '\'' +
                '}';
    }
}