<template>
  <div class="hello">
    <h3>Meus Pokemons</h3>
    <hr>
          <div class="d-flex flex-row justify-content-around">
            <div v-for="(pokemon, index) in pokemons" :key="index">
              <div class="card" style="width: 15rem; height: 25rem; align-items: center;">
                <div class="card-body">
                  <img :src="pokemon.image" class="card-img-top">
                  <h5 class="card-title">{{pokemon.nome}}</h5>
                  <p class="card-text">{{pokemon.categoria}} - {{pokemon.peso}} - {{pokemon.habilidade}}</p>
                  <a class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
            </div>
          </div>
  </div>
</template>

<script>
import axios from 'axios';
import Alert from './Alert.vue';


export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
   return {
      pokemons: [],
      addPokemonForm: {
        nome: '',
        categoria: '',
        habilidade: '',
        image: '',
        peso: '',
      },
       components: {
        alert: Alert,
      },
      message: '',
      showMessage: false,
   }
  },
  methods: {
    getPokemons() {
      const path = 'http://localhost:8080/pokemon';
      axios.get(path, {
        headers: {
                'Access-Control-Allow-Origin': 'http://localhost:8080/pokemon',
                'Accept':'*'
        }
      })
        .then((res) => {
          console.log(res.data)
          this.pokemons = res.data;
          console.log(this.pokemons)
          return this.pokemons
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  beforeMount(){
    this.getPokemons()
 },
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
</style>
