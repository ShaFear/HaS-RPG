package com.example.jereczem.hasrpg.playgame;

/**
 * Created by Michał on 2016-01-07.
 */
public class ArrayData<T> {
    private Integer userID;
    private T data;

    public ArrayData(Integer userID, T data) {
        this.userID = userID;
        this.data = data;
    }

    public Integer getUserID() {
        return userID;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ArrayData{" +
                "\nuserID=" + userID +
                "\n, data=" + data.toString() +
                '}';
    }
}
