package com.ky.flowable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ky.flowable.mapper.ExpenseMapper;
import com.ky.flowable.model.entity.Expense;
import com.ky.flowable.service.ExpenseService;
import org.springframework.stereotype.Service;

/**
* @author kouyang
* @description 针对表【BIZ_EXPENSE(报销申请表)】的数据库操作Service实现
* @createDate 2025-03-31 15:05:29
*/
@Service
public class ExpenseServiceImpl extends ServiceImpl<ExpenseMapper, Expense>
    implements ExpenseService{

}




