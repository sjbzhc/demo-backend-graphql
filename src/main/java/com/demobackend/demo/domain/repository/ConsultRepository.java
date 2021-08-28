package com.demobackend.demo.domain.repository;

import com.demobackend.demo.models.Consult;

import java.util.List;

public interface ConsultRepository {

    List<Consult> findAllByUserId(String id);

    List<Consult> findAll();

    Consult save(Consult consult);
}
