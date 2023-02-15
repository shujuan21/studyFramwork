package com.shujuan.studyframework.common.exception;

/**
 * 自定义异常类
 *
 * @Auther: guany
 * @Date: 2023/02/11
 */
public class CustomException extends RuntimeException{

    private String code;

    private Object data;

    //因为Throwable实现了Serializable,使用输出流来进行输出
    private static final long serialVersionUID = 1268647560381331845L;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message); //父类构造器Throwable赋值detailMessage = message
        this.code = "999000";
    }

    public CustomException(String code,String message) {
        super(message);
        this.code = code;
    }

    public CustomException(String code,String message, Object data) {
        this(code,message);
        this.data = data;
    }

    public static String replaceParams(String message, String... params) {
        String[] var2 = params;
        int var3 = params.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String param = var2[var4];
            message = message.replaceFirst("\\{}", param);
        }

        message = message.replace("{}", "");
        return message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

