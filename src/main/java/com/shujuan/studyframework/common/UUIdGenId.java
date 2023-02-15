package com.shujuan.studyframework.common;

import tk.mybatis.mapper.genid.GenId;

/**
 * @Auther: guany
 * @Date: 2023/02/12
 */
public class UUIdGenId implements GenId<String> {
    public UUIdGenId() {
    }

    @Override
    public String genId(String table, String column) {
        return IdGenerator.getNextId();
    }
}
