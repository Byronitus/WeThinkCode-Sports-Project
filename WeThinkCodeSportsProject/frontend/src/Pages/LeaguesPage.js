import React, {Component, useCallback} from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router, Route} from "react-router-dom";
import SportInfo from "../components/Info/SportInfo";
import axios from "axios";
import LeagueInfo from "../components/Info/LeagueInfo";
import Header from "../components/layouts/Header";
import League from "../components/Map/League";
import ListOfListEvents from "../components/Map/ListOfLiveEvents";

export class LeaguesPage extends Component {

    constructor(props) {
        super(props);
        this.state = {leagueList:[],LiveEventsList:[]}
    }

    componentDidMount() {
        if (this.props.location.state.sport == "Soccer") {
            axios.get('/api/ListOfLiveScores/' + this.props.location.state.sport)
                .then(response => this.setState({LiveEventsList: response.data}))
        }
        axios.get('/api/league/'+this.props.location.state.sport)
            .then(response => this.setState({leagueList:response.data}))
    }

    render() {
        if (this.state.leagueList.length > 0 && this.state.LiveEventsList.length > 0) {
            return (
                <div className="container">
                    <Header/>
                    {this.props.location.state.Description}
                    <League leagueList={this.state.leagueList}/>
                    Live Events:
                    <ListOfListEvents LiveEventList={this.state.LiveEventsList}/>
                </div>

            );
        }else if (this.state.leagueList.length > 0) {
            return(<div className="container">
                <Header/>
                {this.props.location.state.Description}
                <League leagueList={this.state.leagueList}/>
            </div>);
        }else{
            return(<div></div>);
        }
    }
}

export default LeaguesPage;