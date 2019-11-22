package com.example.actions;

/**
 * @Auther: Leo.hu
 * @Date: 2018/6/6 13:39
 * @Description: ActionEntity表中的枚举对象
 */
public enum ActionType {

    /**
     * 切换操作
     */
    SWITCH("SWITCH"),

    /**
     * 切换到新窗口
     */
    SWITCHWINDOW("SWITCHWINDOW"),

    /**
     * 关闭新窗口
     */
    CLOSENEWWINDOW("CLOSENEWWINDOW"),

    /**
     * 切换frame
     */
    SWITCHTOFRAME("SWITCHTOFRAME"),

    /**
     * 切换到默认frame
     */
    SWITCHTODEFRAME("SWITCHTODEFRAME"),

    /**
     * ACTIONS.
     */
    ACTIONS("ACTIONS"),

    /**
     * 点击事件
     */
    CLICK("CLICK"),

    /**
     *
     */
    IMPLICIT_CLICK("IMPLICIT_CLICK"),

    /**
     * 右击事件
     */
    RIGHTCLICK("RIGHTCLICK"),

    /**
     * The doubleClick.
     */
    DOUBLECLICK("DOUBLECLICK"),

    /**
     * The drag.
     */
    DRAG("DRAG"),

    /**
     * The hover.
     */
    HOVER("HOVER"),

    /**
     * The sendkey.
     */
    SENDKEY("SENDKEY"),

    /**
     * The move cursor.
     */
    MOVE("MOVE"),

    /**
     *  确认alert
     */
    ACCEPTALERT("ACCEPTALERT"),

    /**
     * 取消alert
     */
    CANCELALERT("CANCELALERT"),

    /**
     * 获取alert文本
     */
    GETALERTTEXT("GETALERTTEXT"),

    /**
     * The switchFrame.
     */
    SWITCHFRAME("SWITCHFRAME"),

    /**
     * The Keyboard
     */
    KEYBOARD("KEYBOARD"),

    /**
     * The javascript.
     */
    JAVASCRIPT("JAVASCRIPT"),

    /**
     * The check.
     */
    CHECK("CHECK"),

    /**
     * The Element Exist.
     */
    ELEMENTEXIST("ELEMENTEXIST"),

    /**
     * The Element NotExist.
     */
    ELEMENTNOTEXIST("ELEMENTNOTEXIST"),

    /**
     * The Equal Text.
     */
    EQUALTEXT("EQUALTEXT"),

    /**
     * The NotEqual Text.
     */
    NOTEQUALTEXT("NOTEQUALTEXT"),

    /**
     * The Equal HrefValue.
     */
    EQUALHREFVALUE("EQUALHREFVALUE"),

    /**
     * The NotEqual HrefValue.
     */
    NOTEQUALHREFVALUE("NOTEQUALHREFVALUE"),

    /**
     * get attribute and if equal the given value.
     */
    GETATTRIBUTE("GETATTRIBUTE1"),

    /**
     * 强制等待
     */
    WAIT("WAIT"),

    /**
     * 元素滚动操作
     */
    SCROLL("SCROLL"),

    /**
     * 通过接口打开URL
     */
    OPEN("OPEN"),

    /**
     * 通过浏览器打开URL
     */
    OPENBR("OPENBR");




    /**
     * The value.
     */
    private String value;

    /**
     * Instantiates a new action type.
     *
     * @param value the value
     */
    private ActionType(String value) {
        this.value = value;
    }

    /**
     * 通过value获取enum对象
     *
     * @param value the value
     * @return the by value
     */
    public static ActionType getEnumByValue(String value) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getValue().toUpperCase().equals(value.toUpperCase())) {
                return actionType;
            }
        }
        return null;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
