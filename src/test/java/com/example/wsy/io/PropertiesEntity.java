package com.example.wsy.io;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class PropertiesEntity {

    /**
     * code
     */
    private String code;

    /**
     * 中文字段
     */
    private String cnValue;

    /**
     * 越南字段
     */
    private String viValue;

    public String getKey(){
        if (!StringUtils.isEmpty(this.code)) {
            return this.code;
        }
        return this.cnValue;
    }

    @Override
    public String toString() {
        return "PropertiesEntity{" +
                "code='" + code + '\'' +
                ", cnValue='" + cnValue + '\'' +
                ", viValue='" + viValue + '\'' +
                '}';
    }
}
