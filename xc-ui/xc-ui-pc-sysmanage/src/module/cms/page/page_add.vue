<template>
  <!--页面静态部分，即view部分-->
  <div>
    <div>
      <el-form :model="pageForm" :rules="rules" ref="pageForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="所属站点" prop="siteId">
          <el-select v-model="pageForm.siteId" placeholder="请选择站点">
            <el-option v-for="item in siteList"
                       :key="item.siteId"
                       :label="item.siteName"
                       :value="item.siteId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="模板选择" prop="templateId">
          <el-select v-model="pageForm.templateId" placeholder="请选择">
            <el-option v-for="item in templateList"
                       :key="item.templateId"
                       :label="item.templateName"
                       :value="item.templateId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="页面名称" prop="pageName">
          <el-input v-model="pageForm.pageName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="别名" prop="pageAliase">
          <el-input v-model="pageForm.pageAliase" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="访问路径" prop="pageWebPath">
          <el-input v-model="pageForm.pageWebPath" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="物理路径" prop="pagePhysicalPath">
          <el-input v-model="pageForm.pagePhysicalPath" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="类型">
          <el-radio-group v-model="pageForm.pageType">
            <el-radio class="radio" label="0">静态</el-radio>
            <el-radio class="radio" label="1">动态</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="创建时间">
          <el-date-picker type="datetime" placeholder="创建时间" v-model="pageForm.pageCreateTime"></el-date-picker>
        </el-form-item>
      </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="danger" @click="resetForm('pageForm')">重置</el-button>
      <el-button type="primary" @click="submitForm('pageForm')">提交</el-button>
      <el-button v-on:click="go_back">返回</el-button>
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
        pageForm: {
          siteId: '',
          templateId: '',
          pageName: '',
          pageAliase: '',
          pageWebPath: '',
          pagePhysicalPath: '',
          pageType: '',
          pageCreateTime: new Date()
        },
        rules: {
          siteId: [
            {required: true, message: '请选择站点', trigger: 'change'},
          ],
          templateId: [
            {required: true, message: '请选择模板', trigger: 'change'}
          ],
          pageName: [
            {required: true, message: '请输入页面名称', trigger: 'blur'}
          ],
          pageWebPath: [
            {required: true, message: '请输入访问路径', trigger: 'blur'}
          ],
          pagePhysicalPath: [
            {required: true, message: '请输入物理路径', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              cmsApi.page_add(this.pageForm).then((res) => {
                console.log(res);
                if (res.success) {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  });
                  this.$refs[formName].resetFields();
                } else {
                  this.$message.error('提交失败');
                }
              })
            })
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      go_back: function () {
        this.$router.push({
          path: '/cms/page/list',
          query: {
            //this.$route.query表示从路由上取的参数列表
            page: this.$route.query.page,
            siteId: this.$route.query.siteId,
          }
        })
      }
    },
    created() {

    }
  }
</script>

<style>
  /*编写页面样式，不是必须*/
</style>
