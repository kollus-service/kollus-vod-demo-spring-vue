<template>
  <el-row>
    <el-table
        :data="logdata"
        @row-click="detail"
    >
      <el-table-column label="ID"
                       prop="id"
      >
      </el-table-column>
      <el-table-column label="로그유형"
                       prop="type"
      >
      </el-table-column>
      <el-table-column label="요청"
                       prop="req"
      >
      </el-table-column>
      <el-table-column label="응답"
                       prop="res"
      >
      </el-table-column>
      <el-table-column label="로그시간"
                       prop="log_at"
      >
      </el-table-column>
    </el-table>
    <el-dialog
        title="컨텐츠 다운로드"
        :visible.sync="dialogVisible"
        width="50%"
    >
      <el-row :gutter="10">
        <el-col :span="12">
          <vue-json-editor ref="reqEditor" v-model="req" :show-btns="false" :expandedOnStart="false"
                           mode="text"></vue-json-editor>
        </el-col>
        <el-col :span="12">
          <vue-json-editor ref="resEditor" v-model="res" :show-btns="false" :expandedOnStart="false"
                           mode="text"></vue-json-editor>
        </el-col>
      </el-row>
      <span slot="footer">
      <el-button @click="dialogVisible = false">닫기</el-button>
    </span>
    </el-dialog>
  </el-row>
</template>

<script>
import KollusService from '../../service/kollus.service'

export default {
  name: "logs",
  data() {
    return {
      logdata: [],
      req: {},
      res: {},
      dialogVisible: false
    }
  },
  created() {
    KollusService.getLog().then(response => {
      this.logdata = response.data;
    })
  },
  methods: {
    detail(row, column, event) {
      let log = this.logdata.filter(item => item.id === row.id)[0]
      this.req = log.req;
      this.res = log.res;
    }
  }
}
</script>

<style scoped>

</style>
