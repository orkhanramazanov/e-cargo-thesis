import React, { Component } from "react";
import { Navbar } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUser,
  faTruck,
  faUserCheck,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
import history from "./browserhistory";

class Header extends Component {
  constructor(props) {
    super(props);

    this.state = {
      buttons: "",
      heading: "",
    };
  }

  componentDidMount() {
    if (sessionStorage.getItem("role") === "ROLE_DRIVER") {
      this.setState({
        heading: <Navbar.Brand href="#">Welcome Driver</Navbar.Brand>,
        buttons: (
          <Link to="/driver/account">
            <Button
              style={{
                color: "black",
                border: "1px solid black",
                background: "none",
                marginRight: "5px",
              }}
            >
              My Account
              <FontAwesomeIcon style={{ paddingLeft: "5px" }} icon={faUser} />
            </Button>
          </Link>
        ),
      });
    }
    if (sessionStorage.getItem("role") === "ROLE_ADMIN") {
      this.setState({
        heading: <Navbar.Brand href="#">Welcome Admin</Navbar.Brand>,
        buttons: (
          <Link to="/admin/account">
            <Button
              style={{
                color: "black",
                border: "1px solid black",
                background: "none",
                marginRight: "5px",
              }}
            >
              My Account
              <FontAwesomeIcon style={{ paddingLeft: "5px" }} icon={faUser} />
            </Button>
          </Link>
        ),
      });
    } else if (sessionStorage.getItem("role") === "ROLE_CLIENT") {
      this.setState({
        heading: <Navbar.Brand href="/landingpage">Welcome Client</Navbar.Brand>,
        buttons: (
          <Link to="/account">
            <Button
              style={{
                color: "black",
                border: "1px solid black",
                background: "none",
                marginRight: "5px",
              }}
            >
              My Account
              <FontAwesomeIcon style={{ paddingLeft: "5px" }} icon={faUser} />
            </Button>
          </Link>
        ),
      });
    } else if (history.location.pathname.indexOf("driver") === 1) {
      this.setState({
        heading: <Navbar.Brand href="#">Welcome Driver</Navbar.Brand>,
      });
    } else if (history.location.pathname.indexOf("admin") === 1) {
      this.setState({
        heading: <Navbar.Brand href="#">Welcome Admin</Navbar.Brand>,
      });
    } else {
      this.setState({
        heading: <Navbar.Brand href="#">Welcome</Navbar.Brand>,
      });
    }
  }
  render() {
    return (
      <React.Fragment>
        <Navbar>
          {this.state.heading}
          <Navbar.Toggle />
          <Navbar.Collapse className="justify-content-end">
            <Navbar.Text>
              {sessionStorage.getItem("authenticated") ? (
                <div>{this.state.buttons}</div>
              ) : (
                <div>
                  <Link to="/login">
                    <Button
                      style={{
                        color: "black",
                        border: "1px solid black",
                        background: "none",
                        marginRight: "5px",
                      }}
                    >
                      Client Login
                      <FontAwesomeIcon
                        style={{ paddingLeft: "5px" }}
                        icon={faUser}
                      />
                    </Button>
                  </Link>
                  <Link to="/driver/login">
                    <Button
                      style={{
                        color: "black",
                        border: "1px solid black",
                        background: "none",
                        marginRight: "5px",
                      }}
                    >
                      Driver Login
                      <FontAwesomeIcon
                        style={{ paddingLeft: "5px" }}
                        icon={faTruck}
                      />
                    </Button>
                  </Link>
                  <Link to="/admin/login">
                    <Button
                      style={{
                        color: "black",
                        border: "1px solid black",
                        background: "none",
                      }}
                    >
                      Admin Login
                      <FontAwesomeIcon
                        style={{ paddingLeft: "5px" }}
                        icon={faUserCheck}
                      />
                    </Button>
                  </Link>
                </div>
              )}
            </Navbar.Text>
          </Navbar.Collapse>
        </Navbar>
      </React.Fragment>
    );
  }
}

export default Header;
