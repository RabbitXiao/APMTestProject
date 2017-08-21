package com.dell.apm.testwebapp.servlet.dto;

import java.util.ArrayList;
import java.util.List;


public class VertragEntityDTO{
    private List<KontofondsEntityDTO> kontoFonds;

    public VertragEntityDTO() {
        init();
    }

    public VertragEntityDTO(VertragDTO vertrag) {
        init();
        KontofondsDTO dto1 = new KontofondsDTO();
        KontofondsDTO dto2 = new KontofondsDTO();

        List<KontofondsEntityDTO> kfeList = new ArrayList<KontofondsEntityDTO>();
        kfeList.add(new KontofondsEntityDTO(dto1));
        kfeList.add(new KontofondsEntityDTO(dto2));
        kontoFonds = kfeList;
    }

    VertragEntityDTO(VertragEntityDTO vertrag) {
        init();
    }

    private void init() {
        System.out.println("VertragEntityDTO inited");
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("kontoFonds:" + kontoFonds);
        return sb.toString();
    }
}
