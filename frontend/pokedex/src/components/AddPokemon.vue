<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="nome">Nome</label>
        <input
          type="text"
          class="form-control"
          id="nome"
          required
          v-model="pokemon.nome"
          name="nome"
        />
      </div>

      <div class="form-group">
        <label for="categoria">Categoria</label>
        <input
          class="form-control"
          id="categoria"
          type="text"
          required
          v-model="pokemon.categoria"
          name="categoria"
        />
      </div>

       <div class="form-group">
        <label for="habilidade">Habilidade</label>
        <input
          class="form-control"
          id="habilidade"
          type="text"
          required
          v-model="pokemon.habilidade"
          name="habilidade"
        />
      </div>

            <div class="form-group">
        <label for="peso">Peso</label>
        <input
          class="form-control"
          id="peso"
          type="number"
          required
          v-model="pokemon.peso"
          name="peso"
        />
      </div>

      <button @click="savePokemon" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newPokemon">Add</button>
    </div>
  </div>
</template>

<script>
import GerenciadorPokemon from "../provider/GerenciadorPokemon";

export default {
  name: "add",
  data() {
    return {
      tutorial: {
        id: null,
        nome: "",
        categoria: "",
        habilidade: "",
        peso: "",
        published: false
      },
      submitted: false
    };
  },
  methods: {
    saveTutorial() {
      var data = {
        nome: this.pokemon.nome,
        categoria: this.pokemon.categoria,
        habilidade: this.pokemon.habilidade,
        peso: this.pokemon.peso,
      };

      GerenciadorPokemon.create(data)
        .then(response => {
          this.pokemon.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newPokemon() {
      this.submitted = false;
      this.pokemon = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>