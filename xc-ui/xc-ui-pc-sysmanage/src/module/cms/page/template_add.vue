<template>
  <!--页面静态部分，即view部分-->
  <div>
    <div>
      <el-form :model="templateForm" :rules="rules" ref="templateForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="所属站点" prop="siteId">
          <el-select v-model="templateForm.siteId" placeholder="请选择站点">
            <el-option v-for="item in siteList"
                       :key="item.siteId"
                       :label="item.siteName"
                       :value="item.siteId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="templateForm.templateName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="选择模板文件">
          <el-upload
            class="upload-demo"
            ref="my-upload"
            action="api/cms/template/upload"
            :before-upload="beforeUpload"
            :before-remove="beforeRemove"
            :on-success="uploadSuccess"
            :on-error="uploadError"
            :limit="1"
            :on-exceed="handleExceed">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip"></div>
          </el-upload>
        </el-form-item>
      </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm('templateForm')">提交</el-button>
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
        templateForm: {
          siteId: '',
          templateName: '',
          templateFileId: ''
        },

        rules: {
          siteId: [
            {required: true, message: '请选择站点', trigger: 'change'},
          ],
          templateName: [
            {required: true, message: '请输入模板名称', trigger: 'change'}
          ]
        }
      }
    },
    methods: {
      //上传成功
      uploadSuccess(res) {
        this.templateForm.templateFileId=res;
        this.$message({
          type: 'success',
          message: '上传成功!'
        });
      },
      //上传失败
      uploadError() {
        this.$refs['my-upload'].clearFiles();
        this.$message.warning(`上传失败！`);
      },
      //文件超出个数
      handleExceed() {
        this.$message.warning(`只能上传一个文件`);
      },
      //删除提示
      beforeRemove() {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(() => {
          cmsApi.template_delTemplateFileByFileId(this.templateForm.templateFileId).then((res) => {
            if (res.success) {
              this.$message({
                type: 'success',
                message: '删除成功！'
              });
              return true;
            } else {
              this.$message({
                type: 'error',
                message: '删除失败！'
              });
              return false;
            }


          })
        })
      },
      //上传提示
      beforeUpload(file) {
        return this.$confirm('确认上传该文件', '确认信息', {
          distinguishCancelAndClose: true,
          confirmButtonText: '确定',
          cancelButtonText: '放弃'
        });
      },

      submitForm(templateForm) {
        if (typeof this.templateForm.templateFileId === "undefined" || this.templateForm.templateFileId == null || this.templateForm.templateFileId.trim() === "") {
          this.$alert('请上传模板文件', '提示', {
            confirmButtonText: '确定'
          });
          return false;
        }
        this.$refs[templateForm].validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              cmsApi.template_add(this.templateForm).then((res) => {
                console.log(res);
                if (res.success) {
                  this.$message({
                    message: '提交成功',
                    type: 'success'
                  });
                  this.$refs[templateForm].resetFields();
                  this.$refs['my-upload'].clearFiles();
                } else if (res.message) {
                  this.$message.error(res.message);
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
      go_back: function () {
        this.$router.push({
          path: '/cms/template/list',
          query: {
            //this.$route.query表示从路由上取的参数列表
            page: this.$route.query.page,
            params: this.$route.query.params,
          }
        })
      }
      ,

    },
    created() {
      //站点列表赋值
      cmsApi.site_findAll().then((res) => {
        console.log(res);
        this.siteList = res.queryResult.list
      });
    }
  }
</script>

<style>
  /*编写页面样式，不是必须*/
</style>
