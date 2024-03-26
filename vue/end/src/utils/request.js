import axios from 'axios'
import router from '@/router';

const request = axios.create({
    baseURL:"http://localhost:8888",
    timeout: 5000
})


request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    const userJson = sessionStorage.getItem("user")
    if(!userJson){
        router.push('/login')
    }
    return config
}, error => {
    return Promise.reject(error)
});


request.interceptors.response.use(
    response => {
        let res = response.data;
        if (response.config.responseType === 'blob') {
            return res
        }
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        return Promise.reject(error)
    }
)

export default request

