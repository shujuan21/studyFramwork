package com.shujuan.studyframework.common.result;

/**
 * 异常枚举类
 *
 * @Auther: guany
 * @Date: 2023/02/11
 */
public enum ExceptionEnum {
    INTERNAL_SERVER_ERROR("5000","服务器内部错误"),
    SUCCESS("000000", "操作成功"),
    FAILED("999999", "操作失败"),
    LoginNameOrPassWordError("100000", "用户名或者密码错误！"),
    EmailUsed("100001", "该邮箱已被注册"),
    UserNameUsed("100002", "该登录名称已存在"),
    EmailNotRegister("100003", "该邮箱地址未注册"),
    LinkOutdated("100004", "该链接已过期，请重新请求"),
    PassWordError("100005", "密码输入错误"),
    UserNameLengthLimit("100006", "用户名长度超限"),
    LoginNameNotExists("100007", "该用户未注册"),
    UserNameSame("100008", "新用户名与原用户名一致"),
    CollectExist("100009", "该文章已被收藏"),
    FileEmpty("100010", "上传文件为空"),
    LimitPictureSize("100011", "图片大小必须小于2M"),
    LimitPictureType("100012", "图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'"),
    UserNotExist("100013", "用户不存在"),
    ParamError("300000", "参数错误: {}"),
    ParamEmpty("300001", "参数为空: {}"),
    DateFormatCanNotParse("300002", "日期格式无法解析"),
    DataError("400000", "数据错误: {}");

    private String code;

    private String msg;

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

