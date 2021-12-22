package com.example.demo.BusinessLogic;

import com.example.demo.BusinessLogic.Models.IJobPostService;
import com.example.demo.DataAccess.Entity.JobPost;
import com.example.demo.DataAccess.Entity.JobPostSkillSet;
import com.example.demo.DataAccess.Repository.JobPostRepository;
import com.example.demo.DataAccess.Repository.JobPostSkillSetRepository;
import com.example.demo.DataAccess.Repository.SkillSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class JobPostService implements IJobPostService {
    @Autowired
    JobPostRepository jobPostRepository;
    @Autowired
    JobPostSkillSetRepository jobPostSkillSetRepository;
    @Autowired
    SkillSetRepository skillSetRepository;

    @Override
    public void saveJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);
    }


    @Override
    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    @Override
    public JobPost getJobPostById(int id) {
        return jobPostRepository.findById(id).get();
    }

    @Override
    public List<String> getNamesJobPosts(String login) {
        return jobPostRepository.getJobPostsNames(login);
    }

    @Override
    public List<JobPost> getFilterJobPosts(String position, String jobType) {
        String pos = "", type = "";
        if(position!=null) {
            pos = position.equals("") ? null : position;
        }
        else pos = null;
        if(jobType!=null) {
            type = jobType.equals("") ? null : jobType;
        }
        else type = null;
        return jobPostRepository.getFilteredJobPosts(pos, type);
    }

    @Override
    public List<JobPost> getSortedByDateJobPosts() {
        List<JobPost> jobPostList = getAllJobPosts();
        jobPostList.sort(new Comparator<JobPost>() {
            @Override
            public int compare(JobPost post1, JobPost post2){
                return post1.getCompensationTerm().compareTo(post2.getCompensationTerm());
            }
        });
        return jobPostList;
    }

    @Override
    public List<JobPost> getSortedByResponsesJobPosts() {
        List<JobPost> jobPostList = getAllJobPosts();
        jobPostList.sort(new Comparator<JobPost>() {
            @Override
            public int compare(JobPost post1, JobPost post2){
                if(post1.getActivities().size() == post2.getActivities().size()) return 0;
                else if(post1.getActivities().size() < post2.getActivities().size()) return 1;
                else return -1;
            }
        });
        return jobPostList;
    }

    @Override
    public void deleteJobPost(int id) {
        jobPostRepository.delete(getJobPostById(id));
    }

    @Override
    public JobPostSkillSet getJobPostSkillSetById(int id) {
        return jobPostSkillSetRepository.findById(id).get();
    }

    @Override
    public void deleteJobPostSkill(int id) {
        jobPostSkillSetRepository.delete(jobPostSkillSetRepository.findById(id).get());
    }

    @Override
    public void saveJobPostSkills(JobPostSkillSet jobPostSkillSet) {
        skillSetRepository.save(jobPostSkillSet.getSkillSet());
        jobPostSkillSetRepository.save(jobPostSkillSet);
    }
}
