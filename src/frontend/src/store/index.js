import Vue from 'vue'
import Vuex from 'vuex'

import {auth} from './auth.module'

Vue.use(Vuex);

const state = {
    uid: '',
    errorState : '',
    isAuth: false
};

export default new Vuex.Store({
    modules: {
        auth
    }
});
