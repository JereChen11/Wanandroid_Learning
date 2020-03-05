package com.jere.test.article.modle.api;

/**
 * @author jere
 */
public interface GetWebDataListener {
    /**
     * get data from CMS successful
     *
     * @param object 反序列化得到的对象 gson.fromJson()
     */
    void getDataSuccess(Object object);

    /**
     * get data from CMS failed
     *
     * @param failedMsg String type error message
     */
    void getDataFailed(String failedMsg);
}
