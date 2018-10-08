package com.lijie.service.impl;

import com.lijie.Dao.EvaluateDao;
import com.lijie.pojo.Evaluate;
import com.lijie.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateServiceImpl implements EvaluateService {
    @Autowired
    EvaluateDao evaluateDao;
    @Override
    public int addEvaluate(Evaluate evaluate) {
        evaluate = evaluateDao.saveAndFlush(evaluate);
        if (evaluate == null) {
            return  0;
        }else {
            return  1;
        }
    }
}
