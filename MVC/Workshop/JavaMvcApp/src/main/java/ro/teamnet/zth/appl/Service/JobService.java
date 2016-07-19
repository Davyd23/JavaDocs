package ro.teamnet.zth.appl.Service;

import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public interface JobService {

    public List<Job> getAllJobs();

    public Job getJob(String id);

    public Job deleteJob(String id);

    public Job addJob(Job job);

}
