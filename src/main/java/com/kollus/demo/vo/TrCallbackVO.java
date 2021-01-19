package com.kollus.demo.vo;

public class TrCallbackVO {

    private String content_provider_key; //	string	고객사의 서비스 계정 키 입니다.
    private String filename; //	string	폴더를 포함한 업로드된 파일명입니다.
    private String upload_file_key; //	string	업로드 파일 키
    private String transcoding_result; //	string	트랜스코딩 결과 (success, fail)

    public TrCallbackVO() {}
    public TrCallbackVO(String content_provider_key, String filename, String upload_file_key, String transcoding_result) {
        this.content_provider_key = content_provider_key;
        this.filename = filename;
        this.upload_file_key = upload_file_key;
        this.transcoding_result = transcoding_result;
    }

    public String getContent_provider_key() {
        return content_provider_key;
    }

    public void setContent_provider_key(String content_provider_key) {
        this.content_provider_key = content_provider_key;
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

    public String getTranscoding_result() {
        return transcoding_result;
    }

    public void setTranscoding_result(String transcoding_result) {
        this.transcoding_result = transcoding_result;
    }

    @Override
    public String toString() {
        return "TrCallbackVO{" +
                "content_provider_key='" + content_provider_key + '\'' +
                ", filename='" + filename + '\'' +
                ", upload_file_key='" + upload_file_key + '\'' +
                ", transcoding_result='" + transcoding_result + '\'' +
                '}';
    }
}
