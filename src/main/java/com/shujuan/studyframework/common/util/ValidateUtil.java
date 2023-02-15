package com.shujuan.studyframework.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 参数校验
 *
 * @author tz
 * @create 2021-07-27
 */
public class ValidateUtil {
    
    private ValidateUtil() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * 参数校验
     *
     * @param o      参数
     * @param groups 参数组
     */
    public static void validate(Object o, Class<?>... groups) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(o, groups);
        for (ConstraintViolation<Object> constraintViolation : set) {
            if (constraintViolation != null) {
//                throw new CustomException(constraintViolation.getMessage());
            }
        }
    }
}
