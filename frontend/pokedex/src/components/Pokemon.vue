<template>
  <div v-if="currentPokemon" class="edit-form">
    <h4>Pokemon</h4>
    <form>
      <div class="form-group">
        <label for="nome">Nome</label>
        <input type="text" class="form-control" id="nome"
          v-model="currentPokemon.nome"
        />
      </div>
      <div class="form-group">
        <label for="categoria">Categoria</label>
        <input type="text" class="form-control" id="categoria"
          v-model="currentPokemon.categoria"
        />
      </div>
      <div class="form-group">
        <label for="habilidade">Habilidade</label>
        <input type="text" class="form-control" id="habilidade"
          v-model="currentPokemon.habilidade"
        />
      </div>
      <div class="form-group">
        <label for="peso">Peso</label>
        <input type="number" class="form-control" id="peso"
          v-model="currentPokemon.peso"
        />
      </div>

      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentPokemon.published ? "Published" : "Pending" }}
      </div>
    </form>

    <button class="badge badge-primary mr-2"
      v-if="currentPokemon.published"
      @click="updatePublished(false)"
    >
      UnPublish
    </button>
    <button v-else class="badge badge-primary mr-2"
      @click="updatePublished(true)"
    >
      Publish
    </button>

    <button class="badge badge-danger mr-2"
      @click="deletePokemon"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
      @click="updatePokemon"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Tutorial...</p>
  </div>
</template>

<script>
import GerenciadorPokemon from "../provider/GerenciadorPokemon";

export default {
  name: "pokemonid",
  data() {
    return {
      currentPokemon: null,
      message: ''
    };
  },
  methods: {
    getPokemon(id) {
      GerenciadorPokemon.get(id)
        .then(response => {
          this.currentPokemon = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    updatePublished(status) {
      var data = {
        id: this.currentPokemon.id,
        nome: this.currentPokemon.nome,
        categoria: this.currentPokemon.categoria,
        habilidade: this.currentPokemon.habilidade,
        peso: this.currentPokemon.peso,
        published: status
      };

      GerenciadorPokemon.update(this.currentPokemon.id, data)
        .then(response => {
          this.GerenciadorPokemon.published = status;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    updatePokemon() {
      GerenciadorPokemon.update(this.currentPokemon.id, this.currentPokemon)
        .then(response => {
          console.log(response.data);
          this.message = 'The pokemon was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deletePokemon() {
      GerenciadorPokemon.delete(this.currentPokemon.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "pokemon" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getTutorial(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>