<template>
<div>
  <div>
    <el-input v-model="name" placeholder="请输入姓名"
              suffix-icon="el-icon-search"
              @keyup.enter.native="loadPost"
              style="width: 200px; margin-bottom: 10px"></el-input>
    <el-select style="margin-left: 10px"
                v-model="sex" filterable placeholder="请选择">
      <el-option
          v-for="item in sexs"
          :key="item.value"
          :label="item.label"
          :value="item.value">
      </el-option>
    </el-select>
    <el-button type="primary" style="margin-left: 10px"
               @click="loadPost">查询</el-button>
    <el-button type="success" @click="resetParam">重置</el-button>
    <el-button type="primary" style="margin-left: 10px"
               @click="add">新增</el-button>
  </div>
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

      <template slot-scope="scope">
      <el-button size="small" type="primary" @click="update(scope.row)" style="margin-right: 10px">编辑</el-button>
        <el-popover
            placement="top"
            width="160"
            >
          <p>确定删除吗？</p>
          <div style="text-align: right; margin: 0">
            <el-button type="primary" size="mini" @click="del(scope.row.id)">确定</el-button>
          </div>
          <el-button slot="reference" size="small" type="danger" >删除</el-button>
        </el-popover>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[5,10,15,20]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="this.total">
  </el-pagination>
  <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
    <el-form :rules="rules" ref="form" :model="form" label-width="80px">
        <el-form-item prop="no" label="账号：">
          <el-col :span="15">
            <el-input v-model="form.no"></el-input>
          </el-col>
        </el-form-item>
      <el-form-item prop="password" label="密码：">
        <el-col :span="15">
          <el-input v-model="form.password"></el-input>
        </el-col>
      </el-form-item>
        <el-form-item prop="name" label="姓名：">
                  <el-col :span="15">
                    <el-input v-model="form.name"></el-input>
                  </el-col>
                </el-form-item>
        <el-form-item prop="age" label="年龄：">
                  <el-col :span="15">
                    <el-input v-model="form.age"></el-input>
                  </el-col>
                </el-form-item>
      <el-form-item prop="sex" label="性别：">
        <el-radio-group v-model="form.sex">
          <el-radio label="1">男</el-radio>
          <el-radio label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
        <el-form-item prop="phone" label="电话：">
                  <el-col :span="15">
                    <el-input v-model="form.phone"></el-input>
                  </el-col>
                </el-form-item>


    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "Main",
  data() {
    let checkAge = (rule, value, callback) => {
      if(value>150){
        callback(new Error( '年龄输入过大' ));
      }else{
        callback();
      }
    };

    let checkDuplicate = (rule, value, callback) => {
      if (this.form.id) {
        return callback();
      }
      this.$axios.get(this.$HttpUrl + "/user/findByNo?no=" + this.form.no).then(res=>res.data).then(res => {
        if (res.code != 200) {
          callback()
        } else {
          callback(new Error('账号已经存在'));
        }
      })
    };

              return {
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      name: '',
      sex: '',
      sexs: [
        {
          value: '1',
          label: '男'
        }, {
          value: '0',
          label: '女'
        }
      ],
      centerDialogVisible: false,
      form: {
        id:'',
        no: '',
        name: "",
        password: '',
        age: '',
        phone: '',
        sex: '0',
        roleId: '2'
      },
      rules: {
        no: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'},
          {validator: checkDuplicate, trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'},
          {min: 1, max: 3, message: '长度在 1到3个位', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,3}$/, message: ' 年龄必须为正整数字', trigger: "blur"},
          {validator: checkAge, trigger: 'blur'}
        ],
        phone: [
          {required: true, message: " 手机号不能为空", trigger: "blur"},
          {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur"}
        ]
      }
    }
  },
  methods:{
    doSave() {
      this.$axios.post(this.$HttpUrl + '/user/save', this.form).then(res => res.data).then(res=>{
        console.log(res)
        if(res.code === 200){
          this.centerDialogVisible=false
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.loadPost()
        }else {
          this.$message.error('操作失败');
        }
      })
    },

    doUpdate() {
      this.$axios.post(this.$HttpUrl + '/user/update', this.form).then(res => res.data).then(res=>{
        console.log(res)
        if(res.code === 200){
          this.centerDialogVisible=false
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.loadPost()
        }else {
          this.$message.error('操作失败');
        }
      })
    },

    del(id){
      this.$axios.get(this.$HttpUrl + '/user/del?id=' + id).then(res => res.data).then(res=>{
        console.log(res)
        if(res.code === 200){
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.loadPost()
        }else {
          this.$message.error('操作失败');
        }
      })
    },
    update(row){
      // console.log(row)
      this.centerDialogVisible = true

      this.$nextTick(()=>{
        this.form.id = row.id
        this.form.no = row.no
        this.form.name = row.name
        this.form.password = ''
        //注意，这里的年龄和性别都需要加一个空字符串转换为string类型，否则前台不会显示数据
        this.form.age = row.age + ''
        this.form.phone = row.phone
        this.form.sex = row.sex + ''
        this.form.roleId = row.roleId
      })
    },

    resetForm() {
      this.$refs.form.resetFields()
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if(this.form.id){
            this.doUpdate()
          }else {
            this.doSave()
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    add() {
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        this.resetForm()
      })
    },
    resetParam() {
      this.name = '';
      this.sex = '';
    },
    handleSizeChange(val) {
      this.pageSize = val
      //防止出现未知的bug
      this.pageNum = 1
      this.loadPost()
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.loadPost()
      console.log(`当前页: ${val}`);
    },
    loadGet(){
      this.$axios.get(this.$HttpUrl + '/user/list').then(res => res.data).then(res=>{
        console.log(res)
      })
    },
    loadPost(){
      this.$axios.post(this.$HttpUrl + '/user/listPageC1',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          name:this.name,
          sex:this.sex
        }
      }).then(res => res.data).then(res=>{
        console.log(res)
        if(res.code === 200){
          this.tableData=res.data
          this.total = res.total
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