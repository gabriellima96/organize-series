import React, { Fragment, Component } from "react";
import NavBar from "../../components/NavBar";
import ModalCreateGeneros from "../../components/ModalCreateGeneros";
import DeleteGenero from "../../components/DeleteGenero";
import EditarGenero from "../../components/EditarGenero";
import { buscarTodos } from "../../services/genero-servico";

class Generos extends Component {
  constructor(props) {
    super(props);

    this.state = {
      generos: [],
      error: false,
      alert: ""
    };
  }

  async componentDidMount() {
    try {
      const response = await buscarTodos();

      this.setState({ generos: response.data });
    } catch (error) {
      const response = error.response;
      if (response) {
        this.setState({ error: true, alert: response.data.mensagem });
      } else {
        this.setState({
          error: true,
          alert: "Ocorreu um error inesperado ao buscar os gêneros"
        });
      }
    }
  }

  render() {
    const { error, alert, generos } = this.state;
    return (
      <Fragment>
        <NavBar active="generos" />
        <div className="jumbotron">
          <div className="container">
            <div className="row">
              <div className="col-2">
                <h1 className="display-5">Gêneros</h1>
              </div>
              <div className="col-3">
                <ModalCreateGeneros />
              </div>
              {alert && (
                <div className="col-5">
                  <div
                    className={`alert ${
                      error ? "alert-danger" : "alert-success"
                    }`}
                    role="alert"
                  >
                    {alert}
                  </div>
                </div>
              )}
            </div>
            <hr className="my-4" />
            <div className="row">
              <table className="table">
                <thead className="thead-dark">
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Ações</th>
                  </tr>
                </thead>
                <tbody>
                  {generos.length > 0 ? (
                    generos.map(genero => (
                      <tr>
                        <th scope="row">{genero.id}</th>
                        <td>{genero.nome}</td>
                        <td>
                          <EditarGenero genero={genero} />
                          <DeleteGenero id={genero.id} />
                        </td>
                      </tr>
                    ))
                  ) : (
                    <p>Sem dados.</p>
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </Fragment>
    );
  }
}

export default Generos;
