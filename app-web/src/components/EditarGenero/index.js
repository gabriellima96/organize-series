import React, { Component, Fragment } from "react";
import { atualizar } from "../../services/genero-servico";
import "./styles.css";

class EditarGenero extends Component {
  constructor(props) {
    super(props);

    const { genero } = this.props;

    this.state = {
      nome: genero.nome,
      id: genero.id,
      disabledButton: false,
      error: false,
      alert: ""
    };
  }

  handleCreateGenero = async e => {
    e.preventDefault();
    this.setState({ disabledButton: true });

    const { nome, id } = this.state;

    if (!nome) {
      this.setState({
        error: true,
        alert: "O campo é obrigátorio",
        disabledButton: false
      });
    } else {
      try {
        await atualizar({ nome, id });

        this.setState({
          error: false,
          alert: "Gênero atualizado com sucesso.",
          nome: ""
        });
      } catch (error) {
        const response = error.response;
        if (response) {
          this.setState({ error: true, alert: response.data.mensagem });
        } else {
          this.setState({ error: true, alert: "Ocorreu um error inesperado" });
        }
      } finally {
        this.setState({ disabledButton: false });

        setTimeout(() => {
          this.setState({ alert: "" });
        }, 5000);
      }
    }
  };

  render() {
    const { error, alert, disabledButton, nome } = this.state;

    return (
      <Fragment>
        <button
          type="button"
          className="btn btn-outline-warning ml-2"
          data-toggle="modal"
          data-target="#modalEditGenero"
        >
          <i class="fa fa-edit" /> Editar
        </button>

        <div
          className="modal fade"
          id="modalEditGenero"
          tabIndex="-1"
          role="dialog"
          aria-labelledby="modalLabel"
          aria-hidden="false"
        >
          <div className="modal-dialog " role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="modalLabel">
                  ATUALIZAR GÊNERO
                </h5>
                <button
                  type="button"
                  className="close"
                  data-dismiss="modal"
                  aria-label="Close"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <form onSubmit={this.handleCreateGenero}>
                <div className="modal-body">
                  {alert && (
                    <div
                      className={`alert ${
                        error ? "alert-danger" : "alert-success"
                      }`}
                      role="alert"
                    >
                      {alert}
                    </div>
                  )}
                  <div className="form-group">
                    <label htmlFor="name">Nome do Gênero</label>
                    <input
                      type="text"
                      className="form-control"
                      id="name"
                      placeholder="Terror, Crime, Romance..."
                      value={nome}
                      onChange={e => this.setState({ nome: e.target.value })}
                    />
                  </div>
                </div>
                <div className="modal-footer">
                  <button
                    type="button"
                    className="btn btn-secondary"
                    data-dismiss="modal"
                  >
                    Cancelar
                  </button>
                  <button
                    type="submit"
                    className="btn btn-primary btn-custom"
                    disabled={disabledButton}
                  >
                    {!disabledButton ? (
                      "Salvar"
                    ) : (
                      <i className="fa fa-spinner fa-pulse" />
                    )}
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </Fragment>
    );
  }
}

export default EditarGenero;
