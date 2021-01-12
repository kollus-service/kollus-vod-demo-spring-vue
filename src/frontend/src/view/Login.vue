<template>
  <div class="login">
    <el-card>
      <h2>Login</h2>
      <el-form
          class="login-form"
          :model="model"
          :rules="rules"
          ref="form"
          @submit.native.prevent="login"
      >
        <el-form-item prop="username">
          <el-input v-model="model.username" placeholder="Username" prefix-icon="fas fa-user"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              v-model="model.password"
              placeholder="Password"
              type="password"
              prefix-icon="fas fa-lock"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
              :loading="loading"
              class="login-button"
              type="primary"
              native-type="submit"
              block
          >Login
          </el-button>
        </el-form-item>
        <a class="forgot-password" href="https://oxfordinformatics.com/">Forgot password ?</a>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      validCredentials: {
        username: "lightscope",
        password: "lightscope"
      },
      model: {
        username: "",
        password: ""
      },
      loading: false,
      rules : {
        username: [
          {
            required: true,
            message: "사용자명이 필요합니다.",
            trigger: "blur"
          },
          {
            min: 4,
            message: "사용자명은 5자이상입니다.",
            trigger: "blur"
          }
        ],
        password:[
          {
            required: true,
            message: "비밀번호를 입력해 주세요.",
            trigger: "blur"
          },
          {
            min: 5,
            message: "비밀번호는 6자이상입니다.",
            trigger: "blur"
          }
        ]
      }
    }
  },
  methods: {
    async login() {
      this.loading = true;
      this.$validator.validateAll().then(isValid => {
        if(!isValid){
          this.loading = false;
          return;
        }
      });
      if(this.user.username && this.user.password){
        this.$store.dispatch('auth/login', this.user).then(
            () => {
              //todo : URL 지정
              this.router.push()
            },
            error => {
              this.loading = false;
              this.message = (error.response && error.response.data && error.response.data.message) ||
                  error.message || error.toString();
            }
        )
      }
    }
  }
};
</script>

<style scoped>

</style>
