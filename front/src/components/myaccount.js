import { Button } from "react-bootstrap";
import React from "react";
import Header from "./header";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSignOutAlt,
  faDatabase,
  faUser,
  faFile,
} from "@fortawesome/free-solid-svg-icons";
import { ToastContainer, toast } from "react-toastify";
import { Link } from "react-router-dom";
import axios from "../axiosinterceptor";
class MyAccount extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayPasswordForm: false,
      displayAccountForm: false,
      firstname: "",
      surname: "",
      email: "",
      currentpassword: "",
      newpassword: "",
      retypepassword: "",
      country: "",
      phone: "",
      countryDb: [],
      options: [],
    };

    this.togglePasswordForm = this.togglePasswordForm.bind(this);
    this.toggleAccountForm = this.toggleAccountForm.bind(this);
  }
  togglePasswordForm() {
    this.setState({
      displayPasswordForm: !this.state.displayPasswordForm,
    });
  }

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
        <option
          selected={this.state.country === country.name ? "selected" : ""}
          key={country.id}
          value={country.name}
        >
          {country.name}
        </option>
      );

      ss.push(s);
    });

    this.setState({
      options: ss,
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

  getUserData = async () => {
    if (sessionStorage.getItem("id")) {
      const userId = sessionStorage.getItem("id");
      const userData = await axios.get(
        `http://localhost:8080/auth/getUser/${userId}`
      );
      if (userData.data) {
        this.setState({
          firstname: userData.data.firstname,
          surname: userData.data.surname,
          email: userData.data.email,
          country: userData.data.country,
          phone: userData.data?.phone,
        });
      }
      return true;
    } else {
      return false;
    }
  };

  async componentDidMount() {
    this.getUserData();
    this.fetchAllCountriesInDB();
  }

  toggleAccountForm() {
    this.setState({
      displayAccountForm: !this.state.displayAccountForm,
    });
  }

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

  modifyAccount = async (e) => {
    e.preventDefault();

    if (
      this.state.country === "" ||
      this.state.email === "" ||
      this.state.phone === "" ||
      this.state.surname === "" ||
      this.state.firstname === ""
    ) {
      toast("Please fill all fields", {
        type: "error",
      });
    } else {
      const body = {
        email: this.state.email,
        country: this.state.country,
        phone: this.state.phone,
        surname: this.state.surname,
        firstname: this.state.firstname,
      };
      try {
        const response = await axios.post(
          `http://localhost:8080/auth/user/${sessionStorage.getItem("id")}`,
          body
        );

        if (response.status === 200) {
          toast("Profile Updated", {
            type: "success",
          });
          this.getUserData();
        } else {
          toast("Email in use", {
            type: "error",
          });
        }
      } catch (err) {
        toast(err.response.data.message, {
          type: "error",
        });
      }
    }
  };

  modifyPassword = async (e) => {
    e.preventDefault();
    if (
      this.state.currentpassword === "" ||
      this.state.newpassword === "" ||
      this.state.retypepassword === ""
    ) {
      toast("Please fill all fields", {
        type: "error",
      });
    } else if (this.state.newpassword !== this.state.retypepassword) {
      toast("New Passwords do not match", {
        type: "error",
      });
    } else {
      if (!this.checkpassword(this.state.newpassword)) {
        const body = {
          currentPassword: this.state.currentpassword,
          newPassword: this.state.newpassword,
        };
        try {
          const response = await axios.post(
            `http://localhost:8080/auth/user/updatePassword/${sessionStorage.getItem(
              "id"
            )}`,
            body
          );

          if (response.status === 200) {
            toast("Password Updated", {
              type: "success",
            });
            this.getUserData();
          } else {
            toast("Incorrect current password", {
              type: "error",
            });
          }
        } catch (err) {
          toast(err.response.data.message, {
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
            height: "110vh",
            minHeight: "110vh",
          }}
        >
          <br />
          <div className="container">
            <div className="row">
              <div
                className="col-lg-4"
                style={{
                  padding: "10px",
                  background: "white",
                  height: "fit-content",
                }}
              >
                <p style={{ padding: "10px" }}>
                  <b>{sessionStorage.getItem("fname")}'s Account</b>{" "}
                  <span>{sessionStorage.getItem("email")}</span>
                </p>

                <hr style={{ borderTop: "3px solid #991A00" }} />

                <div>
                  <div
                    style={{
                      padding: "10px",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{ borderRight: "none", marginRight: "15px" }}
                      icon={faFile}
                    />
                    <Link style={{ color: "black" }} to="/reservations">
                      My reservations
                    </Link>
                  </div>
                  <hr />
                  <div
                    style={{
                      padding: "10px",
                      borderWidth: "1px",
                      borderLeft: "7px solid #991A00 ",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{
                        borderRight: "none",
                        marginRight: "15px",
                        color: "#991A00",
                      }}
                      icon={faUser}
                    />
                    <Link style={{ color: "black" }} to="/account">
                      <b> My data</b>
                    </Link>
                  </div>
                  <hr />
                  <div
                    style={{
                      padding: "10px",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{ borderRight: "none", marginRight: "15px" }}
                      icon={faDatabase}
                    />
                    <Link style={{ color: "black" }} to="/billing">
                      {" "}
                      Billing
                    </Link>
                  </div>
                  <hr />
                  <div
                    style={{
                      padding: "10px",
                    }}
                  >
                    <FontAwesomeIcon
                      style={{ borderRight: "none", marginRight: "15px" }}
                      icon={faSignOutAlt}
                    />
                    <Link style={{ color: "black" }} to="/logout">
                      {" "}
                      Log out
                    </Link>
                  </div>
                </div>
              </div>
              <hr />
              <div
                className="col-lg-7"
                style={{
                  padding: "10px",
                  background: "white",
                  height: "fit-content",
                }}
              >
                <p style={{ padding: "10px", marginTop: "25px" }}>
                  <b>{sessionStorage.getItem("fname")}'s Account</b>{" "}
                </p>
                <hr />
                <h5 style={{ padding: "10px" }}>
                  <b>Account Details</b>
                </h5>
                <form className="form-horizontal">
                  <div className="form-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Email</b>
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        placeholder="Email"
                        aria-label="email"
                        value={this.state.email}
                        name="email"
                        aria-describedby="basic-addon"
                        disabled={!this.state.displayAccountForm}
                        onChange={this.handleForm}
                      />
                    </div>
                  </div>

                  <div className="input-group">
                    <div className="col-lg-4">
                      <label>
                        <b>Surname</b>
                      </label>

                      <input
                        type="text"
                        className="form-control input-group-lg"
                        placeholder="surname"
                        name="surname"
                        aria-label="surname"
                        value={this.state.surname}
                        aria-describedby="basic-addon"
                        disabled={!this.state.displayAccountForm}
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
                        value={this.state.firstname}
                        name="firstname"
                        aria-describedby="basic-addon"
                        disabled={!this.state.displayAccountForm}
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
                        aria-label="phone"
                        value={this.state.phone}
                        name="phone"
                        aria-describedby="basic-addon"
                        disabled={!this.state.displayAccountForm}
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
                        disabled={!this.state.displayAccountForm}
                        onChange={this.handleForm}
                      >
                        <option value="">Select Country</option>
                        {this.state.options}
                      </select>
                    </div>
                  </div>
                  <div className="col-lg-6">
                    <div className="input-group">
                      <Button
                        style={{
                          display: this.state.displayAccountForm
                            ? "none"
                            : "block",
                          background: "white",
                          color: "black",
                          border: "1px solid black",
                        }}
                        onClick={this.toggleAccountForm}
                      >
                        <b>Modify</b>
                      </Button>
                    </div>
                  </div>
                  <div
                    className="form-group"
                    style={{
                      display: this.state.displayAccountForm ? "block" : "none",
                    }}
                  >
                    <div className="col-lg-6">
                      <div className="input-group">
                        <Button
                          onClick={this.modifyAccount}
                          style={{
                            background: "#009966",
                            marginRight: "10px",
                            border: "none",
                          }}
                        >
                          <b>Save</b>
                        </Button>

                        <Button
                          style={{
                            background: "white",
                            color: "black",
                            border: "1px solid black",
                          }}
                          onClick={this.toggleAccountForm}
                        >
                          <b>Cancel</b>
                        </Button>
                      </div>
                    </div>
                  </div>
                </form>
                <hr />
                <h5 style={{ padding: "10px" }}>
                  <b>New password</b>
                </h5>
                <p style={{ padding: "10px" }}>
                  To change your password please type in your old password and
                  new password twice
                </p>

                <Button
                  style={{
                    display: this.state.displayPasswordForm ? "none" : "block",
                    background: "white",
                    marginLeft: "10px",
                    color: "black",
                    border: "1px solid black",
                  }}
                  onClick={this.togglePasswordForm}
                >
                  <b>Create a new password</b>
                </Button>
                <div
                  style={{
                    display: this.state.displayPasswordForm ? "block" : "none",
                  }}
                >
                  <form className="form-horizontal">
                    <div className="form-group">
                      <div className="col-lg-4">
                        <label>
                          <b>Old Password</b>
                        </label>

                        <input
                          type="password"
                          className="form-control input-group-lg"
                          placeholder="Password"
                          aria-label="password"
                          name="currentpassword"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>
                    </div>
                    <div className="input-group">
                      <div className="col-lg-6">
                        <label>
                          <b>Password</b>
                        </label>

                        <input
                          type="password"
                          className="form-control input-group-lg"
                          placeholder="Password"
                          aria-label="password"
                          name="newpassword"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>
                      <div className="col-lg-6">
                        <label>
                          <b>Please retype your new password</b>
                        </label>

                        <input
                          type="password"
                          className="form-control form-control input-group-lg"
                          placeholder="Password"
                          aria-label="password"
                          name="retypepassword"
                          aria-describedby="basic-addon"
                          onChange={this.handleForm}
                        />
                      </div>
                    </div>
                    <br />

                    <div className="form-group">
                      <div className="col-lg-6">
                        <div className="input-group">
                          <Button
                            onClick={this.modifyPassword}
                            style={{
                              background: "#009966",
                              marginRight: "10px",
                              border: "none",
                            }}
                          >
                            <b>Save</b>
                          </Button>

                          <Button
                            style={{
                              background: "white",
                              color: "black",
                              border: "1px solid black",
                            }}
                            onClick={this.togglePasswordForm}
                          >
                            <b>Cancel</b>
                          </Button>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
                <br />
              </div>
            </div>
          </div>
          <ToastContainer />
        </div>
      </React.Fragment>
    );
  }
}

export default MyAccount;
