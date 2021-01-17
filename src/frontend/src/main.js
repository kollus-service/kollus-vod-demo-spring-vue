import Vue from 'vue'
import App from './App.vue'
import {router} from './router'
import store from './store'
import {ValidationProvider, ValidationObserver} from 'vee-validate'
import './rules';
import Vuex from 'vuex'
import 'element-ui/lib/theme-chalk/index.css';
import './theme/index.css';
import ElementUI from 'element-ui';


import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {faUser, faLock} from "@fortawesome/free-solid-svg-icons";

import {BootstrapVue} from 'bootstrap-vue';

import VueI18n from "vue-i18n";
import lang from 'element-ui/lib/locale/lang/ko';
import locale from 'element-ui/lib/locale';



library.add(faUser);
library.add(faLock);


locale.use(lang);

import './kollus/vg-controller-clint.latest.min'

Vue.config.productionTip = false;
Vue.config.devtools = true;
Vue.use(Vuex);
Vue.use(ElementUI, {lang});
Vue.use(VueI18n);
Vue.use(BootstrapVue);

Vue.component('ValidationProvider', ValidationProvider);
Vue.component('ValidationObserver', ValidationObserver);
Vue.component('font-awesome-icon', FontAwesomeIcon);
new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app')
