package com.wicky.chedam.service;

import com.wicky.chedam.core.SqlAnalyzer;
import com.wicky.chedam.dao.MysqlJdbcDao;
import com.wicky.chedam.dao.po.TypedEntity;
import com.wicky.chedam.service.bo.SqlEntity;
import com.wicky.chedam.service.bo.SqlQuery;
import com.wicky.chedam.web.vo.Egg;
import com.wicky.chedam.web.vo.Rope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PullService {

    @Autowired
    private MysqlJdbcDao jdbcDao;

    @Autowired
    private ExportService exportService;

    public List<String> pullRope(Rope root) {
        // TODO recursive to load all the eggs
        // 1. get root Egg
        Egg curr = root.getCurr();
        String ddl = curr.getDdl();

        Rope next = null;
        while((next = root.getNext()) != null){

        }

        // 2. extract Entity from Egg
        SqlEntity entity = extractSqlEntity(ddl);

        // TODO 3. merge extracted entities by tableName

        // 4. get result from datasource
        TypedEntity dbResult = jdbcDao.getDbResult(entity);

        // 5. export result into expected format
        List<String> exportedSqls = exportService.exportAsSql(entity, dbResult);

        return exportedSqls;
    }

    private SqlEntity extractSqlEntity(String ddl) {
        SqlAnalyzer analyzer = new SqlAnalyzer();

        String tableName = analyzer.toTableName(ddl);
        String query = analyzer.toQuery(ddl);
        Map<String, Class<?>> types = analyzer.toTypesMap(ddl);

        SqlEntity entity = new SqlEntity(tableName);
        entity.getQueryList().add(new SqlQuery(query, types));

        return entity;
    }
}
