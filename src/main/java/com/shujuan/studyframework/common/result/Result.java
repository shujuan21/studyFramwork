package com.shujuan.studyframework.common.result;

/**
 * 统一结果封装
 *
 * @Auther: guany
 * @Date: 2023/02/11
 */
public class Result {

    private String code;

    private String msg;

    private Object data;

    /*
       操作成功
        */
    public static Result success(){
        return new Result(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMsg());
    }

    public static Result success(Object data){
        return new Result(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMsg(),data);
    }

    /*
    操作失败
     */
    public static Result error(String code,String msg){
        return new Result(code,msg);
    }



    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
