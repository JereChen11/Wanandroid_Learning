package com.jere.test.article.modle.homearticlelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author jere
 */
public class Data {
    @SerializedName("curPage")
    @Expose
    private Integer curPage;
    @SerializedName("datas")
    @Expose
    private List<Datas> datas = null;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("over")
    @Expose
    private Boolean over;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public List<Datas> getDatas() {
        return datas;
    }

    public void setDatas(List<Datas> datas) {
        this.datas = datas;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
