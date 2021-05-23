import React, { Component } from "react";
import { Navbar } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
class DriverHeader extends Component {
  render() {
    return (
      <React.Fragment>
        <Navbar>
          <Navbar.Toggle />
          <Navbar.Collapse className="justify-content-end">
            <Navbar.Text>
              <Link to="/driver/login">
                <Button
                  style={{
                    color: "black",
                    border: "1px solid black",
                    background: "none",
                  }}
                >
                  Login
                  <FontAwesomeIcon
                    style={{ paddingLeft: "5px" }}
                    icon={faUser}
                  />
                </Button>
              </Link>
            </Navbar.Text>
          </Navbar.Collapse>
        </Navbar>
      </React.Fragment>
    );
  }
}

export default DriverHeader;
