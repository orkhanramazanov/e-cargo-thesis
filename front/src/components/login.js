import { Button } from "react-bootstrap";
import React from "react";
import Header from "./header";
import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import history from "./browserhistory";
import axios from "axios";
class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
    };
  }

  componentDidMount() {
    sessionStorage.clear();
  }

  handleForm = (e) => {
    e.preventDefault();
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  submitForm = async (e) => {
    e.preventDefault();
    if (this.state.email === "" || this.state.password === "") {
      toast("Please fill all fields", {
        type: "error",
      });
    } else {
      const body = {
        email: this.state.email,
        password: this.state.password,
      };
      try {
        const response = await axios.post("http://localhost:8080/signin", body);
        if (response.status === 200) {
       
          if(response.data.authorities[0].authority !== "ROLE_CLIENT")
          {
            toast("Bad Credentials", {
              type: "error",
            });
          }
          else 
          {
            sessionStorage.setItem("authenticated", true);
            sessionStorage.setItem("id", response.data.id);
            sessionStorage.setItem("fname", response.data.firstName);
            sessionStorage.setItem("email", response.data.username);
            sessionStorage.setItem("token", response.data.accessToken);
            sessionStorage.setItem(
              "role",
              response.data.authorities[0].authority
            );
            history.push("/landingpage");
          }

      
        } else {
          toast("Bad Credentials", {
            type: "error",
          });
        }
      } catch (err) {
        if (err.response.status === 423) {
          toast(err.response.data.message, {
            type: "error",
          });
        } else {
          toast("Bad Credentials", {
            type: "error",
          });
        }
      }
    }
  };

  render() {
    return (
      <React.Fragment>
        <Header></Header>
        <div
          style={{
            background: "#F5F5F5",
            width: "100%",
            height: "100vh",
            minHeight: "100vh",
          }}
        >
          <br />
          <div
            style={{
              background: "white",
              marginTop: "20px",
              borderRadius: "5px",
            }}
            className="container"
          >
            <div className="row justify-content-center">
              <div className="col-lg-12" style={{ padding: "10px" }}>
                <form>
                  <div className="form-group col-lg-4">
                    <h4>Login</h4>
                    <label>
                      <b>Email</b>
                    </label>

                    <div className="input-group-prepend">
                      <input
                        onChange={this.handleForm}
                        type="text"
                        name="email"
                        className="form-control"
                        placeholder="Email"
                        aria-label="email"
                        aria-describedby="basic-addon"
                      />
                    </div>
                  </div>

                  <div className="form-group col-lg-4">
                    <label>
                      <b>Password</b>
                    </label>

                    <div className="input-group-prepend">
                      <input
                        type="password"
                        onChange={this.handleForm}
                        className="form-control"
                        placeholder="Password"
                        name="password"
                        aria-label="password"
                        aria-describedby="basic-addon"
                      />
                    </div>
                  </div>
                  <div className="form-group">
                    <Button
                      style={{
                        background: "red",
                        border: "none",
                        marginLeft: "20px",
                        marginTop: "30px",
                      }}
                      onClick={this.submitForm}
                    >
                      Login
                    </Button>
                  </div>
                </form>
              </div>
              <div
                className="col-lg-12"
                style={{ background: "#F0F0F0", padding: "15px" }}
              >
                <p style={{ paddingLeft: "15px" }}>
                  No account yet?{" "}
                  <Link to="/register">
                    <u>Get yours now</u>
                  </Link>
                </p>
                <p style={{ paddingLeft: "15px" }}>
                  Forgot password?{" "}
                  <Link to="/forgotpassword">
                    <u>Get new one here</u>
                  </Link>
                </p>
              </div>
            </div>
          </div>
          <ToastContainer />
        </div>
      </React.Fragment>
    );
  }
}

export default Login;
