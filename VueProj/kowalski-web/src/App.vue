<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {

  },
  watch:{
    '$store.state.menu':{
      handler(val, old) {
        if(!old && this.user && this.user.no){
          this.$store.commit("setRouter", val)
        }
      },
      immediate: true
    }
  },
}
</script>

<style>
@import "./assets/font.css";
 *{
   font-family: "SmileySans";
 }
#app {
 height: 100%;

}
</style>
