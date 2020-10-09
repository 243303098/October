package com.example.excute;

import com.example.controller.*;
import com.example.model.ExcuteLog;
import com.example.model.ExcuteLogDetails;
import com.example.model.ExcuteStepDetails;
import com.example.model.Project;
import com.example.rabbitmq.ConsumeMq;
import com.example.service.MailService;
import com.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 09:55
 * @Description:
 */
public class TestNGSimpleReport implements ITestListener, IReporter {

    @Autowired
    private MailService mailService;

    @Autowired
    private ProjectService projectService;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        //设置重新执行次数为0
        ConsumeMq.setRetryCount(0);
        List<ITestResult> list = new ArrayList<>();
        Integer projectId = null;

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                IResultMap passedTests = testContext.getPassedTests();
                IResultMap failedTests = testContext.getFailedTests();
                IResultMap skippedTests = testContext.getSkippedTests();
                projectId = Integer.valueOf(testContext.getAttribute("projectId").toString());
                list.addAll(this.listTestResult(passedTests));
                list.addAll(this.listTestResult(failedTests));
                list.addAll(this.listTestResult(skippedTests));
            }
        }
        this.sort(list);
        this.outputResult(list, projectId);
    }

    private ArrayList<ITestResult> listTestResult(IResultMap resultMap) {
        Set<ITestResult> results = resultMap.getAllResults();
        return new ArrayList<>(results);
    }

    private void sort(List<ITestResult> list) {
        Collections.sort(list, (r1, r2) -> {
            if (r1.getStartMillis() > r2.getStartMillis()) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    private void outputResult(List<ITestResult> list, Integer projectId) {
        Boolean flag = true;
        int successCount = 0;
        for (ITestResult result : list) {
            if (this.getStatus(result.getStatus()).equals("FAILURE")) {
                flag = false;
            }else {
                successCount = successCount + 1;
            }
            //存储各个case的结果
            ExcuteLogDetails excuteLogDetails = new ExcuteLogDetails();
            excuteLogDetails.setExcutelogid(ExcuteCase.getExcuteId());
            excuteLogDetails.setExcutetime(String.valueOf((result.getEndMillis() - result.getStartMillis()) / 1000));
            excuteLogDetails.setStarttime(this.formatDate(result.getStartMillis()));
            excuteLogDetails.setStatus(this.getStatus(result.getStatus()));
            excuteLogDetails.setExcutename(result.getTestClass().getRealClass().getName());
            excuteLogDetails.setMethodname(result.getMethod().getMethodName());
            ExcuteLogDetailsController.getInstance().getExcuteLogDetailsService().save(excuteLogDetails);

            //存储具体的执行Log
            List<ExcuteStepDetails> excuteStepDetailsList = new ArrayList<>();
            for (int i = 0; i < Reporter.getOutput(result).size(); i++) {
                ExcuteStepDetails excuteStepDetails = new ExcuteStepDetails();
                excuteStepDetails.setExcutelogdetailid(ExcuteLogDetailsController.getInstance().getExcuteLogDetailsService().getLastExcuteLog().getId());
                if (Reporter.getOutput(result).get(i).length() > 250){
                    excuteStepDetails.setStepdeatail(Reporter.getOutput(result).get(i).substring(0,250));
                }else {
                    excuteStepDetails.setStepdeatail(Reporter.getOutput(result).get(i));
                }
                excuteStepDetailsList.add(excuteStepDetails);
            }
            ExcuteStepDetailsController.getInstance().getExcuteStepDetailsService().saveByList(excuteStepDetailsList);
        }

        //更新执行记录的状态
        ExcuteLog excuteLog = new ExcuteLog();
        excuteLog.setId(ExcuteCase.getExcuteId());
        if (flag || successCount >= list.size()) {
            excuteLog.setStatus("SUCCESS");
        } else {
            excuteLog.setStatus("FAILURE");
            //若发生错误则发送邮件通知
            Project project = BaseConfigController.getInstance().getProjectService().selectByKey(projectId);
            MailController.getInstance().getMailService().sendSimpleMail(project.getMail(), "异常通知", "FAILURE，请登录后台查看具体Report");
        }
        ExcuteLogController.getInstance().getExcuteLogService().updateNotNull(excuteLog);
    }

    private String getStatus(int status) {
        String statusString = null;
        switch (status) {
            case 1:
                statusString = "SUCCESS";
                break;
            case 2:
                statusString = "FAILURE";
                break;
            case 3:
                statusString = "SKIP";
                break;
            default:
                break;
        }
        return statusString;
    }

    private String formatDate(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

}
