<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  a{
    text-decoration: none;
    outline: none;
    color: #242921;
  }
  input{
    border: 0;
    outline: none;
  }
  input:-webkit-autofill{ box-shadow: 0 0 0px 1000px transparent inset !important;}
  span{
    border: none;
    outline: none;
  }
</style>
