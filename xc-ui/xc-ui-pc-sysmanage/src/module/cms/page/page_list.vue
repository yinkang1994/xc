<template>
  <!--页面静态部分，即view部分-->
  <div>
    <div>
      <router-link class="mui-tab-item" :to="{path:'/cms/page/add',query:{
        page:this.page,
        siteId:this.params.siteId
        }}">
      <el-button type="primary" style="float: left;" >新增页面</el-button>
      </router-link>
      <el-form :inline="true" :model="params" class="demo-form-inline" style="float: right;">
        <el-form-item label="所属站点">
          <el-select v-model="params.siteId" placeholder="请选择站点">
            <el-option
              v-for="item in siteList"
              :label="item.siteName"
              :key="item.siteId"
              :value="item.siteId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="别名">
          <el-input v-model="params.pageAliase" placeholder="页面别名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="query">查询</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div>
      <el-table
        :data="pageList"
        border
        style="width: 100%">
        <el-table-column
          type="index"
          width="60">
        </el-table-column>
        <el-table-column
          prop="pageName"
          label="页面名称"
          width="120">
        </el-table-column>
        <el-table-column
          prop="pageAliase"
          label="别名"
          width="120">
        </el-table-column>
        <el-table-column
          prop="pageType"
          label="页面类型"
          width="100">
        </el-table-column>
        <el-table-column
          prop="pageWebPath"
          label="访问路径"
          width="150">
        </el-table-column>
        <el-table-column
          prop="pagePhysicalPath"
          label="物理路径"
          width="200">
        </el-table-column>
        <el-table-column
          prop="pageCreateTime"
          label="创建时间"
          width="200">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="page">
            <el-button
              size="mini"
              @click="edit(page.row.pageId)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="delete(page.row.pageId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!--分页导航条-->
    <div class="block" style="margin-top: 10px;float: right ">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page"
        :page-sizes="[10, 25, 50, 100]"
        :page-size="size"
        layout="total, sizes, prev, pager, next"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import * as cmsApi from '../api/cms'
  // model部分
  export default {
    data() {
      return {
        siteList:[],
        pageList: [],
        total: 100,
        page:1,
        size:10,
        params: {
          siteId:'',
          pageAliase:''
        }
      }
    },
    methods: {
      //分页查询
      handleCurrentChange: function (page) {
        this.page=page;
        this.query();
      },
      handleSizeChange: function (size) {
        this.size=size;
        this.query();
      },
      //查询
      query: function () {
        cmsApi.page_list(this.page, this.size, this.params).then((res) => {
            console.log(res);
            this.total = res.queryResult.total;
            this.pageList = res.queryResult.list
          }
        )
      },
      //修改
      edit:function (pageId) {
        this.$router.push({
          path: '/cms/page/edit/'+pageId,
          query: {
            //this.$route.query表示从路由上取的参数列表
            page: this.page,
            siteId: this.siteId,
          }
        })
      },
      //删除
      delete:function () {
        
      }

    },
    created(){
      //从路由上取参数
      this.page=Number.parseInt(this.$route.query.page||1);
      this.params.siteId=this.$route.query.siteId||'';
      this.query();
    } ,
    mounted(){

    }
  }
</script>

<style>
  /*编写页面样式，不是必须*/
</style>
