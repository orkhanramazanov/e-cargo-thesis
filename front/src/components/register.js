import { Button } from "react-bootstrap";
import React from "react";
import Header from "./header";
import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";

class Register extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      firstname: "",
      surname: "",
      phone: "",
      country: "",
      email: "",
      password: "",
      retypepassword: "",
      terms: false,
      userType: "Client",
      disableButton: false,
      countryDb: [],
      options: [],
    };
  }

  componentDidMount() {
    this.fetchAllCountriesInDB();
  }

  handleTerms = (e) => {
    e.preventDefault();
    this.setState({
      terms: e.target.checked,
    });
  };

  checkpassword = (password) => {
    var check = false;
    if (!password.match(/[a-z]+/)) {
      toast("Please include lower case character in password", {
        type: "error",
      });
      check = true;
    }
    if (!password.match(/[A-Z]+/)) {
      toast("Please include upper case character in password", {
        type: "error",
      });
      check = true;
    }
    if (!password.match(/[0-9]+/)) {
      toast("Please include numeric charcter in password", {
        type: "error",
      });
      check = true;
    }
    if (!password.match(/[$@#&!]+/)) {
      toast("Please include special character in password ", {
        type: "error",
      });
      check = true;
    }

    if (password.length < 6) {
      toast("Password length should be > 6 ", {
        type: "error",
      });
      check = true;
    }

    if (password.length > 20) {
      toast("Password length should be <= 20", {
        type: "error",
      });
      check = true;
    }

    return check;
  };

  fetchAllCountriesInDB = async () => {
    const response = await axios.get(
      "http://localhost:8080/auth/country/getAll"
    );

    this.setState({
      countryDb: response.data,
    });
    const ss = [];
    this.state.countryDb.map((country) => {
      const s = (
        <option key={country.id} value={country.name}>
          {country.name}
        </option>
      );

      ss.push(s);
    });

    this.setState({
      options: ss,
    });
  };

  handleForm = (e) => {
    e.preventDefault();
    if (e.target.name === "phone") {
      if (e.target.value <= 0) {
        toast("Phone Number can't be negative", {
          type: "error",
        });
        this.setState({
          [e.target.name]: 0,
        });
      } else {
        this.setState({
          [e.target.name]: e.target.value,
        });
      }
    } else {
      this.setState({
        [e.target.name]: e.target.value,
      });
    }
  };

  submitForm = async (e) => {
    e.preventDefault();
    if (
      this.state.email === "" ||
      this.state.password === "" ||
      this.state.phone === 0 ||
      this.state.country === "" ||
      this.state.firstname === "" ||
      this.state.surname === ""
    ) {
      toast("Please fill all fields", {
        type: "error",
      });
    } else if (!this.state.terms) {
      toast("Please accept terms and conditions", {
        type: "error",
      });
    } else if (this.state.password !== this.state.retypepassword) {
      toast("Passwords do not match", {
        type: "error",
      });
    } else {
      if (!this.checkpassword(this.state.password)) {
        const body = {
          firstname: this.state.firstname,
          surname: this.state.surname,
          country: this.state.country,
          userType: this.state.userType,
          email: this.state.email,
          phone: this.state.phone,
          username: this.state.email,
          password: this.state.password,
        };
        this.setState({
          disableButton: true,
        });
        try {
          const response = await axios.post(
            "http://localhost:8080/signup",
            body
          );
          if (response) {
            if (response.status === 200) {
              toast("Registered Successfully", {
                type: "success",
              });
            }
          } else {
            toast("Email is already in use", {
              type: "error",
            });
          }
          this.setState({
            disableButton: false,
          });
        } catch (err) {
          toast("Email is already in use", {
            type: "error",
          });
          this.setState({
            disableButton: false,
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
                <form className="form-horizontal">
                  <div className="form-group">
                    <div className="col-lg-4">
                      <h4>Create a new account</h4>
                      <label>
                        <b>Email</b>
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        placeholder="Email"
                        name="email"
                        aria-label="email"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>
                  <div className="input-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Password</b>
                      </label>

                      <input
                        type="password"
                        className="form-control input-group-lg"
                        placeholder="Password"
                        name="password"
                        aria-label="password"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4">
                      <label>
                        <b>Retype Password</b>
                      </label>

                      <input
                        type="password"
                        className="form-control form-control input-group-lg"
                        placeholder="Password"
                        name="retypepassword"
                        aria-label="password"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>
                  <br />

                  <div className="input-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Surname</b>
                      </label>

                      <input
                        type="text"
                        className="form-control input-group-lg"
                        placeholder="surname"
                        aria-label="surname"
                        name="surname"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                    <div className="col-lg-4">
                      <label>
                        <b>Firstname</b>
                      </label>

                      <input
                        type="text"
                        className="form-control form-control input-group-lg"
                        placeholder="firstname"
                        aria-label="firstname"
                        name="firstname"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>
                  <br />
                  <div className="form-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Phone Number</b>
                      </label>

                      <input
                        type="number"
                        className="form-control form-control input-group-lg"
                        placeholder="Phone"
                        name="phone"
                        aria-label="phone"
                        aria-describedby="basic-addon"
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>

                  <div className="form-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Country of residence</b>
                      </label>
                      <br />
                      <select
                        style={{
                          borderRadius: "2px",
                          padding: "10px",
                          background: "white",
                        }}
                        name="country"
                        onChange={this.handleForm}
                      >
                        <option value="">Select Country</option>
                        {this.state.options}
                      </select>
                    </div>
                  </div>

                  <div className="form-group">
                    <div className="col-lg-6">
                      <div className="input-group">
                        <input
                          type="checkbox"
                          name="terms"
                          onChange={this.handleTerms}
                          defaultChecked={this.state.terms}
                        />
                        <p style={{ paddingLeft: "5px" }}>
                          {" "}
                          I accept the terms and conditions
                        </p>
                      </div>
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
                      Register
                    </Button>
                  </div>
                </form>
              </div>
              <div
                className="col-lg-12"
                style={{ background: "#F0F0F0", padding: "15px" }}
              >
                <p style={{ paddingLeft: "15px" }}>
                  Already have an account?{" "}
                  <Link to="/login">
                    <u>Login here</u>
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

export default Register;
