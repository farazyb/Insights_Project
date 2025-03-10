package com.sea.reporter.model.repository;

import com.sea.reporter.model.entity.CutOffTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CutOffTimeRepository  extends JpaRepository<CutOffTime, Long> {
    public CutOffTime findTopByOrderByCutofftimeIdDesc();
}
