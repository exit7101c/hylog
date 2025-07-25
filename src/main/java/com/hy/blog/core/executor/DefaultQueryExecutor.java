package com.hy.blog.core.executor;

import com.hy.blog.common.loader.SqlXmlLoader;
import com.hy.blog.common.model.QueryRequestDto;
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
