package com.portfolio.ecsite.service.repository.sample;

import com.portfolio.ecsite.sample.SampleDTO;
import org.springframework.stereotype.Repository;

@Repository
public class SampleRepository {

    public SampleRecord select(){
        return new SampleRecord("Hello, world!!");
    }
}
