package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.LoginInfoEntity;

/**
 * ログイン情報Dto.
 */
@Repository
public class LoginInfoDao {
    /** JdbcTemplate. */
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    /**
     * ログインIDとパスワードがDBの値と一致したらtrue.
     * @param loginId String
     * @param password String
     * @return boolean
     */
    public boolean isLoginSuccess(String loginId, String password){
        String sql = 
                "SELECT login_id"
                + " , password FROM login_info"
                + " WHERE login_id = :loginId and password = :password";
        //loginId、passwordをセット
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("loginId", loginId)
                .addValue("password", password);
        // SQLの実行結果は、自動的にLoginInfoEntityクラスにマッピングされて、Listで取得できる。
        RowMapper<LoginInfoEntity> rowMapper = new BeanPropertyRowMapper<LoginInfoEntity>(LoginInfoEntity.class);

        return !jdbc.query(sql, map, rowMapper).isEmpty();
    }
}
