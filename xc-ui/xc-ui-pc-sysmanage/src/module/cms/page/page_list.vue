<template>
  <!--页面静态部分，即view部分-->
  <div>
    <div>
      <router-link class="mui-tab-item" :to="{path:'/cms/page/add',query:{
        page:this.page,
        params:this.params
        }}">
        <el-button type="primary" style="float: left;">新增页面</el-button>
      </router-link>
      <el-form :inline="true" :model="params" class="demo-form-inline" style="float: right;">
        <el-form-item label="所属站点">
          <el-select v-model="params.siteId" placeholder="请选择站点" style="width: 120px">
            <el-option
              v-for="item in siteList"
              :label="item.siteName"
              :key="item.siteId"
              :value="item.siteId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="页面名称">
          <el-input v-model="params.pageName" placeholder="页面名称" style="width: 90px"></el-input>
        </el-form-item>
        <el-form-item label="别名">
          <el-input v-model="params.pageAliase" placeholder="页面别名" style="width: 90px"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select v-model="params.pageType" placeholder="请选择" style="width: 90px">
            <el-option
              v-for="item in types"
              :label="item.label"
              :key="item.value"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="findAll">查询全部</el-button>
          <el-button icon="el-icon-search" circle type="primary" @click="query">搜索</el-button>
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
          width="50">
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
          label="类型"
          width="60">
          <template slot-scope="scope">
            <p v-if="scope.row.pageType=='0'">静态</p>
            <p v-if="scope.row.pageType=='1'">动态</p>
          </template>
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
        <el-table-column label="操作" >
          <template slot-scope="page">
            <el-button
              size="mini"
              @click="edit(page.row.pageId)">编辑
            </el-button>
            <br/>
            <el-button
              size="mini"
              type="danger"
              @click="del(page.row.pageId)">删除
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
        types:[{
          value: '0',
          label: '静态'
        }, {
          value: '1',
          label: '动态'
        }],
        pageList: [],
        total: 100,
        page: 1,
        size: 10,
        params: {
          siteId: '',
          pageAliase: '',
          pageName:'',
          pageType:''
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
        cmsApi.page_list(this.page, this.size, this.params).then((res) => {
            console.log(res);
            this.total = res.queryResult.total;
            this.pageList = res.queryResult.list
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
      edit: function (pageId) {
        this.$router.push({
          path: '/cms/page/edit/' + pageId,
          query: {
            //将参数由路由传递
            page: this.page,
            params:this.params
          }
        })
      },
      //删除
      del: function (pageId) {
        this.$confirm('确认删除吗？', '提示', {}).then(() => {
          cmsApi.page_del(pageId).then((res) => {
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
