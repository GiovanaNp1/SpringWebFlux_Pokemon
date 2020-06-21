import http from "../http-common";

class GerenciadorPokemon {
  getAll() {
    return http.get("/pokemon");
  } //pegar todos

  get(id) {
    return http.get(`/pokemon/${id}`);
  } //pegar unico pokemon

  create(data) {
    return http.post("/pokemon", data);
  } //criar um pokemon

  update(id, data) {
    return http.put(`/pokemon/${id}`, data);
  } //update pokemon

  delete(id) {
    return http.delete(`/pokemon/${id}`);
  } //deletar um pokemon

  deleteAll() {
    return http.delete(`/pokemon`);
  } //deletar todos

  // findByTitle(title) {
  //   return http.get(`/pokemon?title=${title}`);
  // } //procurar um pokemon por nome (precisa de endpoint no back)
}

export default new GerenciadorPokemon();