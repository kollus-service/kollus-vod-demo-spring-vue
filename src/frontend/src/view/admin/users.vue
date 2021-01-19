<template>
  <el-row :gutter="20">
    <el-col :span="3">
      <el-card>

        <div slot="header" class="clearfix">
          <span>사용자</span>
        </div>
        <el-menu default-active="0" active-text-color="#E2A00B">
          <el-menu-item v-for="(u, index) in users" :key="u.id" :index="index">
            <el-link :underline="false" @click.native="userClick(u.email)">{{ u.username }}</el-link>
          </el-menu-item>
        </el-menu>
      </el-card>
      <el-form label-position="left" label-width="80px">
        <el-form-item label="ID">
          <el-input v-model="user.id"></el-input>
        </el-form-item>
        <el-form-item label="EMAIL">
          <el-input v-model="user.email"></el-input>
        </el-form-item>
        <el-form-item label="Password">
          <el-input v-model="user.password"></el-input>
        </el-form-item>
        <el-button type="primary" @click="addUser">추가</el-button>
        <el-button type="info" @click="initUser">취소</el-button>

      </el-form>
    </el-col>
    <el-col :span="3">
      <el-card>

        <div slot="header" class="clearfix">
          <span>채널 목록</span>
        </div>
        <el-menu default-active="0" active-text-color="#E2A00B">
          <el-menu-item v-for="(channel, index) in channels" :key="channel.key" :index="index">
            <el-link :underline="false" @click.native="channelClick(channel.key)">{{ channel.name }}</el-link>
          </el-menu-item>
        </el-menu>

        <!--          <el-tag v-if="item.shared" :key="item.key" type="info" effect="dark" size="mini">-->
        <!--            <i class="el-icon-share"></i></el-tag>-->
        <!--          <el-tag v-if="item.encrypt" :key="item.key" type="info" effect="dark" size="mini"><i class="el-icon-key"></i>-->
        <!--          </el-tag>-->


      </el-card>


    </el-col>
    <el-col :span="6">
      <el-table
          :data="mediacontents"
          :default-sort="{prop:'title', order:'descending'}"
          ref="singleTable"
          highlight-current-row
          @current-change="selectMck"
          border
          style="width: 100%;">
        <el-table-column
            prop="poster_url"
            label="대표이미지">
          <template slot-scope="scope">
            <el-image :src="scope.row.poster_url" style="width: 100%; height: 100%;"
                      fit="cover"/>
          </template>
        </el-table-column>
        <el-table-column
            prop="title"
            label="컨텐츠명"
            sortable>
        </el-table-column>
        <el-table-column
            prop="media_content_key"
            label="미디어컨텐츠키"
        >
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="mediaContentsLen">
      </el-pagination>
    </el-col>
    <el-col :span="12">
      <el-row :gutter="10">
        <el-col :span="8">
          <el-col :span="8">
            <span>사용자</span>
            <el-tag type="info" v-if="selectedUser !=='' " :closable="false">{{ selectedUser }}</el-tag>
          </el-col>
          <span>채널키</span>
          <el-tag type="info" v-if="selectedChannel !=='' " :closable="false">{{ selectedChannel }}</el-tag>
        </el-col>
        <el-col :span="8">
          <span>미디어컨텐츠키</span>
          <el-tag type="info" v-if="selectedMck !=='' " :closable="false">{{ selectedMck }}</el-tag>
        </el-col>
      </el-row>
      <el-tabs type="border-card">
        <el-tab-pane label="Jwt 토큰">
          <el-form ref="jwtForm" label-position="left" label-width="100px">
            <el-form-item label="만료시간">
              <el-input-number v-model="jwt.expt" :step="60" :min="0" :max="3600"
                               label="만료시간" style="width: 100%;"></el-input-number>
            </el-form-item>

            <el-form-item label="인트로여부">
              <el-switch v-model="jwt.intro"
                         active-text="적용"
                         inactive-text="비적용"
              ></el-switch>
            </el-form-item>
            <el-form-item v-if="jwt.intro" label="인트로 미디어컨텐츠키">
              <el-input v-model="jwt.intro_mck" style="width: 100%;" :disabled="jwt.intro"></el-input>
            </el-form-item>
            <el-form-item label="아웃트로여부">
              <el-switch v-model="jwt.outro"
                         active-text="적용"
                         inactive-text="비적용"

              ></el-switch>
            </el-form-item>
            <el-form-item v-if="jwt.outro" label="아웃트로 미디어컨텐츠키">
              <el-input v-model="jwt.outro_mck" style="width: 100%;" :disabled="jwt.outro"></el-input>
            </el-form-item>
            <el-form-item label="스크롤 이벤트">
              <el-switch v-model="jwt.scroll_event"
                         active-text="적용"
                         inactive-text="비적용"
              ></el-switch>
            </el-form-item>
            <el-form-item label="Seek 활성화">
              <el-switch v-model="jwt.seek"
                         active-text="적용"
                         inactive-text="비적용"

              ></el-switch>
            </el-form-item>
            <el-form-item v-if="!jwt.seek" label="Seek 허용 구간">
              <el-input-number v-model="jwt.seekable_end" :step="1" :min="-1"
                               style="width: 100%;" :disabled="jwt.seek"></el-input-number>
            </el-form-item>
            <el-form-item label="배속 비활성화">
              <el-switch v-model="jwt.disable_playrate"
                         active-text="적용"
                         inactive-text="비적용"

              ></el-switch>
            </el-form-item>
            <el-form-item label="이어보기 비활성화">
              <el-switch v-model="jwt.disable_nscreen"
                         active-text="적용"
                         inactive-text="비적용"

              ></el-switch>
            </el-form-item>
            <el-form-item label="구간반복 지정(영상 잘라내기)">
              <el-input-number v-model="jwt.start_time" :step="1" :min="-1"
                               style="width: 50%;"></el-input-number>
              <el-input-number v-model="jwt.end_time" :step="1" :min="-1"
                               style="width: 50%;"></el-input-number>
            </el-form-item>

            <el-form-item label="비디오 워터마킹 활성화">
              <el-switch v-model="jwt.enable_video_water_marking"
                         active-text="적용"
                         inactive-text="비적용"

              ></el-switch>
            </el-form-item>

            <el-form-item label="비디오 워터마크 코드 종류">
              <el-input v-model="jwt.code_kind" style="width: 100%;"></el-input>
            </el-form-item>
            <el-form-item label="비디오 워터마크 투명도">
              <el-input-number v-model="jwt.alpha" :step="1" :min="0" :max="255"
                               style="width: 50%;"></el-input-number>
            </el-form-item>
            <el-form-item label="비디오 워터마크 폰트 크기">
              <el-input-number v-model="jwt.font_size" :step="1" :min="1" :max="255"
                               style="width: 50%;">
              </el-input-number>
            </el-form-item>
            <el-form-item label="비디오 워터마크 폰트 색">
              <el-color-picker v-model="jwt.font_color"></el-color-picker>
            </el-form-item>
            <el-form-item label="비디오 워터마크 숨김/표시 시간">
              <el-input-number v-model="jwt.show_time" :step="1" :min="-1"
                               style="width: 50%;"></el-input-number>
              <el-input-number v-model="jwt.hide_time" :step="1" :min="-1"
                               style="width: 50%;"></el-input-number>
            </el-form-item>
            <el-form-item label="비디오 워터마크 HTML5플레이어 적용여부">
              <el-switch v-model="jwt.enable_html5_player"
                         active-text="적용"
                         inactive-text="비적용"

              ></el-switch>
            </el-form-item>
            <el-button type="primary" v-on:click="jwtOk">저장</el-button>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="Play Callback">
          <el-form ref="playcallbackform" label-position="left" label-width="200px">
            <el-form-item label="재생가능">
              <el-switch v-model="playcallback.result"
                         active-text="가능"
                         active-value="1"
                         inactive-text="불가"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item v-if="playcallback.result == 0" label="재생 불가시 메시지">
              <el-input v-model="playcallback.message" style="width: 100%;"></el-input>
            </el-form-item>
            <el-form-item label="재생 만료 시간">
              <el-date-picker
                  v-model="playcallback.expiration_date"
                  type="datetime"
                  value-format="timestamp"
                  placeholder="만료되는 시간을 지정해주세요" style="width: 80%;">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="재생 허용 시간">
              <el-input-number v-model="playcallback.expiration_playtime" :step="1" :min="0"
                               style="width: 100%;"></el-input-number>
            </el-form-item>
            <el-form-item label="가상머신 사용 불가">
              <el-switch v-model="playcallback.vmcheck"
                         active-text="불가"
                         active-value="1"
                         inactive-text="허용"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="재생 가능 구간 지정">
              <el-input-number v-model="playcallback.start_time" :step="1" :min="-1"
                               style="width: 50%;"></el-input-number>
              <el-input-number v-model="playcallback.end_time" :step="1" :min="-1"
                               style="width: 50%;"></el-input-number>
            </el-form-item>
            <el-form-item label="TV OUT 불가">
              <el-switch v-model="playcallback.disable_tvout"
                         active-text="불가"
                         active-value="1"
                         inactive-text="허용"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="재생 만료처리">
              <el-switch v-model="playcallback.content_expired"
                         active-text="만료"
                         active-value="1"
                         inactive-text="허용"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-button type="primary" v-on:click="playCallbackOk">저장</el-button>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="DRM Callback">
          <el-form ref="drmcallbackform" label-position="left" label-width="200px">
            <el-form-item label="다운로드가능">
              <el-switch v-model="drmcallback.result"
                         active-text="가능"
                         active-value="1"
                         inactive-text="불가"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item v-if="drmcallback.result == 0" label="다운로드 불가시 메시지">
              <el-input v-model="drmcallback.message" style="width: 100%;"></el-input>
            </el-form-item>
            <el-form-item label="다운로드 컨텐츠 만료 시간">
              <el-date-picker
                  v-model="drmcallback.expiration_date"
                  type="datetime"
                  value-format="timestamp"
                  placeholder="만료되는 시간을 지정해주세요" style="width: 80%;">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="다운로드 컨텐츠 재생 허용 횟수">
              <el-input-number v-model="drmcallback.expiration_count" :step="1" :min="0"
                               style="width: 100%;"></el-input-number>
            </el-form-item>
            <el-form-item label="다운로드 컨텐츠 재생 허용 시간">
              <el-input-number v-model="drmcallback.expiration_playtime" :step="1" :min="0"
                               style="width: 100%;"></el-input-number>
            </el-form-item>
            <el-form-item label="다운로드 컨텐츠 재생 허용 시간 산출 방식">
              <el-switch v-model="drmcallback.vmcheck"
                         active-text="재생시간"
                         active-value="1"
                         inactive-text="전체"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>

            <el-form-item label="다운로드 컨텐츠 만료시 갱신 팝업 표시">
              <el-switch v-model="drmcallback.vmcheck"
                         active-text="표시"
                         active-value="1"
                         inactive-text="미표시"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>

            <el-form-item label="다운로드 컨텐츠 재생시 마다 재생 권한 확인">
              <el-switch v-model="drmcallback.check_abuse"
                         active-text="확인"
                         active-value="1"
                         inactive-text="미확인"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>

            <el-form-item label="오프라인시 북마크 다운로드 사용여부">
              <el-switch v-model="drmcallback.bookmark_download"
                         active-text="인덱스 북마크만 다운로드"
                         active-value="1"
                         inactive-text="안함"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="오프라인시 북마크 추가 삭제 사용여부">
              <el-switch v-model="drmcallback.bookmark_readonly"
                         active-text="사용"
                         active-value="1"
                         inactive-text="안함"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>

            <el-form-item label="다운로드 컨텐츠 삭제">
              <el-switch v-model="drmcallback.content_delete"
                         active-text="삭제"
                         active-value="1"
                         inactive-text="안함"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="다운로드 컨텐츠 만료 체크 유효 시간">
              <el-date-picker
                  v-model="drmcallback.check_expiration_date"
                  type="datetime"
                  value-format="timestamp"
                  placeholder="만료되는 시간을 지정해주세요" style="width: 80%;">
              </el-date-picker>
            </el-form-item>

            <el-form-item label="가상머신 사용 불가">
              <el-switch v-model="drmcallback.vmcheck"
                         active-text="불가"
                         active-value="1"
                         inactive-text="허용"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="재생 가능 구간 지정">
              <el-input-number v-model="drmcallback.start_time" :step="1" :min="-1"
                               style="width: 50%;"></el-input-number>
              <el-input-number v-model="drmcallback.end_time" :step="1" :min="-1"
                               style="width: 50%;"></el-input-number>
            </el-form-item>
            <el-form-item label="TV OUT 불가">
              <el-switch v-model="drmcallback.disable_tvout"
                         active-text="불가"
                         active-value="1"
                         inactive-text="허용"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="컨텐츠 만료처리">
              <el-switch v-model="drmcallback.content_expired"
                         active-text="만료"
                         active-value="1"
                         inactive-text="허용"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-form-item label="만료된 컨테츠 권한 초기화">
              <el-switch v-model="drmcallback.content_expire_reset"
                         active-text="초기화"
                         active-value="1"
                         inactive-text="안힘"
                         inactive-value="0"
              ></el-switch>
            </el-form-item>
            <el-button type="primary" v-on:click="drmCallbackOk">저장</el-button>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</template>

<script>
import KollusService from '../../service/kollus.service';

export default {
  name: "users",
  data() {
    return {
      user: {},
      users: [],
      selectedUser: '',
      channels: [],
      selectedChannel: '',
      selectedMck: '',
      mediaContentsLen: 0,
      mediacontents: [],
      jwt: {
        expt: 300,
        intro: false,
        intro_mck: '',
        outro: false,
        outro_mck: '',
        scroll_event: false,
        seek: true,
        seekable_end: -1,
        disable_playrate: false,
        disable_nscreen: false,
        start_time: -1,
        end_time: -1,
        enable_video_water_marking: false,
        code_kind: 'client_user_id',
        alpha: 200,
        font_size: 7,
        font_color: '#FFFFFF',
        show_time: 1,
        hide_time: 60,
        enable_html5_player: false
      },
      playcallback: {
        expiration_date: 0,
        result: 1,
        message: '',
        vmcheck: 0,
        start_time: 0,
        end_time: 0,
        disable_tvout: 0,
        expiration_playtime: 0,
        content_expired: 0
      },
      drmcallback: {
        expiration_date: 0,
        expiration_count: 0,
        expiration_playtime: 0,
        expiration_playtime_type: 0,
        result: 1,
        message: '',
        expiration_refresh_popup: 0,
        vmcheck: 0,
        check_abuse: 0,
        bookmark_download: 0,
        bookmark_readonly: 0,
        content_delete: 0,
        check_expiration_date: 0,
        content_expired: 0,
        content_expire_reset: 0
      }

    }
  },
  created() {
    KollusService.getUsers().then(response => {
      console.log("getuser", response.data);
      this.users = response.data;
      this.userClick(response.data[0].email);
    });
    KollusService.getChannels().then(response => {
      this.channels = response.data
      this.channelClick(response.data[0].key);
    });
  },

  methods: {
    initUser() {
      this.user = {
        username: undefined,
        password: undefined,
        email: undefined
      }
    },
    addUser() {
      KollusService.addUser(this.user).then(response => {
        this.users.push(response.data);
      });
    },
    userClick(email) {
      this.selectedUser = email;
      this.selectedChannel = '';
      this.selectedMck = '';
      this.mediacontents = [];

      KollusService.getPolicyWithCuid(email).then(
          response => {
            console.log(response.data)
            this.jwt = response.data.jwt;
            this.playcallback = response.data.playcallback;
            this.drmcallback = response.data.drmcallback;
            delete this.jwt.channel_key;
            delete this.jwt.media_content_key;
            delete this.playcallback.channel_key;
            delete this.playcallback.media_content_key;
            delete this.drmcallback.channel_key;
            delete this.drmcallback.media_content_key;
          }
      );

    },
    channelClick(channel_key) {
      this.selectedChannel = channel_key;
      this.selectedMck = '';
      KollusService.getContentsByChannel(this.selectedChannel).then(response => {
        this.mediacontents = response.data
      });
      KollusService.getPolicyWithChannelAndCuid(this.selectedChannel, this.selectedUser).then(response => {
        this.jwt = response.data.jwt;
        this.playcallback = response.data.playcallback;
        this.drmcallback = response.data.drmcallback;
      });
    },
    selectMck(row) {
      this.selectedMck = row.media_content_key;
      KollusService.getPolicyWithMckAndCuid(this.selectedChannel, this.selectedMck, this.selectedUser).then(
          response => {
            this.jwt = response.data.jwt;
            this.playcallback = response.data.playcallback;
            this.drmcallback = response.data.drmcallback;
          }
      );
    },
    jwtOk() {
      if (this.selectedChannel === '' && this.selectedMck === '') {
        KollusService.savePolicyWithCuid('jwt', this.jwt, this.selectedUser)
      } else if (this.selectedChannel !== '' && this.selectedMck === '') {
        KollusService.savePolicyWithChannelAndCuid('jwt', this.jwt, this.selectedChannel, this.selectedUser);
      } else {
        KollusService.savePolicyWithMckAndCuid('jwt', this.jwt, this.selectedChannel, this.selectedMck, this.selectedUser);
      }
    },
    playCallbackOk() {
      if (this.selectedChannel === '' && this.selectedMck === '') {
        KollusService.savePolicyWithCuid('playcallback', this.playcallback, this.selectedUser)
      } else if (this.selectedChannel !== '' && this.selectedMck === '') {
        KollusService.savePolicyWithChannelAndCuid('playcallback', this.playcallback, this.selectedChannel, this.selectedUser);
      } else {
        KollusService.savePolicyWithMckAndCuid('playcallback', this.jwt, this.selectedChannel, this.selectedMck, this.selectedUser);
      }
    },
    drmCallbackOk() {
      if (this.selectedChannel === '' && this.selectedMck === '') {
        KollusService.savePolicyWithCuid('drmcallback', this.drmcallback, this.selectedUser)
      } else if (this.selectedChannel !== '' && this.selectedMck === '') {
        KollusService.savePolicyWithChannelAndCuid('drmcallback', this.drmcallback, this.selectedChannel, this.selectedUser);
      } else {
        KollusService.savePolicyWithMckAndCuid('drmcallback', this.drmcallback, this.selectedChannel, this.selectedMck, this.selectedUser);
      }
    },

  }
}
</script>

<style scoped>

</style>
