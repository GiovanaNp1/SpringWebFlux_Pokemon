import Vue from 'vue'
import vSelect from 'vue-select'
import App from './App.vue'
import 'vue-select/dist/vue-select.css'; 

Vue.component('v-select', vSelect)
Vue.config.productionTip = false


new Vue({
  render: h => h(App),
}).$mount('#app')
