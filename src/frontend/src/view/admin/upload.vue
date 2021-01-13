<template>
  <div>
    <el-row>

      <el-steps :active="active" :space="300" process-status="process" finish-status="success" align-center>
        <el-step title="Step 1" description="Upload URL"></el-step>
        <el-step title="Step 2" description="Upload"></el-step>
        <el-step title="Step 3" description="Watch Upload Progress"></el-step>
      </el-steps>

    </el-row>
    <el-row>
      <el-collapse v-model="activeName" accordion>
        <el-collapse-item title="업로드 URL 획득" name="1">
          <el-form ref="form" label-position="left" label-width="150px">
            <el-form-item label="카테고리">
              <el-select v-model="uploadReq.category_key" filterable placeholder="Category" style="width: 100%;">
                <el-option v-for="item in categories" :key="item.key" :label="item.name"
                           :value="item.key"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="만료시간">
              <el-input-number v-model="uploadReq.expire_time" :step="60" :min="0" :max="3600"
                               label="만료시간" style="width: 100%;"></el-input-number>
            </el-form-item>
            <el-form-item label="암호화여부">
              <el-switch v-model="uploadReq.is_encryption_upload"
                         active-text="암호화"
                         active-value="1"
                         inactive-text="비암호화"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="오디오여부">
              <el-switch v-model="uploadReq.is_audio_upload"
                         active-text="오디오"
                         active-value="1"
                         inactive-text="비디오"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="패스쓰루적용여부">
              <el-switch v-model="uploadReq.is_passthrough"
                         active-text="패스쓰루"
                         active-value="1"
                         inactive-text="인코딩"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="프로파일키">
              <el-select v-model="uploadReq.profile_key" filterable placeholder="Category" style="width: 100%;"
                         :disabled="!uploadReq.is_passthrough">
                <el-option v-for="item in profiles" :key="item.key" :label="item.name"
                           :value="item.key"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="컨텐츠명">
              <el-input v-model="uploadReq.title" label="" style="width: 100%;"></el-input>
            </el-form-item>
            <el-button type="primary" native-type="button" block :loading="isLoading" @click.native="getUploadUrl">다음
            </el-button>
          </el-form>
        </el-collapse-item>
        <el-collapse-item title="컨텐츠 선택 및 업로드 시작" name="2">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-upload
                  class="upload-demo"
                  ref="upload"
                  drag
                  :action="uploadUrl"
                  :auto-upload="false"
                  :file-list="fileList"
              >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">영상파일을 올려 놓으시거나 <em>여기를 클릭해주세요</em></div>
              </el-upload>
              <el-button @click.native="upload" style="width: 100%;" type="warn">업로드</el-button>
              <el-progress style="width: 100%;" :percentage="progress"></el-progress>
            </el-col>
            <el-col :span="16">
              <el-form ref="form" label-position="left" label-width="150px">
                <el-form-item label="리다렉트 URL">
                  <el-input v-model="afterUpload.return_url" placeholder="RedirectUrl"></el-input>
                </el-form-item>
                <el-form-item label="업로드 완료후 알럿 띄우기">
                  <el-switch v-model="afterUpload.disable_alert"
                             active-text="YES"
                             active-value="0"
                             inactive-text="NO"
                             inactive-value="1"
                  ></el-switch>
                </el-form-item>
                <el-form-item label="리다이렉트 스코프">
                  <el-select v-model="afterUpload.redirection_scope" filterable placeholder="Redirection Scope" style="width: 100%;">
                    <el-option value="outer">Outer</el-option>
                    <el-option value="inner">Inner</el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="Accept Type">
                  <el-input v-model="afterUpload.accept" placeholder="accept"></el-input>
                </el-form-item>
              </el-form>
            </el-col>
          </el-row>

        </el-collapse-item>
        <el-collapse-item title="업로드 상태 확인" name="3">

        </el-collapse-item>
      </el-collapse>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "upload",
  data() {
    return {
      isLoading: false,
      active: 0,
      activeName: '1',
      uploadReq: {
        category_key: '',
        expire_time: 300,
        is_encryption_upload: 0,
        is_audio_upload: 0,
        is_passthrough: 0,
        profile_key: ''
      },
      afterUpload:{
        return_url:'',
        disable_alert:1,
        direction_scope:'',
        accept:''
      },
      uploadUrl: '',
      uploadProgres: '',
      progress: 0,
      categories: [],
      profiles: [],
      fileList:[]
    }
  },
  methods: {
    getUploadUrl(){
      //todo 다음 스텝
      this.isLoading = true;
      this.active += 1;
      this.activeName = (this.active + 1).toString();
      this.isLoading = false;
    },
    upload(){

    }
  }
}
</script>

<style scoped>

</style>
