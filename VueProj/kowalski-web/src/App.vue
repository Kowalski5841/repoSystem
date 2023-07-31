<template>
  <div id="app">
    <index></index>
  </div>
</template>

<script>

import Index from "@/components";
export default {
  name: 'App',
  components: {
    Index
  }
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
