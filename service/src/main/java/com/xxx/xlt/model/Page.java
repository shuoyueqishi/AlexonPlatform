package com.xxx.xlt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page implements Serializable {
    private static final long serialVersionUID = 8798896750104833391L;
    private int currentPage;
    private int pageSize;
    private int total;
    private int totalPages;

    public void setCurrentPage(int currentPage) {
        if(currentPage<=0){
            currentPage=1;
        }
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        totalPages = (total%pageSize==0)?total/pageSize:(total/pageSize+1);
        return totalPages;
    }
}
