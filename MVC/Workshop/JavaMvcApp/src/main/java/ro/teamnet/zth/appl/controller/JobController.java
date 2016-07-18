package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.Service.impl.JobServiceImpl;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyController(urlPath = "/jobs")
public class JobController {
    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAlljobs(){
        return new JobServiceImpl().getAllJobs();
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneJob(){
        return "oneRandomJob";
    }
}
