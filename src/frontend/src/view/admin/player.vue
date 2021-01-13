<template>
  <el-row :gutter="20">
    <el-col :span="3">
      <el-card>

        <div slot="header" class="clearfix">
          <span>채널 목록</span>
        </div>
        <el-link v-for="item in channels" :key="item.key" :underline="false" v-on:click="channelClick(item.key)"
                 style="text-align: left;">
          {{ item.name }}
          <el-tag v-if="item.shared" :key="item.key" type="info" effect="dark" size="mini">
            <i class="el-icon-share"></i></el-tag>
          <el-tag v-if="item.encrypt" :key="item.key" type="info" effect="dark" size="mini"><i class="el-icon-key"></i>
          </el-tag>


        </el-link>
      </el-card>


    </el-col>
    <el-col :span="6">
      <el-table
          :data="mediacontents"
          :default-sort="{prop:'title', order:'descending'}"
          ref="singleTable"
          stripe
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
            label="업로드파일키"
        >
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="mediaContentsLen">
      </el-pagination>
    </el-col>
    <el-col :span="15">
      <b-embed type="iframe" aspect="16by9" :src="playerUrl" allow="autoplay" allowfullscreen
               style="border: none;width: 100%;">
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
              <b-card title="토큰 페이로드">
                <b-card-text>
                  {{ payload }}
                </b-card-text>
              </b-card>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="VG Controller">


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
        <el-tab-pane label="재생정보"></el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</template>

<script>


export default {
  name: "player",
  data() {
    return {
      playerUrl: 'https://v.kr.kollus.com/xuk0ZKrH',
      moveTime: 0,
      payload: {},
      channels: [{
        name: '채널1',
        key: 'abc',
        shared: true,
        encrypt: true
      }],
      selectedChannel: '',
      mediaContentsLen: 0,
      mediacontents: [],
      selectMck: '',
      controller: {},
    }
  },
  methods: {
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
    let ctrl;
    try {
      this.controller = new window.Kollus.VideogatewayController({
        target_window: document.querySelector('iframe').contentWindow
      })
      ctrl = this.controller;
      this.controller.on('play', function () {
        console.log('play');
      });
      this.controller.on('done', function () {
        console.log('done');
        ctrl.play(0)
      });
    } catch (e) {
      console.error(e);
    }

  }
}
</script>

<style scoped>

</style>
