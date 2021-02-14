<template>
  <el-row :gutter="20">
    <el-col :span="3">
      <el-card :body-style="{padding: 0}">

        <div slot="header" class="clearfix">
          <span>채널 목록</span>
        </div>
        <el-menu default-active="0" active-text-color="#E2A00B">
          <el-menu-item v-for="(channel, index) in channels" :key="channel.key" :index="index">
            <el-link :underline="false" @click.native="channelClick(channel.key, channel.name)">{{
                channel.name
              }}
            </el-link>
          </el-menu-item>
        </el-menu>

        <!--          <el-tag v-if="item.shared" :key="item.key" type="info" effect="dark" size="mini">-->
        <!--            <i class="el-icon-share"></i></el-tag>-->
        <!--          <el-tag v-if="item.encrypt" :key="item.key" type="info" effect="dark" size="mini"><i class="el-icon-key"></i>-->
        <!--          </el-tag>-->


      </el-card>


    </el-col>
    <el-col :span="6">
      <el-row>
        <el-button-group>
          <el-button icon="el-icon-video-play" v-on:click="playSelected">재생</el-button>
          <el-button icon="el-icon-circle-plus" v-on:click="addPlaylist">재생추가</el-button>
          <el-button icon="el-icon-download" v-on:click="download">다운로드</el-button>
        </el-button-group>
      </el-row>
      <el-row>
        <el-table
            :data="mediacontents"
            :default-sort="{prop:'title', order:'descending'}"
            ref="mediacontentsTable"
            highlight-current-row
            @current-change="playMck"
            border
            @selection-change="selectMcksChange"
            style="width: 100%;">
          <el-table-column
              type="selection"
              width="40"
          >
          </el-table-column>
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
              label="미디어 컨텐츠 키"
          >
          </el-table-column>
        </el-table>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="mediaContentsLen">
        </el-pagination>
      </el-row>
    </el-col>
    <el-col :span="15">
      <b-embed type="iframe" aspect="16by9" :src="playerUrl" allow="autoplay" allowfullscreen
               style="border: none;width: 100%; height: 500px;">
      </b-embed>
      <el-row :gutter="5"></el-row>
      <el-tabs type="border-card">
        <el-tab-pane label="URL 정보">
          <el-row :gutter="5">
            <el-col :span="12">
              <b-card title="플레이어 주소">
                <b-card-text>
                  {{ playerUrl }}
                </b-card-text>
              </b-card>
            </el-col>
            <el-col :span="12">
              <vue-json-editor ref="jsoneditor" v-model="payload" :show-btns="true" :expandedOnStart="false"
                               mode="text" @json-save="payloadChange"></vue-json-editor>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="PlayList">
          <el-table
              :data="playlist"
              ref="playlistTable"
              highlight-current-row
              style="width: 100%;">
            <el-table-column
                label="재생"
            >
              <template scope="scope">
                <el-button icon="el-icon-video-play" @click="playWithPlayList(scope.$index)"></el-button>
              </template>

            </el-table-column>
            <el-table-column
                prop="channel_name"
                label="채널명"
            >
            </el-table-column>
            <el-table-column
                prop="channel_key"
                label="채널키"
            >
            </el-table-column>
            <el-table-column
                prop="title"
                label="컨텐츠명"
                sortable>
            </el-table-column>
            <el-table-column
                prop="media_content_key"
                label="미디어 컨텐츠 키"
            >
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="Query String">
          <el-form label-position="left" label-width="150px">
            <el-button type="primary" @click="confirmQueryString">적용</el-button>
            <el-button type="info" @click="cancelQueryString">초기화</el-button>
            <el-form-item label="프로파일 지정">
              <el-input v-model="querystring.pf" placeholder="프로파일키를 입력해주세요"></el-input>
            </el-form-item>
            <el-form-item label="자동재생">
              <el-switch v-model="querystring.autoplay"
                         active-text="자동재생"
                         inactive-text="수동재생"
              ></el-switch>
            </el-form-item>
            <el-form-item label="음소거">
              <el-switch v-model="querystring.mute"
                         active-text="음소거"
                         inactive-text="소리"
              ></el-switch>
            </el-form-item>
            <el-form-item label="다운로드">
              <el-switch v-model="querystring.download"
                         active-text="다운로드"
                         inactive-text="스트리밍"
              ></el-switch>
            </el-form-item>
            <el-form-item label="영상제목">
              <el-input v-model="querystring.title"></el-input>
            </el-form-item>
            <el-form-item label="시작 시간지정(이어보기 팝업 사용)">
              <el-input-number v-model="querystring.t" min="-1"></el-input-number>
            </el-form-item>
            <el-form-item label="시작 시간지정(이어보기 팝업 미사용)">
              <el-input-number v-model="querystring.s" min="-1"></el-input-number>
            </el-form-item>
            <el-form-item label="데스크탑 전용플레이어로 강제 지정">
              <el-switch v-model="querystring.force_exclusive_player"
                         active-text="지정"
                         inactive-text="미지정"
              ></el-switch>
            </el-form-item>
            <el-collapse>
              <el-collapse-item title="사용자 정의 데이터">
                <el-form-item label="USERVALUE0">
                  <el-input v-model="querystring.uservalue0"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE1">
                  <el-input v-model="querystring.uservalue1"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE2">
                  <el-input v-model="querystring.uservalue2"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE3">
                  <el-input v-model="querystring.uservalue3"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE4">
                  <el-input v-model="querystring.uservalue4"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE5">
                  <el-input v-model="querystring.uservalue5"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE6">
                  <el-input v-model="querystring.uservalue6"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE7">
                  <el-input v-model="querystring.uservalue7"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE8">
                  <el-input v-model="querystring.uservalue8"></el-input>
                </el-form-item>
                <el-form-item label="USERVALUE9">
                  <el-input v-model="querystring.uservalue9"></el-input>
                </el-form-item>
              </el-collapse-item>
            </el-collapse>
            <el-form-item label="밝기 (v2 전용)">
              <el-input-number v-model="querystring.brightness" min="-50" max="50"></el-input-number>
            </el-form-item>
            <el-form-item label="대비 (v2 전용)">
              <el-input-number v-model="querystring.contrast" min="-50" max="50"></el-input-number>
            </el-form-item>
            <el-form-item label="색조 (v2 전용)">
              <el-input-number v-model="querystring.saturation" min="-50" max="50"></el-input-number>
            </el-form-item>
            <el-form-item label="재생중 뒤로가기 버튼 누르거나 재생종료시 이동되는 URL(ios 전용)">
              <el-input v-model="querystring.mobile_return_url"></el-input>
            </el-form-item>
            <el-form-item label="플레이어 버전 지정">
              <el-select v-mode="querystring.pv">
                <el-option value="v2">윈도우즈 전용플레이어(ActiveX)</el-option>
                <el-option value="v3">윈도우즈 전용플레이어(HTML5)</el-option>
                <el-option value="v3e">윈도우즈 전용플레이어(어플리케이션)</el-option>
                <el-option value="html5">비보안 플레이어</el-option>
                <el-option value="v4a">HTML 기반 보안 라이브 플레이어</el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="PC 다운로드 폴더 지정">
              <el-input v-model="querystring.pc_folder_download"></el-input>
            </el-form-item>
            <el-form-item label="모바일 다운로드 폴더 지정">
              <el-input v-model="querystring.mobile_folder_download"></el-input>
            </el-form-item>
            <el-form-item label="PC 다운로드 컨텐츠 재생">
              <el-switch v-model="querystring.play_downloaded_file"
                         active-text="다운로드 컨텐츠 재생"
                         inactive-text="스트리밍"
              ></el-switch>
            </el-form-item>
            <el-form-item label="버퍼 지정 (2~10)">
              <el-input-number v-model="querystring.buffer" min="1" max="10"></el-input-number>
            </el-form-item>

            <el-form-item label="inactive 상태일때 컨트롤을 계속 보여줌 (비보안플레이어)">
              <el-switch v-model="querystring.show_controls_pause"
                         active-text="활성화"
                         inactive-text="비활성화"
              ></el-switch>
            </el-form-item>
            <el-form-item label="재생이 끝난후 포스터 표시(비보안플레이어)">
              <el-switch v-model="querystring.show_poster_ended"
                         active-text="포스터 표시"
                         inactive-text="미표시"
              ></el-switch>
            </el-form-item>

            <el-form-item label="윈도우 보안플레이어 에서 message 항목 사용">
              <el-switch v-model="querystring.loadcheck"
                         active-text="포스터 표시"
                         active-value=0
                         inactive-text="미표시"
                         inactive-value=1
              ></el-switch>
            </el-form-item>

          </el-form>

        </el-tab-pane>
        <el-tab-pane label="VG Controller">
          <el-switch v-model="replay"
                     active-text="반복재생"
                     inactive-text="비적용"
          ></el-switch>
          <el-button-group>
            <el-button plain v-on:click="rw">5초</el-button>
            <el-button plain v-on:click="play">재생</el-button>
            <el-button plain v-on:click="pause">일시정지</el-button>
            <el-button plain v-on:click="ff">5초후</el-button>
          </el-button-group>
          <el-input v-model="moveTime" label="" style="width: 150px;">
            <el-button slot="append" plain v-on:click="play(moveTime)">이동</el-button>
          </el-input>
        </el-tab-pane>
        <el-tab-pane label="재생정보">
          <el-row :gutter="5">
            <el-col :span="12">
              <el-card>
                <div slot="header">
                  <span>VG Controller Event (FrontEnd)</span>
                  <el-timeline>
                    <el-timeline-item
                        v-for="(event, index) in vgEvents"
                        :key="index"
                        :timestamp="event.timestamp"
                        style="width: 200px;"
                    >
                      {{ event.message }}
                    </el-timeline-item>
                  </el-timeline>
                </div>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card>
                <div slot="header">
                  <span>LMS DATA</span>
                </div>
                <el-table
                    :data="lmsdata"
                    :default-sort="{prop:'start_at', order:'descending'}"
                    border
                    style="width: 100%;"
                >
                  <el-table-column
                      prop="start_at"
                      label="시작시간">
                  </el-table-column>
                  <el-table-column
                      prop="device"
                      label="시청 디바이스">
                  </el-table-column>
                  <el-table-column
                      prop="playtime"
                      label="시청시간">
                  </el-table-column>
                  <el-table-column
                      prop="last_play_at"
                      label="마지막 시청 위치">
                  </el-table-column>
                </el-table>
              </el-card>

            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-col>

    <el-dialog
        title="컨텐츠 다운로드"
        :visible.sync="downloadDialogVisible"
        width="30%"
    >
      <el-row>
        <span>아래의 목록을 다운로드 하시겠습니까?</span>
        <el-table
            :data="downloadlist"
            ref="downloadTable"
            style="width: 100%;">
          <el-table-column
              prop="channel_name"
              label="채널명"
          >
          </el-table-column>
          <el-table-column
              prop="channel_key"
              label="채널키"
          >
          </el-table-column>
          <el-table-column
              prop="title"
              label="컨텐츠명"
              sortable>
          </el-table-column>
          <el-table-column
              prop="media_content_key"
              label="미디어 컨텐츠 키"
          >
          </el-table-column>
        </el-table>
      </el-row>
      <span slot="footer">
      <el-button @click="cancelDownload">취소</el-button>
      <el-button @click="confirmDownload" type="primary">댜운로드</el-button>
    </span>
    </el-dialog>

  </el-row>

</template>

<script>


import KollusService from "@/service/kollus.service";
import vueJsonEditor from 'vue-json-editor'

const initQuery = {
  pf: '',
  autoplay: false,
  mute: false,
  download: false,
  title: '',
  t: -1,
  s: -1,
  force_exclusive_player: false,
  pv: '',
  uservalue0: '',
  uservalue1: '',
  uservalue2: '',
  uservalue3: '',
  uservalue4: '',
  uservalue5: '',
  uservalue6: '',
  uservalue7: '',
  uservalue8: '',
  uservalue9: '',
  brightness: 0,
  contrast: 0,
  saturation: 0,
  mobile_return_url: '',
  mobile_folder_download: '',
  pc_folder_download: '',
  play_downloaded_file: true,
  buffer: 1,
  show_controls_pause: false,
  show_poster_ended: false,
  overlay_button_position: null,
  loadcheck: -1

}
export default {
  name: "player",
  components: {
    vueJsonEditor,
  },
  data() {
    return {
      playerUrl: '',
      moveTime: 0,
      replay: false,
      payload: {},
      options: {},
      channels: [],
      selectedChannel: '',
      mediaContentsLen: 0,
      mediacontents: [],
      selectMck: '',
      selectMcks: [],
      currentPlay: 0,
      playlist: [],
      downloadDialogVisible: false,
      downloadlist: [],
      controller: {},
      querystring: initQuery,
      strQueryString: '',
      vgEvents: [],
      lmsdata: [],
    }
  },
  created() {
    KollusService.getChannels().then(response => {
      console.log(response.data);
      this.channels = response.data;
      this.channelClick(response.data[0].key, response.data[0].name);
    });
  },
  methods: {
    channelClick(channel_key, channel_name) {
      this.selectedChannel = {channel_key: channel_key, channel_name: channel_name};
      KollusService.getContentsByChannel(channel_key).then(response => {
        this.mediacontents = response.data
      });
    },
    playMck(row) {
      this.currentPlay = -1;
      this.playlist = [];
      let data = {
        mck: row.media_content_key,
        cuid: JSON.parse(localStorage.getItem('user')).email,
        channel_key: this.selectedChannel.channel_key
      };
      console.log(data);
      KollusService.getPlayUrl(data).then(
          response => {
            this.playerUrl = response.data.url + '&' + this.strQueryString;
            this.payload = JSON.parse(response.data.payload);
            this.initVgController();
          }
      ).catch(error => {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
      KollusService.getLms(row.media_content_key,JSON.parse(localStorage.getItem('user')).email ).then(
          response => {
            this.lmsdata = response.data();
          }
      );
    },
    selectMcksChange(value) {
      console.log('selectMcksChange', value);
      this.selectMcks = value.map(item => {
        return {
          channel_name: this.selectedChannel.channel_name, channel_key: this.selectedChannel.channel_key,
          title: item.title, media_content_key: item.media_content_key
        };
      })
    },
    playWithPlayList(index) {
      console.log("playWithPlayList", index)
      let data = {
        mck: this.playlist[index].media_content_key,
        cuid: JSON.parse(localStorage.getItem('user')).email,
        channel_key: this.selectedChannel.channel_key
      };
      KollusService.getPlayUrl(data).then(
          response => {
            this.playerUrl = response.data.url + '&a' + '&' + this.strQueryString;
            this.payload = JSON.parse(response.data.payload);
            this.currentPlay = index;
            this.initVgController();
          }
      ).catch(error => {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      });
      KollusService.getLms(this.playlist[index].media_content_key,JSON.parse(localStorage.getItem('user')).email ).then(
          response => {
            this.lmsdata = response.data();
          }
      );
    },
    playSelected(evt) {
      this.playlist = this.selectMcks
      this.selectMcks = [];
      this.playWithPlayList(0);
      this.$refs.mediacontentsTable.clearSelection();

    },
    addPlaylist(evt) {
      console.log('addPlaylist', this.selectMcks);
      if (this.selectMcks.length > 0) {
        this.selectMcks.forEach(item => {
          this.playlist.push(item);
        });
      }
      this.selectMcks = [];
      this.$refs.mediacontentsTable.clearSelection();

    },
    download(evt) {
      console.log(this.selectMcks);
      this.downloadlist = this.selectMcks;
      this.downloadDialogVisible = true;
      this.$refs.mediacontentsTable.clearSelection();

    },
    cancelDownload() {
      this.downloadlist = [];
      this.downloadDialogVisible = false;
    },
    confirmDownload() {
      this.downloadDialogVisible = false;
      let downloadReq = [];
      this.downloadlist.forEach(item => {
        downloadReq.push({
          channel_key : item.channel_key,
          cuid: localStorage.getItem("user").email,
          mck: item.media_content_key
        })
      });
      KollusService.download(downloadReq).then(response => {
        window.location = response.data.url
        this.downloadlist = [];
      });
    },
    confirmQueryString() {
      KollusService.getQueryString(this.querystring).then(response => {
        this.strQueryString = response.data.query;
      })
    },
    cancelQueryString() {
      this.querystring = initQuery;
      this.strQueryString = '';
    },
    payloadChange(value) {
      this.currentPlay = -1;
      this.playlist = [];
      KollusService.getPlayUrlByPayload(value).then(response => {
        this.playerUrl = response.data.url + '&' + this.querystring;
        this.payload = JSON.parse(response.data.payload);
        this.initVgController();
      });
    },

    initVgController() {

      let self = this;
      self.controller = {};
      try {
        self.controller = new window.Kollus.VideogatewayController({
          target_window: document.querySelector('iframe').contentWindow
        });
        self.controller.on('loaded', function () {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player Loaded'
          });

        });
        self.controller.on('ready', function () {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player Ready'
          });
          if (self.playlist.length > 0) {
            self.controller.play();
          }
        });
        self.controller.on('play', function () {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player Play (' + self.controller.get_progress().position + ')'
          });

        });
        self.controller.on('pause', function () {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player Pause (' + self.controller.get_progress().position + ')'
          });

        });
        self.controller.on('play', function (is_muted) {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player ' + is_muted ? '음소거' : '소리재생' + ' (' + self.controller.get_progress().position + ')'
          });
        });
        self.controller.on('screenchange', function (screen) {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player ' + screen === 'fullscreen' ? '전체화면' : '일반화면' + ' (' + self.controller.get_progress().position + ')'
          });

        });
        self.controller.on('volumechange', function (volumechange) {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player VolumeChange : ' + volumechange + ' (' + self.controller.get_progress().position + ')'
          });

        });
        self.controller.on('speedchange', function (speedchange) {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player SpeedChange : ' + speedchange + ' (' + self.controller.get_progress().position + ')'
          });
        });
        self.controller.on('jumpstepchange', function (jumpstep) {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player jumpstepchange: ' + jumpstep + ' (' + self.controller.get_progress().position + ')'
          });

        });

        self.controller.on('error', function (error) {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Player Raise Error: ' + error + ' (' + self.controller.get_progress().position + ')'
          });

        });

        self.controller.on('done', function () {
          self.vgEvents.push({
            timestamp: new Date().toISOString().split('T')[1],
            message: 'Play done'
          });

          if (self.replay) {
            self.controller.play(0)
          } else {

            if (self.playlist.length > 0 && self.playlist.length - 1 > self.currentPlay) {
              self.playWithPlayList(self.currentPlay + 1);
            }
          }
        });
      } catch (e) {
        console.error(e);
      }
    },
    play(position) {
      if (position === undefined) {
        this.controller.play(0);
      } else {
        this.controller.play(position);
      }
    },
    pause() {
      this.controller.pause();
    },
    rw() {
      this.controller.rw();
    },
    ff() {
      this.controller.ff();
    }
  },

  mounted() {
    let titleSpan = window.document.createElement('span');
    titleSpan.innerText = 'PAYLOAD';
    titleSpan.style.lineHeight = '35px'
    window.document.querySelector('div.jsoneditor-menu').append(titleSpan);


  }
}
</script>

<style>
div.card-body,
p .card-text {
  word-break: break-all;
}

.el-menu > .el-menu-item.is-active {
  color: whitesmoke;
  background-color: #E2A00B;
}

.el-card > .el-card__body {
  padding: 0;
}

.el-table__row.current-row {
  background-color: #ffdb1f !important;
}

div.jsoneditor-menu {
  background-color: #E2A00B;
}

.jsoneditor-modes {
  display: none;
}
</style>
