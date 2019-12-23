package com.example.excute;

import com.alibaba.fastjson.JSON;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.actions.ActionType;
import com.example.actions.ByType;
import com.example.controller.ExcuteLogController;
import com.example.controller.UICaseController;
import com.example.model.ExcuteLog;
import com.example.model.UICase;
import com.example.model.UIStep;
import com.example.tools.HttpRequestUtil;
import com.example.tools.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import static com.codeborne.selenide.Selenide.*;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/21 17:28
 * @Description:
 */
//@Listeners({ExtentTestNGIReporterListener.class})
@Listeners({TestNGSimpleReport.class})
public class ExcuteCase {

    /* 操作枚举类型 */
    private ActionType actionType;

    /* 操作枚举类型 */
    private ActionType actionKey;

    /* 页面元素 */
    private SelenideElement selenideElement;

    private static String excuteId;

    @BeforeSuite
    public void initExcuteLog() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        setExcuteId(uuid);
        ExcuteLog excuteLog = new ExcuteLog();
        excuteLog.setId(uuid);
        excuteLog.setCreatetime(new Date());
        ExcuteLogController.getInstance().getExcuteLogService().save(excuteLog);
    }

    @Test(dataProvider = "initData")
    public void excute(UICase uiCase, List<UIStep> uiStepList, ITestContext context) {
        ExcuteLog excuteLog = new ExcuteLog();
        excuteLog.setProjectid(uiCase.getProjectid());
        excuteLog.setId(ExcuteCase.getExcuteId());
        ExcuteLogController.getInstance().getExcuteLogService().updateNotNull(excuteLog);
        List<UIStep> uiSteps = JSON.parseArray(JSON.toJSONString(uiStepList), UIStep.class);
        Configuration.browser = uiSteps.get(0).getBrowsertype();
        Configuration.baseUrl = uiSteps.get(0).getUrl();
        Configuration.reportsFolder = "C:\\workspace\\report";
        Configuration.headless = true;
        Configuration.timeout = 30000;
        //传递项目ID
        context.setAttribute("projectId", uiCase.getProjectid());
        //输出UICaseId以便插入日志
        Reporter.log("使用的浏览器为：" + Configuration.browser + "打开的默认URL为：" + Configuration.baseUrl);
        Selenide.clearBrowserCookies();
        open("/");
        Reporter.log(String.valueOf(uiSteps.size()));
        //遍历uiSteps，执行具体的Step
        for (int i = 0; i < uiSteps.size(); i++) {
            Reporter.log("执行的步骤名称为：" + uiSteps.get(i).getName());
            excuteStep(uiSteps.get(i));
        }
        Selenide.closeWebDriver();
    }

    @DataProvider
    public Object[][] initData() {
        return UICaseController.getDateMap();
    }

//    部署之后暂时不删除该文件
//    @AfterMethod
//    public void initReport(){
//        File file = new File("D:\\workspace\\SpringBootDemo-master\\demo-test\\src\\main\\resources\\templates\\report.html");
//        //File file = new File("report.html");
//        if (file != null){
//            System.gc();
//            if (file.delete()){
//                System.out.println("删除文件成功");
//            }else {
//                System.out.println("删除文件失败");
//            }
//
//        }
//    }

    /**
     * 执行具体的操作步骤
     *
     * @param uiStep
     * @throws Exception
     */
    public void excuteStep(UIStep uiStep) {
        actionType = ActionType.getEnumByValue(uiStep.getActionType());
        actionKey = ActionType.getEnumByValue(uiStep.getActionKey());
        Reporter.log("选择的Action为：" + uiStep.getActionid());
        selenideElement = getElement(uiStep);
        Reporter.log("获取到的元素为：" + selenideElement);
        try {
            doAllAction(uiStep);
        } catch (Exception e) {
            Reporter.log(e.toString());
            Assert.fail("执行出现异常！");
        }

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
        SelenideElement element;
        if (StringUtil.isNull(path)) {
            return null;
        } else {
            Reporter.log("查找的locationType为：" + locationType + "元素路径为：" + path);
            switch (locationType) {
                case ByType.ID:
                    element = $(By.id(path)).should(Condition.enabled);
                    return checkElement(element);
                case ByType.NAME:
                    element = $(By.name(path)).should(Condition.enabled);
                    return checkElement(element);
                case ByType.XPATH:
                    element = $(By.xpath(path)).should(Condition.enabled);
                    return checkElement(element);
                case ByType.CSSSELECTOR:
                    element = $(By.cssSelector(path)).should(Condition.enabled);
                    return checkElement(element);
                case ByType.CLASSNAME:
                    element = $(By.className(path)).should(Condition.enabled);
                    return checkElement(element);
                case ByType.LINKTEXT:
                    element = $(By.linkText(path)).should(Condition.enabled);
                    return checkElement(element);
                case ByType.PARTIALLINKTEXT:
                    element = $(By.partialLinkText(path)).should(Condition.enabled);
                    return checkElement(element);
                case ByType.TAGNAME:
                    element = $(By.tagName(path)).should(Condition.enabled);
                    return checkElement(element);
                default:
                    element = $(By.xpath(path)).should(Condition.enabled);
                    return checkElement(element);
            }
        }
    }

    /**
     * 校验获取的元素
     *
     * @param element
     * @return
     */
    public SelenideElement checkElement(SelenideElement element) {
        if (element.toString().contains("Exception")) {
            Reporter.log("查询元素出现异常！" + element);
            Assert.fail("查询元素出现异常！");
            return null;
        } else {
            return element;
        }
    }

    /**
     * 执行
     *
     * @param uiStep
     * @throws Exception
     */
    private void doAllAction(UIStep uiStep) {
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
                try {
                    selenideElement.click();
                    break;
                } catch (Exception e) {
                    Reporter.log("点击元素时发生异常：" + e.toString());
                    Assert.fail();
                }
            case SENDKEY:
                try {
                    selenideElement.clear();
                    Reporter.log("输入参数为：" + uiStep.getDatakey());
                    //值由data提供
                    selenideElement.setValue(uiStep.getDatakey());
                    break;
                } catch (Exception e) {
                    Reporter.log("输入参数时发生异常：" + e.toString());
                    Assert.fail();
                }
            case MOVE:
                try {
                    selenideElement.hover();
                    break;
                } catch (Exception e) {
                    Reporter.log("浮动到元素上时发生异常：" + e.toString());
                    Assert.fail();
                }

            case RIGHTCLICK:
                try {
                    selenideElement.contextClick();
                    break;
                } catch (Exception e) {
                    Reporter.log("元素右击时发生异常：" + e.toString());
                    Assert.fail();
                }

            case DOUBLECLICK:
                try {
                    selenideElement.doubleClick();
                    break;
                } catch (Exception e) {
                    Reporter.log("元素双击时时发生异常：" + e.toString());
                    Assert.fail();
                }

            case DRAG:
                try {
                    //To的值（XPATH路径）由data提供
                    Reporter.log("移动到的元素为：" + $(By.xpath(uiStep.getDatakey())));
                    selenideElement.dragAndDropTo($(By.xpath(uiStep.getDatakey())));
                    break;
                } catch (Exception e) {
                    Reporter.log("拖动元素时发生异常：" + e.toString());
                    Assert.fail();
                }

            case ACCEPTALERT:
                try {
                    Reporter.log("执行接受Alert操作");
                    Selenide.confirm();
                    break;
                } catch (Exception e) {
                    Reporter.log("执行接受Alert操作时发生异常：" + e.toString());
                    Assert.fail();
                }

            case CANCELALERT:
                try {
                    Reporter.log("执行取消Alert操作");
                    Selenide.dismiss();
                    break;
                } catch (Exception e) {
                    Reporter.log("执行取消Alert操作时发生异常：" + e.toString());
                    Assert.fail();
                }

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
                Reporter.log("期望相等且预期值为：" + uiStep.getDatakey() + "实际值为：" + selenideElement.getText());
                selenideElement.waitUntil(Condition.matchesText(uiStep.getDatakey()), 30000);
                break;
            case NOTEQUALTEXT:
                Reporter.log("期望不相等且预期值为：" + uiStep.getDatakey() + "实际值为：" + selenideElement.getText());
                selenideElement.shouldNotHave(Condition.text(uiStep.getDatakey()));
                break;
            case EQUALHREFVALUE:
                //值由data提供
                Reporter.log("期望相等且预期href为：" + uiStep.getDatakey() + "实际href为：" + selenideElement.getAttribute("href"));
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
                try {
                    Reporter.log("根据参数：" + uiStep.getDatakey() + "切换至新窗口");
                    //nameOrHandleOrTitle
                    Selenide.switchTo().window(uiStep.getDatakey());
                    break;
                } catch (Exception e) {
                    Reporter.log("切换窗口时发生异常：" + e.toString());
                    Assert.fail();
                }

            case SWITCHTOFRAME:
                try {
                    Reporter.log("切换到的Iframe为：" + selenideElement);
                    // 切换frame
                    Selenide.switchTo().frame(selenideElement);
                    break;
                } catch (Exception e) {
                    Reporter.log("切换Iframe时发生异常：" + e.toString());
                    Assert.fail();
                }

            case SWITCHTODEFRAME:
                try {
                    Reporter.log("切换到默认的Iframe");
                    // 切换到默认frame
                    Selenide.switchTo().defaultContent();
                    break;
                } catch (Exception e) {
                    Reporter.log("切换到默认Iframe时发生异常：" + e.toString());
                    Assert.fail();
                }
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
        try {
            Reporter.log("当前执行的JS为：" + uiStep.getDatakey());
            Selenide.executeJavaScript(uiStep.getDatakey(), selenideElement);
        } catch (Exception e) {
            Reporter.log("执行JS时发生异常：" + e.toString());
            Assert.fail();
        }

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

    public static String getExcuteId() {
        return excuteId;
    }

    public static void setExcuteId(String excuteId) {
        ExcuteCase.excuteId = excuteId;
    }
}
