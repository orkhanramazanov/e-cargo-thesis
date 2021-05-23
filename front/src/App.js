import React, { Component } from "react";
import { Router, Switch, Route } from "react-router-dom";
import LandingPage from "./components/landingpage";
import Login from "./components/login";
import ModifyReservation from "./components/modifyreservation";
import MyAccount from "./components/myaccount";
import NewReservation from "./components/newreservation";
import Register from "./components/register";
import Reservations from "./components/reservations";
import ResetPassword from "./components/resetpassword";
import history from "./components/browserhistory";
import Billing from "./components/billing";
import DriverBilling from "./components/truckdriverbilling";
import DriverLogin from "./components/truckdriverlogin";
import DriverRegister from "./components/truckdriverregister";
import DriverAccount from "./components/truckdriveraccount";
import DriverRequest from "./components/truckdriverrequest";
import DriverReservations from "./components/truckdriverreservations";
import AdminAccount from "./components/adminaccount";
import AdminLogin from "./components/adminlogin";
import AdminUsers from "./components/adminusers";
import AdminOrders from "./components/adminorders";
import AdminCountry from "./components/admineditcountry";
import Logout from "./components/logout";
import {
  ClientRoute,
  DriverRoute,
  AdminRoute,
} from "./components/privateRoutes";
import DriverTrucks from "./components/truckdrivertrucks";
class App extends Component {
  render() {
    return (
      <Router history={history}>
        <Switch>
          <Route exact path="/landingpage" component={LandingPage} />
          <Route exact path="/" component={LandingPage} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
          <ClientRoute exact path="/account" component={MyAccount} />
          <ClientRoute exact path="/reservations" component={Reservations} />
          <ClientRoute
            exact
            path="/newreservation/:id"
            component={NewReservation}
          />
          <ClientRoute exact path="/billing" component={Billing} />
          <DriverRoute exact path="/driver/billing" component={DriverBilling} />
          <Route exact path="/driver/login" component={DriverLogin} />
          <Route exact path="/driver/register" component={DriverRegister} />
          <DriverRoute exact path="/driver/account" component={DriverAccount} />
          <DriverRoute exact path="/driver/request" component={DriverRequest} />
          <AdminRoute exact path="/admin/account" component={AdminAccount} />
          <Route exact path="/admin/login" component={AdminLogin} />
          <AdminRoute exact path="/admin/users" component={AdminUsers} />
          <AdminRoute exact path="/admin/orders" component={AdminOrders} />
          <AdminRoute exact path="/admin/countries" component={AdminCountry} />
          <Route exact path="/logout" component={Logout} />
          <DriverRoute exact path="/driver/trucks" component={DriverTrucks} />
          <DriverRoute
            exact
            path="/driver/reservations"
            component={DriverReservations}
          />
          <ClientRoute
            exact
            path="/modifyreservation/:orderId/:truckId"
            component={ModifyReservation}
          />
          <Route exact path="/forgotpassword" component={ResetPassword} />
          <Route path="*" component={LandingPage} />
        </Switch>
      </Router>
    );
  }
}

export default App;
