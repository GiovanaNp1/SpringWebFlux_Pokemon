<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Pesquise por Nome"
          v-model="nome"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="BuscarNome"
          >
            Buscar
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Pokemon Lista</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(pokemon, index) in pokemons"
          :key="index"
          @click="setActivePokemon(pokemon, index)"
        >
          {{ pokemon.nome }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllPokemons">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentPokemon">
        <h4>Pokemon</h4>
        <div>
          <label><strong>Nome:</strong></label> {{ currentPokemon.nome }}
        </div>
        <div>
          <label><strong>Categoria:</strong></label> {{ currentPokemon.categoria }}
        </div>
        <div>
          <label><strong>Habilidade:</strong></label> {{ currentPokemon.habilidade }}
        </div>
        <div>
          <label><strong>Peso:</strong></label> {{ currentPokemon.peso }}
        </div>
        <div>
          <label><strong>Status:</strong></label> {{ currentPokemon.published ? "Published" : "Pending" }}
        </div>

        <a class="badge badge-warning"
          :href="'/pokemon/' + pokemon.id"
        >
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Tutorial...</p>
      </div>
    </div>
  </div>
</template>

<script>
import GerenciadorPokemon from "../provider/GerenciadorPokemon";

export default {
  name: "pokemon",
  data() {
    return {
      pokemon: [],
      currentPokemon: null,
      currentIndex: -1,
      name: ""
    };
  },
  methods: {
    retrievePokemon() {
      pokemon.getAll()
        .then(response => {
          this.pokemon = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrievePokemon();
      this.currentPokemon = null;
      this.currentIndex = -1;
    },

    setActivePokemon(tutorial, index) {
      this.currentPokemon = tutorial;
      this.currentIndex = index;
    },

    removeAllPokemons() {
      GerenciadorPokemon.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // searchTitle() {
    //   TutorialDataService.findByTitle(this.title)
    //     .then(response => {
    //       this.tutorials = response.data;
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // }
  },
  mounted() {
    this.retrievePokemon();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
