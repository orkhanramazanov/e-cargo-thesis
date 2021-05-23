import React from "react";
import { Route, Redirect } from "react-router-dom";

export const ClientRoute = ({ component, ...rest }) => {
  const isAuthed = sessionStorage.getItem("authenticated");
  const role = sessionStorage.getItem("role");

  return (
    <Route
      {...rest}
      exact
      render={(props) =>
        isAuthed && role === "ROLE_CLIENT" ? (
          <div>{React.createElement(component, props)}</div>
        ) : (
          <Redirect
            to={{
              pathname: "/login",
              state: { from: props.location },
            }}
          />
        )
      }
    />
  );
};

export const DriverRoute = ({ component, ...rest }) => {
  const isAuthed = sessionStorage.getItem("authenticated");
  const role = sessionStorage.getItem("role");

  return (
    <Route
      {...rest}
      exact
      render={(props) =>
        isAuthed && role === "ROLE_DRIVER" ? (
          <div>{React.createElement(component, props)}</div>
        ) : (
          <Redirect
            to={{
              pathname: "/driver/login",
              state: { from: props.location },
            }}
          />
        )
      }
    />
  );
};

export const AdminRoute = ({ component, ...rest }) => {
  const isAuthed = sessionStorage.getItem("authenticated");
  const role = sessionStorage.getItem("role");

  return (
    <Route
      {...rest}
      exact
      render={(props) =>
        isAuthed && role === "ROLE_ADMIN" ? (
          <div>{React.createElement(component, props)}</div>
        ) : (
          <Redirect
            to={{
              pathname: "/admin/login",
              state: { from: props.location },
            }}
          />
        )
      }
    />
  );
};
