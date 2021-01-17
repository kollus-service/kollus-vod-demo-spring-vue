<template>
  <el-row :gutter="20">
    <el-col :span="4">
      <el-menu default-active="0" active-text-color="#E2A00B">
        <el-menu-item v-for="(category, index) in categories" :key="category.key" :index="index">
          <el-link :underline="false" @click.native="viewContents(category.key)">{{category.name}}</el-link>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="14">
      <el-table
          :data="mediacontents"
          :default-sort="{prop:'title', order:'descending'}"
          ref="singleTable"
          highlight-current-row
          @current-change="handleCurrentChange"
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
            prop="upload_file_key"
            label="업로드파일키"
        >
        </el-table-column>
        <el-table-column
            prop="use_encryption"
            label="암호화">
          <template slot-scope="scope">
            <i v-if="scope.row.use_encryption" class="el-icon-lock"></i>
            <i v-else class="el-icon-unlock"></i>
          </template>
        </el-table-column>
        <el-table-column
            prop="is_audio"
            label="오디오">
          <template slot-scope="scope">
            <i v-if="scope.row.is_audio" class="el-icon-headset"></i>
            <i v-else class="el-icon-video-play"></i>
          </template>
        </el-table-column>
        <el-table-column
            prop="duration"
            label="길이">
<!--            :formatter="durationFormat">-->
        </el-table-column>
        <el-table-column
            prop="uploaded_at"
            label="업로드일자"
            sortable
            :formatter="dateFormat">
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="mediaContentLen">
      </el-pagination>
    </el-col>
    <el-col :span="6">
      <el-card>
        <div slot="header" class="clearfix">
          <span>Profiles</span>
        </div>
        <el-table
            :data="profiles">
          <el-table-column prop="name">
          </el-table-column>
          <el-table-column prop="key">
          </el-table-column>
          <el-table-column prop="size">
          </el-table-column>
        </el-table>
      </el-card>
<!--      <el-card>-->
<!--        <div slot="header" class="clearfix">-->
<!--          <span>Poster Upload</span>-->
<!--        </div>-->
<!--        <el-upload></el-upload>-->
<!--      </el-card>-->
<!--      <el-card>-->
<!--        <div slot="header" class="clearfix">-->
<!--          <span>Subtitle Upload</span>-->
<!--        </div>-->
<!--        <el-upload></el-upload>-->
<!--      </el-card>-->
    </el-col>
  </el-row>
</template>

<script>
import KollusService from '../../service/kollus.service';
export default {
  name: "categories",
  data() {
    return {

      categories: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      mediaContentsLen: 0,
      mediacontents: [
        {
          poster_url: '',
          title: '',
          upload_file_key: '',
          use_encryption: false,
          is_audio: false,
          duration: 0,
          uploaded_at: 0
        }
      ],
      profiles: [
        {
          name: '',
          key: '',
          size: 0
        }
      ]
    }
  },
  created() {
    KollusService.getCategories().then(response => {
      this.categories = response.data;
      this.viewContents(response.data[0].key);
    });
  },
  methods: {
    leadingZeros(n, digits) {
      let zero = '';
      n = n.toString();

      if (n.length < digits) {
        for (let i = 0; i < digits - n.length; i++)
          zero += '0';
      }
      return zero + n;
    },
    getFullTimeStamp(d) {
      let s =
          this.leadingZeros(d.getFullYear(), 4) + '-' +
          this.leadingZeros(d.getMonth() + 1, 2) + '-' +
          this.leadingZeros(d.getDate(), 2) + ' ' +

          this.leadingZeros(d.getHours(), 2) + ':' +
          this.leadingZeros(d.getMinutes(), 2) + ':' +
          this.leadingZeros(d.getSeconds(), 2);

      return s;
    },
    getTimeStamp(d) {
      // let s =
      //     this.leadingZeros(d.getHours(), 2) + ':' +
      //     this.leadingZeros(d.getMinutes(), 2) + ':' +
      //     this.leadingZeros(d.getSeconds(), 2);

      return d.toISOString().slice(-13, -5);
    },
    durationFormat(row, column) {
      return this.getTimeStamp(new Date(row.duration * 1000));
    },
    dateFormat(row, column) {
      console.log('dateFormat', row);
      console.log(row.uploaded_at * 1000);
      return this.getFullTimeStamp(new Date(row.uploaded_at * 1000));
    },
    handleCurrentChange(value) {
      let self = this;
      console.log("handleCurrentChange", value);
      KollusService.getContentByUFK(value.upload_file_key).then(response => {
        self.profiles = response.data.profiles;
      })
    },
    viewContents(category_key){
      let self = this;
      KollusService.getContentsByCategory(category_key).then(response =>{
        this.mediacontents = response.data;
      })
    }
  }
}
</script>

<style scoped>
.current-row {
  background-color: #6f7aad;
}
</style>
