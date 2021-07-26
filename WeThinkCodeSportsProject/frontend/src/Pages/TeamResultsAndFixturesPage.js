import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import ListOfPlayers from "../components/Map/ListOfPlayers";

export class TeamResultsAndFixturesPage extends Component {


    constructor(props) {
        super(props);
        this.state = {FutureEvents:[], PastEvents : []}
    }


    componentDidMount() {
        axios.get("/api/TeamUpcomingEvents/"+this.props.location.state.TeamID)
            .then(response => {this.setState({FutureEvents:response.data});})
        axios.get("/api/TeamPastEvents/"+this.props.location.state.TeamID)
            .then(response => {this.setState({PastEvents:response.data});})
    }


    render() {
        if (this.state.FutureEvents.length > 0 && this.state.PastEvents.length > 0) {
            return (
                <div className="container">
                    <Header/>
                    {this.props.location.state.TeamDescription}
                    <ListOfPlayers playerList={this.state.PlayerList}/>
                </div>
            );
        }
        else{
            return (<div> </div>);
        }
    }
}

export default TeamResultsAndFixturesPage;