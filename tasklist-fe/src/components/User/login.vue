<template>
      <div class="login">
      <form class="login_form">
        <img class="login_image" src="../../assets/images/login.png">
          <div class="content">
            <label class="account_number">
              <i class="iconfont">&#xe62a;</i>
              <input type="email" name="user_login"  placeholder=" 请输入邮箱地址" required="true" title="请输入帐号" v-model="email" autocomplete="off">
            </label>
            <label class="account_password">
              <i class="iconfont">&#xe638;</i>
              <input type="password" name="user_pass" placeholder=" 请输入密码"  required="true" title="请输入密码" v-model="pass" autocomplete="off">
            </label>
            <div class="user_nav">
              <button type="button" class="btn_login" @click="login">登录</button>
              <router-link to="/User/register" class="register">
                注册
              </router-link>
              <router-link to="/User/modifyPassword" class="forget">
                忘记密码
              </router-link>
            </div>
          </div>
        <mt-popup
          v-model="loginVisible"
          position="top" class="loginVisible">
          {{loginMsg}}
        </mt-popup>
      </form>
    </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "login",
    data(){
      return{
        email: '',
        pass: '',
        loginVisible: false,
        loginMsg: ''
      }
    },
    methods: {
      login () {
        localStorage.setItem("shixianEmail", this.email)
        let postData = { email: this.email, password: this.pass }
        axios.post('http://www.todobest.tk/api/user/login', postData).then(this.todoSomething)
      },
      todoSomething (res) {
        this.loginVisible = true
        this.loginMsg = res.data.data
        if(res.data.code){
          localStorage.setItem("jwtToken",res.data.data.token)
          this.$router.push({path:'/'})
        }
      }
    }

  }
</script>

<style scoped>
  .login{
    font-family: PingFangSC-Semibold, sans-serif;
  }
  .loginVisible{
    width: 100%;
    height: 1rem;
    font-size: 30px;
    text-align: center;
  }
  .login_form{
    width: 7.41rem;
    margin: 54px auto 0 auto;
    position: relative;
  }
  .login_image{
    width: 6.134rem;
    display: block;
    margin: 0 auto;
  }
  .content{
    position: relative;
    margin-top: 51px;
  }
  .content label i{
    font-size: 25px;
  }
  input{
    width: 6.5rem;
    height: 60px;
    line-height: 60px;
    outline: none;
    border-bottom: 1px solid rgb(176,176,176) ;
    border-top: 0px;
    border-left: 0px;
    border-right: 0px;
    padding-left: 1rem;
    font-size: 18px;
  }
  .account_number i{
    position: absolute;
    top: 0.45rem;
    left: 0;
  }
  .account_password i{
    position: absolute;
    top: 2.1rem;
  }
  .btn_login{
    margin-top: 48px;
    margin-bottom: 11px;
    width: 7.41rem;
    height: 1rem;
    background: #292421;
    border: 0;
    border-radius: 10px;
    color:white ;
    box-shadow: 0px 3px 6px rgb(220,220,220);
    font-size: 16px;
  }
  .register{
    float: left;
    color: #3D3D3D ;
    font-size: 18px;
    text-decoration: none;
  }
  .forget{
    float: right;
    color: #3D3D3D ;
    font-size: 18px;
    text-decoration: none;
  }
</style>
