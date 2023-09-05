package carsharing.menu;

import java.lang.reflect.Method;

public class MenuItem {
    private final Object obj;
    private final String label;
    private final String target;
    private String param1 = null;

    private boolean isExitItem;

    public MenuItem(String label) { this(label, null, null); }

    public MenuItem(String label, Object obj, String target) {
        this.label = label;
        this.obj = obj;
        this.target = target;
    }

    public MenuItem(String label, Object obj, String target, String param1) {
        this.label = label;
        this.obj = obj;
        this.target = target;
        this.param1 = param1;
    }

    public String getLabel() { return label; }

    void invoke() {
        if (target == null) return;

        try {
            if (param1 != null) {
                Method method = obj.getClass().getMethod(target, String.class);
                method.invoke(obj, param1);
            } else {
                Method method = obj.getClass().getMethod(target);
                method.invoke(obj);
            }
        }
        catch (Exception e) { System.out.println(e.getMessage()); }
    }

    boolean isExitItem() { return isExitItem; }

    void setExitItem() { this.isExitItem = true; }

    public String toString() { return getLabel(); }
}
