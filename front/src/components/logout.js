import React from "react";
import history from "./browserhistory";
class Logout extends React.Component {
  componentDidMount() {
    sessionStorage.clear();
    history.push("/login");
  }

  render() {
    return <div></div>;
  }
}

export default Logout;
