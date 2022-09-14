import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Router, Route, Link, Switch } from 'react-router-dom';
import { createBrowserHistory } from 'history';
import { CookiesProvider } from 'react-cookie';

import HomePage from './pages/home/home.page';
import LoginPage from './pages/login/login.page';
import RegisterPage from './pages/register/register.page';
import ProfilePage from './pages/profile/profile.page';
import User from './models/user';
import UserService from './services/user.service';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faUser,
  faUserPlus,
  faSignInAlt,
  faHome,
  faSignOutAlt
} from '@fortawesome/free-solid-svg-icons';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      history: createBrowserHistory(),
      user: new User()
    };
  }

  componentDidMount() {
    UserService.currentUser.subscribe((data) => {
      this.setState({ user: data });
    });
  }

  logout() {
    UserService.logOut().then(
      (data) => {
        this.state.history.push('/');
      },
      (error) => {
        this.setState({ errorMessage: 'Unexpected error occurred.' });
      }
    );
  }

  render() {
    const { history, user } = this.state;
    return (
      <CookiesProvider>
        <Router history={history}>
          <div>
            {user && (
              <nav className="navbar navbar-expand navbar-dark bg-dark">
                <a className="navbar-brand" href="https://reactjs.org">
                  <img src={logo} className="App-logo" alt="logo" />
                  React
                </a>
                <div className="navbar-nav mr-auto">
                  <Link to="/home" className="nav-item nav-link">
                    <FontAwesomeIcon icon={faHome} /> Home
                  </Link>
                </div>
                <div className="navbar-nav ml-auto">
                  <Link to="/profile" className="nav-item nav-link">
                    <FontAwesomeIcon icon={faUser} /> {user.name}
                  </Link>
                  <Link onClick={() => this.logout()} className="nav-item nav-link">
                    <FontAwesomeIcon icon={faSignOutAlt} /> LogOut
                  </Link>
                </div>
              </nav>
            )}
            {!user && (
              <nav className="navbar navbar-expand navbar-dark bg-dark">
                <a className="navbar-brand" href="https://reactjs.org">
                  <img src={logo} className="App-logo" alt="logo" />
                  React
                </a>
                <div className="navbar-nav mr-auto">
                  <Link to="/home" className="nav-item nav-link">
                    <FontAwesomeIcon icon={faHome} /> Home
                  </Link>
                </div>
                <div className="navbar-nav ml-auto">
                  <Link to="/register" className="nav-item nav-link">
                    <FontAwesomeIcon icon={faUserPlus} /> Register
                  </Link>
                  <Link to="/login" className="nav-item nav-link">
                    <FontAwesomeIcon icon={faSignInAlt} /> Login
                  </Link>
                </div>
              </nav>
            )}
            <div className="container">
              <Switch>
                <Route exact path="/" component={HomePage} />
                <Route exact path="/home" component={HomePage} />
                <Route exact path="/login" component={LoginPage} />
                <Route exact path="/register" component={RegisterPage} />
                <Route exact path="/profile" component={ProfilePage} />
              </Switch>
            </div>
          </div>
        </Router>
      </CookiesProvider>
    );
  }
}

export default App;
