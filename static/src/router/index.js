import Vue from 'vue'
import Router from 'vue-router'
import index from '../components/index'
import login from '../components/User/login'
import register from '../components/User/register'
import task from '../components/Task/task'
import shoolCalendar from '../components/Calendar/schoolCalendar'
import suggestion from '../components/User/suggestion'
import about from '../components/User/about'
import modifyPassword from '../components/User/modifyPassword'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },{
      path: '/User/register',
      name: 'register',
      component: register,
    },
    {
      path: '/User/login',
      name: 'login',
      component: login
    },
    {
      path: '/Task/task',
      name: task,
      component: task
    },
    {
      path: '/Calendar',
      name: shoolCalendar,
      component: shoolCalendar
    },
    {
      path: '/User/suggestion',
      name: suggestion,
      component: suggestion
    },
    {
      path: '/User/about',
      name: about,
      component: about
    },
    {
      path: '/User/modifyPassword',
      name: modifyPassword,
      component: modifyPassword
    }
  ]
})
