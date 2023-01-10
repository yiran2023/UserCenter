package com.yiran.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * user register request body
 *
 * @author Yiran
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3861840861768595133L;

    private String userAccount;
    private String userPassward;
    private String checkPassward;
}
