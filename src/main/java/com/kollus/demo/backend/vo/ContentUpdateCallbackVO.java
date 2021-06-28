package com.kollus.demo.eduwillvod.vo;

public class ContentUpdateCallbackVO {

    private String content_provider_key;//	string	고객사의 서비스 계정 키 입니다.
    private String full_filename;//	string	폴더를 포함한 업로드 된 파일명입니다.
    private String filename;//	string	업로드 된 파일명입니다.
    private String upload_file_key;//	string	업로드 파일 키
    private String update_type;//	string	업데이트 종류

    public ContentUpdateCallbackVO(){}

    public ContentUpdateCallbackVO(String content_provider_key, String full_filename, String filename, String upload_file_key, String update_type) {
        this.content_provider_key = content_provider_key;
        this.full_filename = full_filename;
        this.filename = filename;
        this.upload_file_key = upload_file_key;
        this.update_type = update_type;
    }

    public String getContent_provider_key() {
        return content_provider_key;
    }

    public void setContent_provider_key(String content_provider_key) {
        this.content_provider_key = content_provider_key;
    }

    public String getFull_filename() {
        return full_filename;
    }

    public void setFull_filename(String full_filename) {
        this.full_filename = full_filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUpload_file_key() {
        return upload_file_key;
    }

    public void setUpload_file_key(String upload_file_key) {
        this.upload_file_key = upload_file_key;
    }

    public String getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(String update_type) {
        this.update_type = update_type;
    }

    @Override
    public String toString() {
        return "ContentUpdateCallbackVO{" +
                "content_provider_key='" + content_provider_key + '\'' +
                ", full_filename='" + full_filename + '\'' +
                ", filename='" + filename + '\'' +
                ", upload_file_key='" + upload_file_key + '\'' +
                ", update_type='" + update_type + '\'' +
                '}';
    }
}
