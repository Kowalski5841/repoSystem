<template>
<div>
  <el-table :data="tableData"
            style="height: 100%"
            border
    :header-cell-style="{background:'RGB(222,222,222)', color:'#555'}"
  >
    <el-table-column prop="id" label="ID" width="60">
    </el-table-column>
    <el-table-column prop="no" label="账号" width="180">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="120">
    </el-table-column>
    <el-table-column prop="age" label="年龄" width="120">
    </el-table-column>
    <el-table-column prop="sex" label="性别" width="120">
      <template slot-scope="scope">
        <el-tag
            :type="scope.row.sex === 1 ? 'primary' : 'success'"
            disable-transitions>{{scope.row.sex === 1 ? '男' : '女'}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="roleId" label="权限" width="120">
      <template slot-scope="scope">
        <el-tag
            :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
            disable-transitions>{{scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' : '用户')}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="phone" label="手机" width="120">
    </el-table-column>
    <el-table-column prop="operation" label="操作" width="145">
      <el-button size="small" type="primary">编辑</el-button>
      <el-button size="small" type="danger">删除</el-button>
    </el-table-column>
  </el-table>
</div>
</template>

<script>
export default {
  name: "Main",
  data() {

    return {
      tableData:[]
    }
  },
  methods:{
    loadGet(){
      this.$axios.get(this.$HttpUrl + '/user/list').then(res => res.data).then(res=>{
        console.log(res)
      })
    },
    loadPost(){
      this.$axios.post(this.$HttpUrl + '/user/listL',{}).then(res => res.data).then(res=>{
        console.log(res)
        if(res.code === 200){
          this.tableData=res.data
        }else {
          alert('获取数据失败')
        }


      })
    },
  },
  beforeMount() {
    // this.loadGet();
    this.loadPost()
  }
}
</script>

<style scoped>

</style>