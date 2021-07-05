import React, {Component, useCallback} from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router, Route} from "react-router-dom";
import SportInfo from "../components/Info/SportInfo";
import axios from "axios";
import LeagueInfo from "../components/Info/LeagueInfo";
import Header from "../components/layouts/Header";
import League from "../components/Map/League";

export class LeaguesPage extends Component {

    constructor(props) {
        super(props);
        this.state = {leagueList:[]}
    }

    componentDidMount() {
        axios.get('/api/league/'+this.props.location.state.sport)
            .then(response => this.setState({leagueList:response.data}))
    }

    render() {
        return (
            <div className="container">
                <Header/>
                {this.props.location.state.Description}
                <League leagueList={this.state.leagueList}/>
            </div>

        );
    }
}

export default LeaguesPage;