import Vue from 'vue'
import App from './App'
import router from './router'

import FastClick from 'fastclick' //引入fastclick,处理移动端300ms延迟
import 'lib-flexible/flexible.js'  //vue rem移动端自适适配
import 'normalize.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import './assets/iconfont/iconfont.css'

import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
Vue.use(MintUI)

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

Vue.use(VueAxios,axios)
FastClick.attach(document.body)   //将fastClicl绑定到body身上
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
