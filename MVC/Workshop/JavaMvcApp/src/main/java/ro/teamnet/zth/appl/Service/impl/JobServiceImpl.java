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
}
