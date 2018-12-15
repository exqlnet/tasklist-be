<template>
  <div>
    <component :is="tab[index]"></component>
    <!--<school-calendar></school-calendar>-->
    <div class="footer">
      <div class="task" @click="changetaskFont">
        <button class="btn_task"><router-link to="/"><span ref="taskFont" class="iconfont">&#xe630;</span></router-link></button>
      </div>
      <div class="note" @click="changenoteFont">
        <button class="btn_note"><router-link to="/"><span ref="noteFont" class="iconfont">&#xe89e;</span></router-link></button>
      </div>
      <div class="mine" @click="changemineFont">
          <button class="btn_mine"><router-link to="/"><span ref="mineFont" class="iconfont">&#xe608;</span></router-link></button>
      </div>
    </div>
  </div>
</template>

<script>
  import login from '../components/User/login'
  import task from '../components/Task/task'
  import schoolCalendar from '../components/Calendar/schoolCalendar'
  import mine from '../components/User/mine'
  import  axios from 'axios'
  import SchoolCalendar from "./Calendar/schoolCalendar";
  export default {
    name: 'index',
    components: {
      SchoolCalendar,
      login,
      task,
      mine
    },
    data () {
      return{
        tab:["task", "SchoolCalendar", "mine"],
        index: 0,
        token : localStorage.jwtToken,
        data: [],
        finish: 0,  //已完成的任务
        unFinish: 0,
      }
    },
    methods: {
      todoSomething () {
        if (this.token) {
          axios({
            method: 'get',
            url: 'http://www.todobest.tk/api/task/list',
            headers: {
              Authorization: this.token
            }
          })
        } else {
          this.$router.push({path: '/User/login'})
        }
      },
      changetaskFont () {
        this.index = 0
        this.$refs.taskFont.innerHTML = '&#xe630'
        this.$refs.noteFont.innerHTML = '&#xe841'
        this.$refs.mineFont.innerHTML = '&#xe608'
      },
      changenoteFont () {
        this.index =1
        this.$refs.taskFont.innerHTML = '&#xe83d'
        this.$refs.noteFont.innerHTML = '&#xe636'
        this.$refs.mineFont.innerHTML = '&#xe608'
      },
      changemineFont () {
        this.index = 2
        this.$refs.taskFont.innerHTML = '&#xe83d'
        this.$refs.noteFont.innerHTML = '&#xe841'
        this.$refs.mineFont.innerHTML = '&#xe62a'
      }
    },
    mounted () {
      this.todoSomething()
    }
  }
</script>
<style scoped>
  .iconfont{
    font-size: 25px;
    color: #595959;
  }
  .footer{
    border-top: 1px solid #f3f3f3;
    box-shadow: 0px -1px 1px #f7f7f714;
    position: fixed;
    bottom: 0;
    width: 10rem;
    height: 1.3rem;
    line-height: 1.3rem;
    z-index: 10;
    background: white;
  }
  .footer div{
    float: left;
    width: 3.33rem;
  }
  .footer div button{
    height: 1.3rem;
    line-height: 1.3rem;
    display: block;
    width: 1rem;
    margin: 0 auto;
    border: 0;
    background: #FFFFFF;
  }

</style>
