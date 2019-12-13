package com.example.controller;

import com.example.model.ExcuteLog;
import com.example.model.ExcuteLogDetails;
import com.example.model.ExcuteStepDetails;
import com.example.service.ExcuteLogDetailsService;
import com.example.service.ExcuteLogService;
import com.example.service.ExcuteStepDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/12 14:20
 * @Description:
 */

@RestController
public class NewReportController {

    @Autowired
    private ExcuteLogDetailsService excuteLogDetailsService;

    @Autowired
    private ExcuteStepDetailsService excuteStepDetailsService;

    @Autowired
    private ExcuteLogService excuteLogService;

    /**
     * 新版报告页面
     * @param excuteLogId
     * @return
     */
    @RequestMapping(value = "/newReport", method = RequestMethod.GET)
    public ModelAndView toNewReportPage(String excuteLogId){
        ModelAndView modelAndView = new ModelAndView();
        ExcuteLog excuteLog = excuteLogService.selectByKey(excuteLogId);
        ExcuteLogDetails excuteLogDetails = new ExcuteLogDetails();
        excuteLogDetails.setExcutelogid(excuteLogId);
        List<ExcuteLogDetails> excuteLogDetailsList = excuteLogDetailsService.selectBy(excuteLogDetails);
        List<ExcuteLogDetails> successList = new ArrayList<>();
        List<ExcuteLogDetails> failList = new ArrayList<>();
        List<ExcuteLogDetails> skipList = new ArrayList<>();
        float time = 0;
        for (int i = 0; i < excuteLogDetailsList.size(); i++) {
            //计算总耗时
            time = time + Float.parseFloat(excuteLogDetailsList.get(i).getExcutetime());
            if (excuteLogDetailsList.get(i).getStatus().equals("SUCCESS")){
                successList.add(excuteLogDetailsList.get(i));
            }
            if (excuteLogDetailsList.get(i).getStatus().equals("FAILURE")){
                failList.add(excuteLogDetailsList.get(i));
            }
            if (excuteLogDetailsList.get(i).getStatus().equals("SKIP")){
                skipList.add(excuteLogDetailsList.get(i));
            }
        }
        modelAndView.addObject("time", time);
        modelAndView.addObject("sum", excuteLogDetailsList.size());
        modelAndView.addObject("sumList", excuteLogDetailsList);
        modelAndView.addObject("success", successList.size());
        modelAndView.addObject("successList", successList);
        modelAndView.addObject("fail", failList.size());
        modelAndView.addObject("failList", failList);
        modelAndView.addObject("skip", skipList.size());
        modelAndView.addObject("skipList", skipList);
        modelAndView.addObject("excuteLog", excuteLog);
        modelAndView.setViewName("newReport");
        return modelAndView;
    }

    /**
     * 返回所有的步骤
     * @param excuteLogDetailId
     * @return
     */
    @RequestMapping(value = "/newReport/steps", method = RequestMethod.GET)
    public String[] getStep(Integer excuteLogDetailId){
        ExcuteStepDetails excuteStepDetails = new ExcuteStepDetails();
        excuteStepDetails.setExcutelogdetailid(excuteLogDetailId);
        List<ExcuteStepDetails> excuteStepDetailsList = excuteStepDetailsService.selectBy(excuteStepDetails);
        String[] stepStr = new String[excuteStepDetailsList.size()];
        for (int i = 0; i < excuteStepDetailsList.size(); i++) {
            stepStr[i] = excuteStepDetailsList.get(i).getStepdeatail();
        }
        return stepStr;
    }
}
