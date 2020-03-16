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
export const page_edit = (pageId,params) => {
  return http.requestPut(apiUrl + '/cms/page/edit/'+pageId, params)
};
//根据id查询页面
export const page_get = pageId => {
  return http.requestQuickGet(apiUrl + '/cms/page/get/'+pageId)
};
//根据id删除页面
export const page_del = pageId => {
  return http.requestDelete(apiUrl + '/cms/page/del/'+pageId)
};




//查询所有站点
export const site_findAll = () => {
  return http.requestQuickGet(apiUrl + '/cms/site/findAll/')
};

//条件查询模板
export const template_list = (page, size, params) => {
  //将json对象转成key/value对
  let query = querystring.stringify(params);
  return http.requestQuickGet(apiUrl + '/cms/template/list/'+ page + '/' + size + '/?' + query)
};
//查询所有模板
export const template_findAll = () => {
  return http.requestQuickGet(apiUrl + '/cms/template/findAll')
};
//根据站点id查询模板
export const template_findBySiteId = (siteId) => {
  return http.requestQuickGet(apiUrl + '/cms/template/findBySiteId/'+siteId)
};
//添加模板
export const template_add = (params) => {
  return http.requestPost(apiUrl + '/cms/template/add/',params)
};


//删除模板
export const template_del = (templateId) => {
  return http.requestDelete(apiUrl + '/cms/template/del/'+templateId)
};



//根据模板文件id删除模板文件
export const template_delTemplateFileByFileId = (templateFileId) => {
  return http.requestDelete(apiUrl + '/cms/template/delTemplateFileByFileId/'+templateFileId)
};


