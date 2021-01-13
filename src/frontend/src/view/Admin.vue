<template>
  <el-row :gutter="20">
    <el-col :span="menuspan" style="height: 100vh;">
      <el-menu
          default-active="0"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
      :collapse="isCollapse">
        <el-menu-item index="0">
          <router-link to="#" @click.native="handleCollapse" tag="li">
            <i class="el-icon-s-grid"></i>
            <span>메뉴 감추기</span>
          </router-link>
        </el-menu-item>
        <el-menu-item index="1">
          <router-link to="/admin/upload" tag="li">
            <i class="el-icon-upload"></i>
            <span>Upload</span>
          </router-link>
        </el-menu-item>
        <el-menu-item index="2">
          <router-link to="/admin/categories" tag="li">
            <i class="el-icon-folder"></i>
            <span>Categories</span>
          </router-link>
        </el-menu-item>
        <el-menu-item index="3">
          <router-link to="/admin/channels" tag="li">
            <i class="el-icon-s-claim"></i>
            <span>Channel</span>
          </router-link>
        </el-menu-item>
        <el-menu-item index="4">
          <router-link to="/admin/player" tag="li">
            <i class="el-icon-video-play"></i>
            <span>Player</span>
          </router-link>
        </el-menu-item>
        <el-menu-item index="5">
          <router-link to="/admin/users" tag="li">
            <i class="el-icon-user-solid"></i>
            <span>User</span>
          </router-link>
        </el-menu-item>
        <el-menu-item index="6">
          <router-link to="/admin/logs" tag="li">
            <i class="el-icon-s-data"></i>
            <span>Log</span>
          </router-link>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="24-menuspan">
      <router-view/>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Admin",
  data(){
    return {
      isCollapse: true,
      menuspan : 1,
    }
  },
  methods: {
    handleOpen: () => {
    },
    handleClose: () => {
    },
    handleCollapse(){
      this.isCollapse = !this.isCollapse;
      this.menuspan = this.isCollapse ? 1  : 3;
    }
  }
}
</script>

<style scoped>

</style>
