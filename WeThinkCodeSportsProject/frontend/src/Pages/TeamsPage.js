import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import ListOfPlayers from "../components/Map/ListOfPlayers";
import FixturesInfo from "../components/Info/FixturesInfo";
import ResultsInfo from "../components/Info/ResultsInfo";

export class TeamsPage extends Component {


    constructor(props) {
        super(props);
        this.state = {PlayerList:[],FutureEvents:[],PastEvents:[]}
    }


    componentDidMount() {
        axios.get("/api/Players/"+this.props.location.state.TeamID)
            .then(response => {
                this.setState({PlayerList:response.data});
            })
        axios.get("/api/TeamUpcomingEvents/"+this.props.location.state.TeamID)
            .then(response => {this.setState({FutureEvents:response.data});})
        axios.get("/api/TeamPastEvents/"+this.props.location.state.TeamID)
            .then(response => {this.setState({PastEvents:response.data});})
    }


    render() {
        if (this.state.PlayerList.length > 0) {
            return (
                <div className="container">
                    <Header/>
                    {this.props.location.state.TeamDescription}
                    <ListOfPlayers playerList={this.state.PlayerList}/>
                    Fixtures:
                    <FixturesInfo user={this.state.FutureEvents}/>
                    Results:
                    <ResultsInfo user={this.state.PastEvents}/>
                </div>
            );
        }
        else{
            return (<div> </div>);
        }
    }
}

export default TeamsPage;