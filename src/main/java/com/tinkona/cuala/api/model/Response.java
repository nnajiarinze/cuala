package com.tinkona.cuala.api.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
@Component
public class Response<T> implements Serializable {
    private String responseCode;
    private String description;
    private List<T> list;
    private T data;
    private long identityValue;
    private long noOfRecords;

    public Response() {
    }

    public Response(String responseCode, String description, long identityValue) {
        this.responseCode = responseCode;
        this.description = description;
        this.identityValue = identityValue;
    }

    public Response(String responseCode, String description, List<T> list, T data, long identityValue, long noOfRecords) {
        this.responseCode = responseCode;
        this.description = description;
        this.list = list;
        this.data = data;
        this.identityValue = identityValue;
        this.noOfRecords = noOfRecords;
    }

    public Response(String responseCode, String description, List<T> list, T data) {
        this.responseCode = responseCode;
        this.description = description;
        this.list = list;
        this.data = data;
    }

    public Response(List<T> list, T data) {
        this.list = list;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getIdentityValue() {
        return identityValue;
    }

    public void setIdentityValue(long identityValue) {
        this.identityValue = identityValue;
    }

    public long getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(long noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
}
