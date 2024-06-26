import axios from 'axios';
import router from '@/router';

axios.defaults.baseURL = 'http://localhost:8888';
// 前置拦截
axios.interceptors.request.use(config => {
  return config;
});
axios.interceptors.response.use(respone => {
  let res = respone.data;
  if (res.code === 200) {
    return respone;
  } else {
    this.$message.error('密码错误', {duration: 3 * 1000});
    return Promise.reject(respone.data.msg);
  }
},
error => {
  if (error.response.data) {
    error.message = error.response.data.msg;
  }
  if (error.response.status === 401) {
    // eslint-disable-next-line no-undef
    store.commit('REMOVE_INFO');
    router.push('/login');
  }
  // eslint-disable-next-line no-undef
  ElMessage.error(error.message, {duration: 3 * 1000});
  return Promise.reject(error);
}

);
