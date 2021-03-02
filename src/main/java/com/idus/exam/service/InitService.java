package com.idus.exam.service;

import com.idus.exam.repository.InitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InitService {


    private final InitRepository initRepository;



    @PostConstruct
    public void initTables() throws Exception {
        initRepository.dropUserTable();
        initRepository.dropOrderTable();
        initRepository.createOrderTable();
        initRepository.createUserTable();
    }

    @PreDestroy
    public void dropTables() throws Exception {
        initRepository.dropUserTable();
        initRepository.dropOrderTable();
    }

}
