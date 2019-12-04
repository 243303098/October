package com.example.quartz;

import com.example.controller.UICaseController;
import com.example.excute.ExcuteCase;
import com.example.excute.ExtentTestNGIReporterListener;
import com.example.model.UICase;
import com.example.model.UIStep;
import com.example.service.UICaseService;
import com.example.service.UIStepService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.TestNG;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Job 的实例要到该执行它们的时候才会实例化出来。每次 Job 被执行，一个新的 Job 实例会被创建。
 * 其中暗含的意思就是你的 Job 不必担心线程安全性，因为同一时刻仅有一个线程去执行给定 Job 类的实例，甚至是并发执行同一 Job 也是如此。
 * @DisallowConcurrentExecution 保证上一个任务执行完后，再去执行下一个任务，这里的任务是同一个任务
 */
@DisallowConcurrentExecution
public class SchedulerJob implements Job,Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UICaseService uiCaseService;

	@Autowired
	private UIStepService uiStepService;

	@Override
	public void execute(JobExecutionContext context) {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String caseIds = dataMap.getString("jobParam");
		String[] caseIdArr = caseIds.split(",");
		List<Integer> caseIdList = new ArrayList<>();
		for (int i = 0; i < caseIdArr.length; i++) {
			caseIdList.add(Integer.valueOf(caseIdArr[i]));
		}
		List<UICase> uiCaseList = uiCaseService.getUICaseByIdIn(caseIdList);
		Object dateMapDetail[][] = new Object[uiCaseList.size()][2];
		for (int i = 0; i < uiCaseList.size(); i++) {
			List<Integer> moduleIdList = new ArrayList();
			List<UIStep> uiStepList = new ArrayList<>();
			UIStep uiStep = new UIStep();
			String[] modulearr = uiCaseList.get(i).getModuleid().split(",");
			for (String s : modulearr) {
				moduleIdList.add(Integer.valueOf(s));
			}
			for (int j = 0; j < moduleIdList.size(); j++) {
				uiStep.setModuleid(moduleIdList.get(j));
				uiStepList.addAll(uiStepService.getUIStepBy(uiStep));
			}
			dateMapDetail[i][0] = uiCaseList.get(i);
			dateMapDetail[i][1] = uiStepList;
		}
		UICaseController.setDateMap(dateMapDetail);
		TestNG testNG = new TestNG();
		Class[] listenerClass = {ExtentTestNGIReporterListener.class};
		testNG.setListenerClasses(Arrays.asList(listenerClass));
		testNG.setTestClasses(new Class[]{ExcuteCase.class});
		testNG.run();
	}
}
