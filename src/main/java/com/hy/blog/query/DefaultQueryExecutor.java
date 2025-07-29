package com.hy.blog.query;

import com.hy.blog.common.SqlXmlLoader;
import com.hy.blog.common.QueryRequestDto;
import com.hy.blog.query.QueryExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultQueryExecutor implements QueryExecutor {

    private final JdbcTemplate jdbcTemplate;
    private final SqlXmlLoader sqlXmlLoader;

    @Override
    public Object executeUpdate(QueryRequestDto dto) {
        String sql = sqlXmlLoader.getSqlFromXml( dto.getId(), dto.getVersion(), dto.getParam());
        return jdbcTemplate.update(sql);
    }

    @Override
    public Object executeQuery(QueryRequestDto dto) {
        String sql = sqlXmlLoader.getSqlFromXml( dto.getId(), dto.getVersion(), dto.getParam());
        return jdbcTemplate.queryForList(sql);
    }
}
