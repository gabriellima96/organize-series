import React, { Component } from "react";
import { Link } from "react-router-dom";
import { salvar } from "../../services/usuario-service";

import "./styles.css";

class SignUp extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      senha: "",
      nome: "",
      disabledButton: false,
      error: false,
      alert: ""
    };
  }

  handleCreateAccount = async e => {
    e.preventDefault();

    this.setState({ disabledButton: true });

    const { nome, email, senha } = this.state;

    if (!nome || !email || !senha) {
      this.setState({
        error: true,
        alert: "Todos os campos são obrigátorios",
        disabledButton: false
      });
    } else {
      try {
        await salvar({ nome, email, senha });

        this.setState({ error: false, alert: "Conta criada com sucesso" });
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
    const { disabledButton, alert, error } = this.state;
    return (
      <div className="container-fluid">
        <div className="row justify-content-center align-items-center center">
          <div className="col-8 col-sm-8 col-md-8 col-lg-6 col-xl-4">
            <form onSubmit={this.handleCreateAccount}>
              <div className="card">
                <h2 className="card-header text-center">Organize Series</h2>
                <div className="card-body">
                  <h4 className="card-title text-center">Criar conta</h4>
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
                  <div className="form-group input-group-lg">
                    <input
                      type="name"
                      id="name"
                      className="form-control"
                      placeholder="Nome completo"
                      onChange={e => this.setState({ nome: e.target.value })}
                    />
                  </div>
                  <div className="form-group input-group-lg">
                    <input
                      type="email"
                      id="email"
                      className="form-control"
                      placeholder="Endereço de e-mail"
                      onChange={e => this.setState({ email: e.target.value })}
                    />
                  </div>
                  <div className="form-group input-group-lg">
                    <input
                      type="password"
                      id="password"
                      className="form-control"
                      placeholder="Senha"
                      onChange={e => this.setState({ senha: e.target.value })}
                    />
                  </div>

                  <button
                    disabled={disabledButton}
                    type="submit"
                    className="btn btn-primary btn-block btn-lg"
                  >
                    {!disabledButton ? (
                      "Criar conta"
                    ) : (
                      <i className="fa fa-spinner fa-pulse" />
                    )}
                  </button>
                </div>
                <Link to="/" className="text-center">
                  Entrar
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default SignUp;
