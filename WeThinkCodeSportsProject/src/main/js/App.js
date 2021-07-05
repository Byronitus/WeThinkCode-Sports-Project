import React, {Component, useCallback} from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router, Route, Switch, Link, Redirect} from "react-router-dom";
import Header from "../../../frontend/src/components/layouts/Header";
import Sports from "../../../frontend/src/components/Map/Sports";
import MainPage from "../../../frontend/src/Pages/MainPage";
import LeaguesPage from "../../../frontend/src/Pages/LeaguesPage";
import ListOfTeamPage from "../../../frontend/src/Pages/ListOfTeamPage";
import TeamsPage from "../../../frontend/src/Pages/TeamsPage";

export class App extends Component {
    render() {
        return (
            <Router>
                <Route exact path="/" component={MainPage}/>
                <Route exact path="/league" component={LeaguesPage}/>
                <Route exact path='/teams' component ={ListOfTeamPage}/>
                <Route exact path='/team' component ={TeamsPage}/>
            </Router>
        );
    }
}

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));