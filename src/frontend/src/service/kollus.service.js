import axios from 'axios'
import {default as properties} from '../properties'
import authHeader from "./auth-header";
import AuthService from './auth.service';
import {router} from '../router';

axios.interceptors.response.use(
    function (response) {
        return response
    },
    function (error) {
        if (error.response.status === 405 ||
            error.response.status === 401) {
            AuthService.logout();
            router.push('/login').then(r => {
                console.log(r)
            });
        }
    }
)

class KollusService {

    getUploadUrl(data) {
        console.log(data);
        return axios.post(properties.API_DOMAIN + '/api/upload/create', data, {headers: authHeader()})
    }

    upload(url, data) {
        return axios.post(url, data, {headers: {'Content-Type': 'multipart/form-data'}});
    }

    getCategories() {
        return axios.get(properties.API_DOMAIN + '/api/info/categories', {headers: authHeader()});
    }

    getChannels() {
        return axios.get(properties.API_DOMAIN + '/api/info/channels', {headers: authHeader()});
    }

    getContentsByCategory(category_key) {
        return axios.get(properties.API_DOMAIN + '/api/info/contents/category/' + category_key, {headers: authHeader()});
    }

    getContentsByChannel(channel_key) {
        return axios.get(properties.API_DOMAIN + '/api/info/contents/channel/' + channel_key, {headers: authHeader()});
    }

    getContentByUFK(upload_file_key) {
        return axios.get(properties.API_DOMAIN + '/api/info/contents/ufk/' + upload_file_key, {headers: authHeader()});
    }

    getContentByMCK(media_content_key) {
        return axios.get(properties.API_DOMAIN + '/api/info/contents/mck/' + media_content_key, {headers: authHeader()});
    }

    getProfiles() {
        return axios.get(properties.API_DOMAIN + '/api/info/profiles', {headers: authHeader()});
    }

    getPlayUrl(data) {
        return axios.post(properties.API_DOMAIN + '/api/player/create', data, {headers: authHeader()});
    }

    getPlayUrlByPayload(data) {
        return axios.post(properties.API_DOMAIN + '/api/player/payload', data, {headers: authHeader()});
    }

    getQueryString(data){
        return axios.post(properties.API_DOMAIN + '/api/player/querystring', data, {headers: authHeader()})
    }

    download(data) {
        return axios.post(properties.API_DOMAIN + '/api/player/download', data, {headers: authHeader()});
    }

    getPolicy(channel_key) {
        return axios.get(properties.API_DOMAIN + '/api/policy/channel/' + channel_key, {headers: authHeader()});
    }

    getPolicyWithMck(channel_key, media_content_key) {
        return axios.get(properties.API_DOMAIN + '/api/policy/channel/' + channel_key + '/mck/' + media_content_key, {headers: authHeader()});
    }

    getPolicyWithCuid(cuid) {
        return axios.get(properties.API_DOMAIN + '/api/policy/cuid/' + encodeURIComponent(cuid), {headers: authHeader()});
    }
    getPolicyWithChannelAndCuid(channel_key, cuid) {
        return axios.get(properties.API_DOMAIN + '/api/policy/channel/' + channel_key + '/cuid/' + cuid, {headers: authHeader()});
    }
    getPolicyWithMckAndCuid(channel_key, media_content_key, cuid) {
        return axios.get(properties.API_DOMAIN + '/api/policy/channel/' + channel_key + '/mck/' + media_content_key + '/cuid/' + cuid, {headers: authHeader()});
    }

    savePolicy(type, data, channel_key) {
        return axios.post(properties.API_DOMAIN + '/api/policy/' + type + '/channel/' + channel_key,
            data,
            {
                headers: authHeader()
            });
    }
    savePolicyWithMck(type, data, channel_key, media_content_key) {
        return axios.post(properties.API_DOMAIN + '/api/policy/' + type + '/channel/' + channel_key + '/mck/' + media_content_key,
            data,
            {
                headers: authHeader()
            });
    }
    savePolicyWithMckAndCuid(type, data, channel_key, media_content_key, cuid) {
        return axios.post(properties.API_DOMAIN + '/api/policy/' + type + '/channel/' + channel_key + '/mck/' + media_content_key + '/cuid/' + cuid,
            data,
            {
                headers: authHeader()
            });
    }
    savePolicyWithCuid(type, data, cuid){
        return axios.post(properties.API_DOMAIN + '/api/policy/' + type + '/cuid/' + cuid,
            data,
            {
                headers: authHeader()
            });
    }
    savePolicyWithChannelAndCuid(type, data, channel_key, cuid){
        return axios.post(properties.API_DOMAIN + '/api/policy/' + type + '/channel/' + channel_key + '/cuid/' + cuid,
            data,
            {
                headers: authHeader()
            });
    }



    getLog() {
        return axios.get(properties.API_DOMAIN + '/api/info/logs', {headers: authHeader()});
    }
    getLms(mck, cuid) {
        return axios.get(properties.API_DOMAIN + '/api/info/lms/mck/'+mck+'/cuid/'+cuid, {headers: authHeader()});
    }
    getUsers(){
        return axios.get(properties.API_DOMAIN + '/api/info/users', {headers:authHeader()});
    }
    addUser(user){
        return axios.get(properties.API_DOMAIN + '/api/info/users', user,{headers:authHeader()});
    }
}
export default new KollusService();
