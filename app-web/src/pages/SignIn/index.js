import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { auth } from "../../services/usuario-service";

class SignIn extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      senha: "",
      disabledButton: false,
      error: false,
      alert: ""
    };
  }

  handleLogin = async e => {
    e.preventDefault();

    this.setState({ disabledButton: true });

    const { email, senha } = this.state;

    if (!email || !senha) {
      this.setState({
        error: true,
        alert: "Todos os campos são obrigátorios",
        disabledButton: false
      });
    } else {
      try {
        await auth({ email, senha });

        return this.props.history.push("/dashboard");
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
    const { disabledButton, error, alert } = this.state;

    return (
      <div className="container-fluid">
        <div className="row justify-content-center align-items-center center">
          <div className="col-8 col-sm-8 col-md-8 col-lg-6 col-xl-4">
            <div className="card">
              <form onSubmit={this.handleLogin}>
                <h2 className="card-header text-center">Organize Series</h2>
                <div className="card-body">
                  <h4 className="card-title text-center">Entrar</h4>
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
                      "Entrar"
                    ) : (
                      <i className="fa fa-spinner fa-pulse" />
                    )}
                  </button>
                </div>
              </form>
              <Link to="/signup" className="text-center">
                Criar conta
              </Link>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(SignIn);
