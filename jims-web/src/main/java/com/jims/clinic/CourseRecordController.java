package com.jims.clinic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qinlongxin on 2016/4/20.
 */
@Controller
@RequestMapping(value = "/courseRecord")
public class CourseRecordController {
    @RequestMapping(value = "recordUrl",method = {RequestMethod.GET,RequestMethod.POST})
    public String recordUser(String type){
        String html="";
        if (type=="1"){
            html="modules/clinic/course/courseRecordSuperiordocrecord";
        }
        if (type=="2"){
            html="modules/clinic/course/courseRecordEachdis";
        }
        if (type=="3"){
            html="modules/clinic/course/courseRecordStage";
        }
        return html;
    }
}
