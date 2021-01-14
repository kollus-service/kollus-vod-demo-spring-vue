import axios from 'axios'
import {default as properties} from '../properties'
import authHeader from "./auth-header";

class KollusService {
    getUploadUrl(data){
        return axios.post(properties.API_DOMAIN + '/api/upload', {
            headers: authHeader(),
            data:data})
    }
    getCategories(){
        return axios.get(properties.API_DOMAIN + '/api/categories', {headers: authHeader()});
    }
    getChannels(){
        return axios.get(properties.API_DOMAIN + '/api/channels', {headers: authHeader()});
    }
    getContentsByCategory(category_key){
        return axios.get(properties.API_DOMAIN + '/api/contents/category/'+category_key, {headers: authHeader()});
    }
    getContentsByChannel(channel_key){
        return axios.get(properties.API_DOMAIN + '/api/contents/channel/'+channel_key, {headers: authHeader()});
    }
    getContentByUFK(uplaod_file_key){
        return axios.get(properties.API_DOMAIN + '/api/contents/ufk/'+upload_file_key, {headers: authHeader()});
    }
    getContentByMCK(media_content_key){
        return axios.get(properties.API_DOMAIN + '/api/contents/mck/'+media_content_key, {headers: authHeader()});
    }
    getPlayUrl(cuid, media_content_key){
        return axios.get(properties.API_DOMAIN + '/api/player/'+media_content_key + '/cuid/'+cuid, {headers: authHeader()});
    }
    getPolicy(channel_key){
        return axios.get(properties.API_DOMAIN + '/api/policy/'+ channel_key, {headers: authHeader()});
    }
    savePolicy(type, channel_key, data){
        return axios.post(properties.API_DOMAIN + '/api/policy/'+type+'/'+ channel_key, {data:data,headers: authHeader()});
    }
    getLog(){
        return axios.get(properties.API_DOMAIN + '/api/log', {headers: authHeader()});
    }


}