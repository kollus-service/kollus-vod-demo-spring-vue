package com.kollus.demo.eduwillvod.vo;

public class UploadCallbackVO {

    private String content_provider_key; //	string	고객사의 서비스 계정 키 입니다.
    private String full_filename; //	string	폴더를 포함한 업로드된 파일명입니다.
    private String filename;	 // string	업로드 된 파일명입니다.
    private String upload_file_key; //업로드 파일 키

    public UploadCallbackVO(){}
    public UploadCallbackVO(String content_provider_key, String full_filename, String filename, String upload_file_key) {
        this.content_provider_key = content_provider_key;
        this.full_filename = full_filename;
        this.filename = filename;
        this.upload_file_key = upload_file_key;
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

    @Override
    public String toString() {
        return "UploadCallback{" +
                "content_provider_key='" + content_provider_key + '\'' +
                ", full_filename='" + full_filename + '\'' +
                ", filename='" + filename + '\'' +
                ", upload_file_key='" + upload_file_key + '\'' +
                '}';
    }
}
