<template>
  <div class="register">
    <div class="header">
      <router-link to="/User/login">
        <span class="iconfont">&#xe61b;</span>
      </router-link>
    </div>

    <!--注册成功后弹出 返回信息-->
    <mt-popup
      v-model="registerVisible"
      position="top" class="registerVisible">
     {{registerMsg}}
    </mt-popup>
    <!--点击验证码后 返回信息-->
    <mt-popup
      v-model="verifyVisible"
      position="top" class="verifyVisible">
      {{verifyMsg}}
    </mt-popup>

    <div class="register">
      <form class="register_form">
        <img class="register_image" src="../../assets/images/register.png">
        <div class="content">
          <label class="account_number">
            <i class="iconfont">&#xe62a;</i>
            <input type="email" name="user_register" placeholder=" 请输入邮箱地址" required="true" title="请输入帐号" v-model="email" autocomplete="on">
          </label>
          <label class="account_verify">
            <i class="iconfont">&#xe629;</i>
            <input type="number" name="user_verify" placeholder=" 请输入验证码" title="请输入验证码" v-model="verifyCode">
            <span class="btn_verify" @click="verify">获取验证码</span>
          </label>
          <label class="account_password">
            <i class="iconfont">&#xe638;</i>
            <input type="password" name="user_pass" placeholder=" 请输入密码"  required="true" title="请输入密码" v-model="pass" autocomplete="on">
          </label>
            <button type="button" class="btn_register" @click="register">注册</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "register",
    data () {
      return{
        email: '',
        pass: '',
        verifyCode: '',
        registerVisible: false,
        registerMsg: '',
        verifyVisible: false,
        verifyMsg: ''
      }
    },
    methods: {
      register () {
        let postData = { email: this.email, password: this.pass, verifyCode: this.verifyCode }
        axios.post('http://www.todobest.tk/api/user/register', postData).then(this.todoSomething)
      },
      todoSomething (res) {
        this.registerVisible = true
        this.registerMsg = res.data.data
        setTimeout(()=>{
          if (res.data.code){
            this.$router.push({path: '/User/login'})
          }
        },2500)
      },
      verify(){
        if(this.email === ''){
          this.verifyVisible = true
          this.verifyMsg = "输入邮箱后才能发送哦"
        } else {
          let postVerifyCode = {email: this.email}
          axios.post('http://www.todobest.tk/api/user/sendVcode', postVerifyCode).then(this.verifying)
        }
      },
      verifying(res){
        this.verifyVisible = true
        this.verifyMsg = res.data.data
        if(res.data.code){
        //TODO
        }
      }
    }
  }
</script>

<style scoped>
  a{
    text-decoration: none;
    color: #292421;
  }
  .registerVisible{
    width: 100%;
    height: 1rem;
    font-size: 30px;
    text-align: center;
  }
  .verifyVisible{
    width: 100%;
    height: 1rem;
    font-size: 30px;
    text-align: center;
  }
  .register{
    font-family:PingFangSC-Semibold, sans-serif;
  }
  .header{
    padding-top: 1.1rem;
    padding-left: 0.8rem;
  }
  .header span{
    font-size: 20px;
  }
  .register_form{
    width: 7.5rem;
    margin: 60px auto 0 auto;
    position: relative;
  }
  .register_image{
    width: 7.5rem;
    display: block;
    margin: 0 auto;
  }
  .content{
    position: relative;
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
  .iconfont{
    font-size: 25px;
  }

  .account_number i{
    position: absolute;
    top: 0.5rem;
  }
  .account_verify i{
    position: absolute;
    top: 2.19rem;
  }
  .account_password i{
    position: absolute;
    top:3.78rem;
  }
  .btn_verify{
    position: absolute;
    top:2.25rem;
    right: 0;
    font-size: 18px;
  }
  .btn_register{
    margin-top: 38px;
    margin-bottom: 11px;
    width: 7.5rem;
    height:1.25rem;
    background:#292421;
    border: 0;
    border-radius: 10px;
    color:white ;
    box-shadow: 0 3px 6px rgb(220,220,220);
    font-size: 16px;
  }

</style>
