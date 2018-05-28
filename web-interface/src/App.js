import React, { Component } from 'react';
import { NavLink, Switch, Route } from 'react-router-dom';

import './App.css';

import HomePage from './components/home-page/home-page.js';
import PanelPage from './components/panel-page/panel-page.js';

class App extends Component {
  render() {
    return (
        <Switch>
          <Route path="/CSV-Analytics" exact component={HomePage} ></Route>
          <Route path="/CSV-Analytics/data-visualization" component={PanelPage} ></Route>
        </Switch>
    );
  }
}

export default App;
