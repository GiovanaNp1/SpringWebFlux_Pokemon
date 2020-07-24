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
          <div class="col-6">
            <h3>Adicionar novo pokemon</h3>
            <alert :message=message v-if="showMessage"></alert>
            <br><br>
            <form class="text-left" @submit="onSubmit">
            <div class="form-group ">
              <label for="inputName">Nome</label>
              <input type="text" v-model="addPokemonForm.nome" class="form-control" id="inputName" aria-describedby="Nome-Pokeon" placeholder="Ex: Pikachu, Chamander">
            </div>
            <div class="form-group">
              <label for="inputCategoria">Categoria</label>
              <input type="text" v-model="addPokemonForm.categoria" class="form-control" id="inputCategoria" aria-describedby="categoria-pokemon" placeholder="Ex: grass, eletric">
            </div>
            <div class="form-group">
              <label for="inputPeso">Peso</label>
              <input type="number"  v-model="addPokemonForm.peso" class="form-control" id="inputPeso" aria-describedby="Peso-pokemon" placeholder="Ex: 90.5, 6.0">
            </div>
            <div class="form-group">
              <label for="inputHabilidade">Habilidade</label>
              <input type="text" class="form-control"  v-model="addPokemonForm.habilidade" id="inputHabilidade" aria-describedby="habiidade-Pokemo" placeholder="Ex: Lightning Rod">
            </div>
            <div class="form-group">
              <label for="inputImage">Image</label>
              <input type="text" class="form-control" id="inputImage"  v-model="addPokemonForm.image" aria-describedby="Image-Pokemo" placeholder="Link">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
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
      onSubmit(evt) {
      evt.preventDefault();
      const payload = {
        nome: this.addPokemonForm.nome,
        categoria:this.addPokemonForm.categoria,
        image:this.addPokemonForm.image,
        habilidade:this.addPokemonForm.habilidade,
        peso:this.addPokemonForm.peso
      };
      this.addPokemon(payload);
      this.initForm();
    },
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
        })
        .catch((error) => {
          console.error(error);
        });
    },
     addPokemon(payload) {
      const path = 'http://localhost:8080/pokemon';
      axios.post(path, payload)
        .then(() => {
          this.getPokemons();
          this.message = 'Pokemon Adicionado!!!';
          this.showMessage = true;
        })
        .catch((error) => {
          // eslint-disable-next-line
          console.log(error);
          this.getPokemons();
        });
    },
  },
  initForm() {
      this.addPokemonForm.nome = '';
      this.addPokemonForm.categoria = '';
      this.addPokemonForm.habilidade = '';
      this.addPokemonForm.image = '';
      this.addPokemonForm.peso = '';
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
a {
  color: #42b983;
}
</style>
