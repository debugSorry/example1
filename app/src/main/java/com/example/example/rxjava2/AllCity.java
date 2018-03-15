package com.example.example.rxjava2;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/11$ 11:13$
 * <p/>
 */
public class AllCity {
    private String     error_code;
    private String     reason;
    private List<City> result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<City> getResult() {
        return result;
    }

    public void setResult(List<City> result) {
        this.result = result;
    }
}
