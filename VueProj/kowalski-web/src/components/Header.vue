<template>
  <div style="display: flex;line-height: 60px; font-size: 20px; ">
    <div style="cursor: pointer;">
      <i :class="icon" @click="collapse"></i>
    </div>
    <div style="text-align: center;flex: 1;font-size: 24px">
      <span>
        欢迎进入仓库管理系统
      </span>
    </div>
    <span style="margin-right: 10px; font-size: 16px">王小虎</span>
    <el-dropdown>
      <i class="el-icon-arrow-down"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人资料</el-dropdown-item>
        <el-dropdown-item @click.native="logout">注销</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  methods:{
    toUser(){
      console.log('toUser');
    },
    logout(){
      console.log('logout');
    },
    collapse(){
      this.$emit('doCollapse')
    }
  },
  props:{
    icon:{
      icon:String
    }
  }
}
</script>

<style scoped>

</style>