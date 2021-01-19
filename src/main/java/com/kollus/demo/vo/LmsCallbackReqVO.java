package com.kollus.demo.vo;

import java.util.HashMap;

public class LmsCallbackReqVO {

    private UserInfoVO user_info;
    private ContentInfoVO content_info;
    private BlockInfoVO block_info;
    private HashMap<String, Object> uservalues;

    public LmsCallbackReqVO(){}

    public LmsCallbackReqVO(UserInfoVO user_info, ContentInfoVO content_info, BlockInfoVO block_info, HashMap<String, Object> uservalues) {
        this.user_info = user_info;
        this.content_info = content_info;
        this.block_info = block_info;
        this.uservalues = uservalues;
    }

    public UserInfoVO getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoVO user_info) {
        this.user_info = user_info;
    }

    public ContentInfoVO getContent_info() {
        return content_info;
    }

    public void setContent_info(ContentInfoVO content_info) {
        this.content_info = content_info;
    }

    public BlockInfoVO getBlock_info() {
        return block_info;
    }

    public void setBlock_info(BlockInfoVO block_info) {
        this.block_info = block_info;
    }

    public HashMap<String, Object> getUservalues() {
        return uservalues;
    }

    public void setUservalues(HashMap<String, Object> uservalues) {
        this.uservalues = uservalues;
    }

    @Override
    public String toString() {
        return "LmsCallbackReqVO{" +
                "user_info=" + user_info +
                ", content_info=" + content_info +
                ", block_info=" + block_info +
                ", uservalues=" + uservalues +
                '}';
    }
}
