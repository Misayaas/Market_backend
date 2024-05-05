package com.seecoder.BlueWhale.exception;

/**
 * @Author: DingXiaoyu
 * @Date: 0:26 2023/11/26
 * 你可以在这里自定义Exception
 */
public class BlueWhaleException extends RuntimeException {

    public BlueWhaleException(String message) {
        super(message);
    }

    public static BlueWhaleException phoneAlreadyExists() {
        return new BlueWhaleException("手机号已经存在!");
    }

    public static BlueWhaleException storeAlreadyExists() {
        return new BlueWhaleException("店铺已经存在!");
    }

    public static BlueWhaleException notLogin() {
        return new BlueWhaleException("未登录!");
    }

    public static BlueWhaleException phoneOrPasswordError() {
        return new BlueWhaleException("手机号或密码错误!");
    }

    public static BlueWhaleException fileUploadFail() {
        return new BlueWhaleException("文件上传失败!");
    }

    public static BlueWhaleException fileDeleteFail() {
        return new BlueWhaleException("文件删除失败!");
    }

    public static BlueWhaleException forbidden() {
        return new BlueWhaleException("权限不足!");
    }
}
