<template>
  <div class="modal-backdrop">
    <div class="modalAdd">
      <header class="modal-header">
        <slot name="header">
          <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
          <button
            type="button"
            class="btn-close"
            @click="close"
          >
            x
          </button>
        </slot>
      </header>
      <section class="modal-body">
        <slot name="body">
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
        </slot>
       </section>
       <footer class="modal-footer">
          <slot name="footer">
            I'm the default footer!

            <button
              type="button"
              class="btn-green"
              @click="close"
            >
              Close me!
          </button>
        </slot>
      </footer>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import HelloWorld from './HelloWorld.vue'


  export default {
    name: 'modalAdd',
    data() {
   return {
      addPokemonForm: {
        nome: '',
        categoria: '',
        habilidade: '',
        image: '',
        peso: '',
      },
       components: {
        HelloWorld
      },
   }
  },
  methods: {
       close() {
        HelloWorld.methods.getPokemons()
        this.$emit('close');
      },
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
    },
     addPokemon(payload) {
      const path = 'http://localhost:8080/pokemon';
      axios.post(path, payload)
        .then(() => {
          this.close();
        })
        .catch((error) => {
          // eslint-disable-next-line
          console.log(error);
          this.close();
        });
    },
  },
  };
</script>

<style>
  .modal-backdrop {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .modalAdd {
    background: #FFFFFF;
    box-shadow: 2px 2px 20px 1px;
    overflow-x: auto;
    display: flex;
    flex-direction: column;
    width: 40%;
    height: auto;
  }

  .modal-header {
    border-bottom: 1px solid #eeeeee;
    color: #4AAE9B;
    justify-content: space-between;
  }

  .modal-footer {
    border-top: 1px solid #eeeeee;
    justify-content: flex-end;
  }

  .modal-body {
    position: relative;
    /* padding: 20px 10px; */
  }

  .btn-close {
    border: none;
    font-size: 20px;
    /* padding: 20px; */
    cursor: pointer;
    font-weight: bold;
    color: #4AAE9B;
    background: transparent;
  }

  .btn-green {
    color: white;
    background: #4AAE9B;
    border: 1px solid #4AAE9B;
    border-radius: 2px;
  }
</style>