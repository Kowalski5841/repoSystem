<template>
  <el-container style="height: 100vh;">
    <el-aside :width="aside_width" style="background-color: rgb(238, 241, 246);">
      <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container style="height: 100%;">
      <el-header style="text-align: right; font-size: 12px">
        <Header @doCollapse="doCollapse" :icon="icon"></Header>
      </el-header>

      <el-main style="height: 100%;">
        <Main></Main>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";
import Main from "@/components/Main";

export default {
  name: "Index",
  components:{
    Aside,
    Header,
    Main
  },
  data(){
    return{
      isCollapse:false,
      aside_width:"200px;",
      icon:'el-icon-s-fold'
    }
  },
  methods:{
    doCollapse(){
      console.log('doCollapse');
      this.isCollapse = !this.isCollapse
      if(!this.isCollapse){ //false 为展开
        this.aside_width='200px';
        this.icon='el-icon-s-fold'
      }else{//TRUE为收起
        this.aside_width="64px";
        this.icon='el-icon-s-unfold'
      }
    }
  }

}
</script>

<style scoped>
.el-header, .el-footer {
  background-color:#999;
  color: #333;
  line-height: 60px;
}
.el-main{
  padding: 5px;
}
.el-aside {
  color: #333;
}
</style>