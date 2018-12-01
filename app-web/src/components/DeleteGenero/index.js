import React, { Component, Fragment } from "react";
import { remover } from "../../services/genero-servico";
import "./styles.css";

class DeleteGenero extends Component {
  constructor(props) {
    super(props);

    this.state = {
      disabledButton: false,
      error: false,
      alert: ""
    };
  }

  handleCreateGenero = async e => {
    e.preventDefault();
    this.setState({ disabledButton: true });

    const { id } = this.props;

    try {
      await remover(id);

      window.$("#modalDeleteGenero").modal("hide");
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
  };

  render() {
    const { error, alert, disabledButton } = this.state;

    return (
      <Fragment>
        <button
          type="button"
          className="btn btn-outline-danger ml-2"
          data-toggle="modal"
          data-target="#modalDeleteGenero"
        >
          <i class="fa fa-trash" /> Deletar
        </button>

        <div
          className="modal fade"
          id="modalDeleteGenero"
          tabIndex="-1"
          role="dialog"
          aria-labelledby="modalLabel"
          aria-hidden="false"
        >
          <div className="modal-dialog " role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title" id="modalLabel">
                  Deletar Gênero
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
                  <p>Você tem certezar que quer deletar?</p>
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
                      "Sim"
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

export default DeleteGenero;
