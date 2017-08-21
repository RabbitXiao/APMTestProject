package com.dell.apm.testwebapp.servlet.dto;

import java.io.Serializable;

/**
 * Created by rxiao on 8/8/2017.
 */
public class KontofondsEntityDTO implements Serializable {
    private KontofondsDTO dto;

    public KontofondsEntityDTO(KontofondsDTO dto) {
        this.dto = dto;
    }
}
