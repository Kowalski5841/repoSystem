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
    <span style="margin-right: 10px; font-size: 16px">{{ user.name }}</span>
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
  data(){
    let u = sessionStorage.getItem("CurUser")
    console.log(u)
    return{
        user : JSON.parse(u)
    }
  },
  methods:{
    toUser(){
      console.log('toUser');
      this.$router.push("/home")
    },
    logout(){
      console.log('logout');

      this.$confirm('您确认要退出吗？','提示',{
        confirmButtonText:'确定',
        type:'warning',
        center: true
      })
        .then(()=>{
          this.$message({
            type:'success',
            message:'退出成功'
          })
          //跳转回登录页面。登录页面的路径就是一个/
          this.$router.push("/")
          //清空相关数据
          sessionStorage.clear()
        })
      .catch(()=>{
        this.$message({
          type:'info',
          message:'已取消操作'
        })
      })


    },
    collapse(){
      this.$emit('doCollapse')
    }
  },
  created(){
    this.$router.push("/Home")
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