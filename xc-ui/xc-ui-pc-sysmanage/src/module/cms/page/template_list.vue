<template>
  <!--页面静态部分，即view部分-->
  <div>
    <div>
      <router-link class="mui-tab-item" :to="{path:'/cms/template/add',query:{
        page:this.page,
        params:this.params
        }}">
        <el-button type="primary" style="float: left;">添加模板</el-button>
      </router-link>
      <el-form :inline="true" :model="params" class="demo-form-inline" style="float: right;">
        <el-form-item label="所属站点">
          <el-select v-model="params.siteId" placeholder="请选择站点" style="width: 150px">
            <el-option
              v-for="item in siteList"
              :label="item.siteName"
              :key="item.siteId"
              :value="item.siteId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="模板名称">
          <el-input v-model="params.templateName" placeholder="模板名称" style="width: 120px"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="findAll">查询全部</el-button>
          <el-button icon="el-icon-search" circle type="primary" @click="query">搜索</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div>
      <el-table
        :data="templateList"
        border
        style="width: 100%">
        <el-table-column
          type="index"
          width="60">
        </el-table-column>
        <el-table-column
          prop="templateName"
          label="模板名称">
        </el-table-column>
        <el-table-column
          prop="siteName"
          label="所属站点">
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="template">
            <el-button
              size="mini"
              @click="edit(template.row.templateId)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="del(template.row.templateId)">删除
            </el-button>
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
        siteList: [],
        templateList: [],
        total: 10,
        page: 1,
        size: 10,
        params: {
          siteId: '',
          templateName: ''
        }
      }
    },
    methods: {
      //分页查询
      handleCurrentChange: function (page) {
        this.page = page;
        this.query();
      },
      handleSizeChange: function (size) {
        this.size = size;
        this.query();
      },
      //查询
      query: function () {
        cmsApi.template_list(this.page, this.size, this.params).then((res) => {
            console.log(res);
            this.total = res.queryResult.total;
            this.templateList = res.queryResult.list;
          }
        )
      },
      //查询全部
      findAll:function(){
        this.params={};
        this.page=1;
        this.query();
      },
      //修改
      edit: function (templateId) {
        this.$router.push({
          path: '/cms/template/edit/' + templateId,
          query: {
            //将参数由路由传递
            page: this.page,
            params:this.params
          }
        })
      },
      //删除
      del: function (templateId) {
        this.$confirm('确认删除吗？', '提示', {}).then(() => {
          cmsApi.template_del(templateId).then((res) => {
            if (res.success) {
              this.$message({
                type: 'success',
                message: '删除成功！'
              });
              this.query();
            } else {
              this.$message({
                type: 'error',
                message: '删除失败！'
              });
            }
          })
        })
      }},
      created() {
        cmsApi.site_findAll().then((res)=>{
          console.log(res);
          this.siteList = res.queryResult.list
        });
        //从路由上取参数
        this.page = Number.parseInt(this.$route.query.page || 1);
        this.params = this.$route.query.params || {};
        this.query();
      },
      mounted() {
      }
    }
</script>

<style>
  /*编写页面样式，不是必须*/
</style>
