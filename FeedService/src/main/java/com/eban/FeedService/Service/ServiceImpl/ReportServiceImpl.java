package com.eban.FeedService.Service.ServiceImpl;

import com.eban.FeedService.Model.Report;
import com.eban.FeedService.Repository.ReportRepository;
import com.eban.FeedService.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository repository;

    @Override
    public Report createReport(Report report) {
        return repository.save(report);
    }
}
