package ro.teamnet.zth.appl.Service.impl;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.Service.JobService;
import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyService
public class JobServiceImpl implements JobService {
    @Override
    public List<Job> getAllJobs() {
        return new JobDao().getAllJobs();
    }

    @Override
    public Job getJob(String id) {
        return new JobDao().getJobById(id);
    }

    @Override
    public Job deleteJob(String id) {
        Job job=getJob(id);
        new JobDao().deleteJob(job);

        return job;
    }

    @Override
    public Job addJob(Job job) {
        return new JobDao().insertJob(job);
    }
}
