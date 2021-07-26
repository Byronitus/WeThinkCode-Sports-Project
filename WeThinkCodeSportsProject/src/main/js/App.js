import React, {Component, useCallback} from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router, Route, Switch, Link, Redirect} from "react-router-dom";
import MainPage from "../../../frontend/src/Pages/MainPage";
import LeaguesPage from "../../../frontend/src/Pages/LeaguesPage";
import ListOfTeamPage from "../../../frontend/src/Pages/ListOfTeamPage";
import TeamsPage from "../../../frontend/src/Pages/TeamsPage";
import SearchPage from "../../../frontend/src/Pages/SearchPage";
import ListOfEventsPage from "../../../frontend/src/Pages/ListOfEventsPage";
import {EventPage} from "../../../frontend/src/Pages/EventPage";
import PlayerPage from "../../../frontend/src/Pages/PlayerPage";
import LiveScorePage from "../../../frontend/src/Pages/LiveScorePage";

export class App extends Component {
    render() {
        return (
            <Router>
                <Route exact path="/" component={MainPage}/>
                <Route exact path="/league" component={LeaguesPage}/>
                <Route exact path='/team' component ={TeamsPage}/>
                <Route exact path='/teams' component ={ListOfTeamPage}/>
                <Route exact path='/search' component = {SearchPage}/>
                <Route exact path='/ListOfEvents' component = {ListOfEventsPage}/>
                <Route exact path='/Events' component = {EventPage}/>
                <Route exact path='/Player' component = {PlayerPage}/>
                <Route exact path='/LiveScore' component = {LiveScorePage}/>
            </Router>
        );
    }
}

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));