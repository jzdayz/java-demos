package io.github.jzdayz.mybatis;

import com.p6spy.engine.common.StatementInformation;
import com.p6spy.engine.logging.LoggingEventListener;

import java.sql.SQLException;

public class LoggingEventListenerCustom extends LoggingEventListener {

    @Override
    public void onAfterAnyAddBatch(StatementInformation statementInformation, long timeElapsedNanos, SQLException e) {

    }
}
