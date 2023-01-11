package com.portfolio.ecsite.service.sample;

import com.portfolio.ecsite.service.repository.sample.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository repository;
    public SampleEntity find() {
        var record = repository.select();
        return new SampleEntity(record.getContent());
    }
}
