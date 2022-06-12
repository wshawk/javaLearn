package com.hawk.example.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @author hawk
 * @package com.hawk.example.transactional
 * @desc
 * @date 2022/1/18
 */

@Component
@Scope("prototype") // 申明为多例,解决线程安全问题。
public class TransactionUtil {

    // 全局接受事务状态
    private TransactionStatus transactionStatus;

    // 获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    // 开启事务
    public TransactionStatus begin() {
        System.out.println("开启事务");
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    // 提交事务
    public void commit(TransactionStatus transaction) {
        System.out.println("提交事务");
        if(dataSourceTransactionManager != null) dataSourceTransactionManager.commit(transaction);
    }

    // 回滚事务
    public void rollback(TransactionStatus transaction) {
        System.out.println("回滚事务...");
        if(dataSourceTransactionManager != null) dataSourceTransactionManager.rollback(transaction);
    }
}
