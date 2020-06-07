// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import { library } from '@fortawesome/fontawesome-svg-core';
import { faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import Axios from 'axios';

import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

import Vue from 'vue';
import App from './App';
import router from './router';

library.add(faTimesCircle);

Vue.component('font-awesome-icon', FontAwesomeIcon);

Vue.prototype.$http = Axios.create({
  baseURL: process.env.VUE_APP_ROOT_URL,
});

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app');
