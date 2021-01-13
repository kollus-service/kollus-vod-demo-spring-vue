<template>
  <div id="app">
    <el-container>
      <el-header style="background-color: rgba(38,33,0,0.1)">
        <el-row justify="start" style="height: 60px;">
          <el-col :span="8"
                  style="height: 60px; line-height: 60px; display: flex; justify-content: center; align-items: center;">
            <el-image
                style="width: 91px; height: 38px; vertical-align: center;"
                src="https://img.eduwill.net/Img2/Common/BI/type2/live/Logo.svg"
                fit="fit"
            ></el-image>
          </el-col>
          <el-col :span="8" style="height: 60px;">
            <h1 style="text-align: center; height: 60px; margin: 0px; line-height: 60px;">Kollus VOD 데모사이트</h1>
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>

      </el-header>
      <el-main style="background-color: rgba(38,33,0,0.1)">
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {},
  computed: {
    currentUser() {
      return this.$store.state.user;
    },
    showAdmin() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }
      return false;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
}
</script>


<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}


</style>
