import axios from 'axios'
import {default as properties} from '../properties'

class AuthService {
    login(user) {
        return axios
            .post(properties.API_DOMAIN + '/api/auth/signin', {
                username: user.username,
                password: user.password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }
}

export default new AuthService();
