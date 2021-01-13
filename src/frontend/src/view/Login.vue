<template>
  <div class="login">
    <el-card>
      <h2>Login</h2>
      <ValidationObserver ref="form">
        <el-form class="login-form" @submit.prevent.native="login">
          <ValidationProvider rules="required|minmaxinput:5,15|alpha_num" name="Username" v-slot="{errors}">
            <el-form-item prop="username" :error="errors[0]">
              <el-input v-model="username" placeholder="Username" prefix-icon="fas fa-user"></el-input>
            </el-form-item>
          </ValidationProvider>
          <ValidationProvider rules="required|minmaxinput:5,15|alpha_num" name="Password" v-slot="{errors}">
            <el-form-item prop="password" :error="errors[0]">
              <el-input
                  v-model="password"
                  placeholder="Password"
                  type="password"
                  prefix-icon="fas fa-lock"
              ></el-input>
            </el-form-item>
          </ValidationProvider>
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
        </el-form>
      </ValidationObserver>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      loading: false
    }
  },
  methods: {
    login() {
      console.log(this.username, this.password)
      this.loading = true;
      this.$refs.form.validate().then(isValid => {
        if (!isValid) {
          this.loading = false;
          return;
        }
      });
      if(this.username === '111111' && this.password === '111111'){
        this.$router.push('/admin');
      }else
      if(this.username === '222222' && this.password === '222222'){
        this.$router.push('/viewer');
      }else
      if (this.username && this.password) {
        this.$store.dispatch('auth/login', this.user).then(
            () => {
              //todo : URL 지정
              this.$router.push()
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
