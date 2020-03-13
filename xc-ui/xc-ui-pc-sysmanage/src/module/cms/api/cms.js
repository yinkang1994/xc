//public是对axios的工具类封装，定义了http请求方法
import http from './../../../base/api/public'
import querystring from 'querystring'

let sysConfig = require('@/../config/sysConfig')
let apiUrl = sysConfig.xcApiUrlPre;

export const page_list = (page, size, params) => {
  //将json对象转成key/value对
  let query = querystring.stringify(params);
  return http.requestQuickGet(apiUrl + '/cms/page/list/' + page + '/' + size + '/?' + query)
};

export const page_add = params => {
  return http.requestPost(apiUrl + '/cms/page/add/', params)
};

//修改
export const page_edit = params => {
  return http.requestPut(apiUrl + '/cms/page/edit/', params)
};
//根据id查询页面
export const page_get = pageId => {
  return http.requestQuickGet(apiUrl + '/cms/page/get/'+pageId)
}
