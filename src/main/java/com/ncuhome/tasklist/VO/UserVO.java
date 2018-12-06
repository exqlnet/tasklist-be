package com.ncuhome.tasklist.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ncuhome.tasklist.dataobject.User;
import lombok.Data;

@Data
public class UserVO {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("description")
    private String description;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    public UserVO(User user){
        userId = user.getUserId();
        email = user.getEmail();
        description = user.getDescription();
        avatarUrl = user.getAvatarPicName();
    }

}
