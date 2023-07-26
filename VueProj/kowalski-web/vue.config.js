const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false //不去检查语法，否则非驼峰式命名的文件会报错
})
