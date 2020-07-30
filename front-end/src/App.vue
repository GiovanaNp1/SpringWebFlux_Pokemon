<template>
  <div id="app">
    <nav class="navbar navbar-dark bg-danger">
      <a class="navbar-brand" href="#">
        <img src="https://cdn140.picsart.com/272496197016211.png?type=webp&to=min&r=640" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
        Pokedex
      </a>
      <br><br>
    </nav>
    <button type="button" class="btn" @click="showModal">
      Open Modal!
    </button>
    <modalAdd v-show="isModalVisible" @close="closeModal"/>
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import modalAdd from './components/Modal';

export default {
  name: 'App',
  components: {
    HelloWorld,
    modalAdd
  },
    data () {
      return {
        isModalVisible: false,
      };
    },
    methods: {
      showModal() {
        this.isModalVisible = true;
      },
      closeModal() {
        this.isModalVisible = false;
      }
    },
}
</script>
<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
