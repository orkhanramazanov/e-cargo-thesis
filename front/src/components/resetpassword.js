import { Button } from "react-bootstrap";
import React from "react";
import Header from "./header";
import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";

class ResetPassword extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      disableButton: false,
    };
  }

  handleForm = (e) => {
    e.preventDefault();
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  submitForm = async (e) => {
    e.preventDefault();
    if (this.state.email === "") {
      toast("Please fill all fields", {
        type: "error",
      });
    } else {
      this.setState({
        disableButton: true,
      });
      try {
        const response = await axios.post(
          "http://localhost:8080/auth/resetPassword",
          {
            email: this.state.email,
          }
        );
        if (response.status === 200) {
          toast("Email sent successfully", {
            type: "success",
          });
        } else {
          toast("An error occured", {
            type: "error",
          });
        }
        this.setState({
          disableButton: false,
        });
      } catch (err) {
        toast(
          "If you are registered with us , you will recieve an email soon.",
          {
            type: "success",
          }
        );
        this.setState({
          disableButton: false,
        });
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
                    <h4>Reset Password</h4>
                    <p>
                      Please enter your email , we will send you a temporary
                      password
                    </p>
                    <label>
                      <b>Email</b>
                    </label>

                    <div className="input-group-prepend">
                      <input
                        type="text"
                        name="email"
                        className="form-control"
                        placeholder="Email"
                        aria-label="email"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>

                  <div className="form-group">
                    <Button
                      onClick={this.submitForm}
                      disabled={this.state.disableButton}
                      style={{
                        background: "red",
                        border: "none",
                        marginLeft: "20px",
                        marginTop: "30px",
                      }}
                    >
                      Reset Password
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
              </div>
            </div>
          </div>
          <ToastContainer />
        </div>
      </React.Fragment>
    );
  }
}

export default ResetPassword;
