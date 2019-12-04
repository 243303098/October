package com.example.excute;

import com.alibaba.fastjson.JSON;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.actions.ActionType;
import com.example.actions.ByType;
import com.example.controller.UICaseController;
import com.example.model.UICase;
import com.example.model.UIStep;
import com.example.tools.HttpRequestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;
import static com.codeborne.selenide.Selenide.*;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/21 17:28
 * @Description:
 */
@Listeners({ExtentTestNGIReporterListener.class})
public class ExcuteCase {

    /* 操作枚举类型 */
    private ActionType actionType;

    /* 操作枚举类型 */
    private ActionType actionKey;

    /* 页面元素 */
    private SelenideElement selenideElement;

    @Test(dataProvider = "initData")
    public void excute(UICase uiCase, List<UIStep> uiStepList) throws Exception {
        List<UIStep> uiSteps = JSON.parseArray(JSON.toJSONString(uiStepList), UIStep.class);
        Configuration.browser = uiSteps.get(0).getBrowsertype();
        Configuration.baseUrl = uiSteps.get(0).getUrl();
        Configuration.headless = true;
        Configuration.timeout = 30000;
        //输出UICaseId以便插入日志
        Reporter.log(uiCase.getId().toString());
        Reporter.log("使用的浏览器为："+ Configuration.browser + "打开的默认URL为：" + Configuration.baseUrl);
        Selenide.clearBrowserCookies();
        open("/");
        //遍历uiSteps，执行具体的Step
        for (int i = 0; i < uiSteps.size(); i++) {
            Reporter.log("执行的步骤名称为：" + uiSteps.get(i).getName());
            excuteStep(uiSteps.get(i));
        }
        Selenide.close();
    }

    @DataProvider
    public Object[][] initData() {
        return UICaseController.getDateMap();
    }

    /**
     * 执行具体的操作步骤
     *
     * @param uiStep
     * @throws Exception
     */
    public void excuteStep(UIStep uiStep) throws Exception {
        actionType = ActionType.getEnumByValue(uiStep.getActionType());
        actionKey = ActionType.getEnumByValue(uiStep.getActionKey());
        Reporter.log("选择的Action为：" + actionType + actionKey);
        selenideElement = getElement(uiStep);
        Reporter.log("获取到的元素为：" + selenideElement);
        doAllAction(uiStep);
    }


    /**
     * 获取selenide元素
     *
     * @param uiStep
     * @return
     */
    private SelenideElement getElement(UIStep uiStep) {
        String locationType = uiStep.getUiElementByType();
        String path = uiStep.getUiElementPath();
        Reporter.log("查找的locationType为：" + locationType + "元素路径为：" + path);
        switch (locationType) {
            case ByType.ID:
                return $(By.id(path)).shouldBe(Condition.enabled);
            case ByType.NAME:
                return $(By.name(path)).shouldBe(Condition.enabled);
            case ByType.XPATH:
                return $(By.xpath(path)).shouldBe(Condition.enabled);
            case ByType.CSSSELECTOR:
                return $(By.cssSelector(path)).shouldBe(Condition.enabled);
            case ByType.CLASSNAME:
                return $(By.className(path)).shouldBe(Condition.enabled);
            case ByType.LINKTEXT:
                return $(By.linkText(path)).shouldBe(Condition.enabled);
            case ByType.PARTIALLINKTEXT:
                return $(By.partialLinkText(path)).shouldBe(Condition.enabled);
            case ByType.TAGNAME:
                return $(By.tagName(path)).shouldBe(Condition.enabled);
            default:
                return $(By.xpath(path)).shouldBe(Condition.enabled);
        }
    }

    /**
     * 执行
     *
     * @param uiStep
     * @throws Exception
     */
    private void doAllAction(UIStep uiStep) throws Exception {
        switch (actionType) {
            case ACTIONS:
                actionsAction(uiStep);
                break;
            case CHECK:
                executeCheckAction(uiStep);
                break;
            case SWITCH:
                switchAction(uiStep);
                break;
            case JAVASCRIPT:
                javaScriptAction(uiStep);
                break;
            case KEYBOARD:
                pressKeyboard(uiStep);
                break;
            default:
                break;
        }
    }

    /**
     * ACTION
     *
     * @param uiStep
     */
    private void actionsAction(UIStep uiStep) {
        switch (actionKey) {
            case CLICK:
                selenideElement.click();
                break;
            case SENDKEY:
                selenideElement.clear();
                Reporter.log("输入参数为：" + uiStep.getDatakey());
                //值由data提供
                selenideElement.setValue(uiStep.getDatakey());
                break;
            case MOVE:
                selenideElement.hover();
                break;
            case RIGHTCLICK:
                selenideElement.contextClick();
                break;
            case DOUBLECLICK:
                selenideElement.doubleClick();
                break;
            case DRAG:
                //To的值（XPATH路径）由data提供
                Reporter.log("移动到的元素为：" + $(By.xpath(uiStep.getDatakey())));
                selenideElement.dragAndDropTo($(By.xpath(uiStep.getDatakey())));
                break;
            case ACCEPTALERT:
                Reporter.log("接受Alert");
                Selenide.confirm();
                break;
            case CANCELALERT:
                Reporter.log("取消Alert");
                Selenide.dismiss();
                break;
            case OPEN:
                HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
                httpRequestUtil.ynchronousGetRequests(uiStep.getDatakey());
                break;
            case OPENBR:
                open(uiStep.getDatakey());
                break;
            case WAIT:
                Reporter.log("强制等待3S");
                sleep(3000);
            default:
                break;
        }
    }

    /**
     * 校验
     *
     * @param uiStep
     */
    private void executeCheckAction(UIStep uiStep) {
        switch (actionKey) {
            case EQUALTEXT:
                Reporter.log("期望相等且预期值为：" + uiStep.getDatakey() + "实际值为：" +selenideElement.getText());
                selenideElement.waitUntil(Condition.matchesText(uiStep.getDatakey()), 30000);
                break;
            case NOTEQUALTEXT:
                Reporter.log("期望不相等且预期值为：" + uiStep.getDatakey() + "实际值为：" +selenideElement.getText());
                selenideElement.shouldNotHave(Condition.text(uiStep.getDatakey()));
                break;
            case EQUALHREFVALUE:
                //值由data提供
                Reporter.log("期望相等且预期href为：" + uiStep.getDatakey() + "实际href为：" +selenideElement.getAttribute("href"));
                selenideElement.shouldHave(Condition.attribute("href", uiStep.getDatakey()));
                break;
            case ELEMENTSELECTED:
                selenideElement.shouldHave(Condition.cssClass("selected"));
                break;
            case ELEMENTEXIST:
                selenideElement.exists();
                break;
            case ELEMENTCHECKED:
                selenideElement.shouldHave(Condition.cssClass("checked"));
                break;
            default:
                break;
        }
    }

    /**
     * 切换窗口，值由data提供
     *
     * @param uiStep
     */
    private void switchAction(UIStep uiStep) {
        switch (actionKey) {
            case SWITCHWINDOW:
                Reporter.log("根据参数：" + uiStep.getDatakey() + "切换至新窗口");
                //nameOrHandleOrTitle
                Selenide.switchTo().window(uiStep.getDatakey());
                break;
            case SWITCHTOFRAME:
                Reporter.log("切换到的Iframe为：" + selenideElement);
                // 切换frame
                Selenide.switchTo().frame(selenideElement);
                break;
            case SWITCHTODEFRAME:
                Reporter.log("切换到默认的Iframe");
                // 切换到默认frame
                Selenide.switchTo().defaultContent();
                break;
            default:
                break;
        }
    }

    /**
     * 执行JS
     *
     * @param uiStep
     */
    private void javaScriptAction(UIStep uiStep) {
        Reporter.log("当前执行的JS为：" + uiStep.getDatakey());
        Selenide.executeJavaScript(uiStep.getDatakey(), selenideElement);
    }

    /**
     * 执行键盘事件，值由data提供
     *
     * @param uiStep
     */
    private void pressKeyboard(UIStep uiStep) {
        Reporter.log("输入的键盘事件为：" + uiStep.getDatakey());
        selenideElement.sendKeys(Keys.valueOf(uiStep.getDatakey()));
    }
}
