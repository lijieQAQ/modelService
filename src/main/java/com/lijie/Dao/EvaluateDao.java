package com.lijie.Dao;

import com.lijie.pojo.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluateDao  extends JpaRepository<Evaluate,Integer> {
}
