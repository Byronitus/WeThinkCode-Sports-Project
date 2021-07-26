import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import League from "../components/Map/League";
import TeamList from "../components/Map/TeamList";
import Season from "../components/Map/Season";
import ListOfLiveEvents from "../components/Map/ListOfLiveEvents";
import FixturesInfo from "../components/Info/FixturesInfo";
import ResultsInfo from "../components/Info/ResultsInfo";


export class ListOfTeamPage extends Component {


    constructor(props) {
        super(props);
        this.state = {TeamList:[], SeasonList:[], LiveEventList:[], PastEvents:[],  FutureEvents:[]}
    }


    componentDidMount() {
        if (this.props.location.state.SportName == "Soccer"){
            axios.get('/api/LeagueLiveScore/'+this.props.location.state.sportID)
                .then(response => this.setState({LiveEventList:response.data}))
        }
        console.log('/api/IncreaseCounter/'+this.props.location.state.LeagueName+"/"+this.props.location.state.SportName)
        axios.get('/api/IncreaseCounter/'+this.props.location.state.LeagueName+"/"+this.props.location.state.SportName)
        axios.get("/api/team/"+this.props.location.state.sportID)
            .then(response => this.setState({TeamList:response.data}))
        axios.get("/api/Seasons/"+this.props.location.state.sportID)
            .then(response => this.setState({SeasonList : response.data }))
        axios.get("/api/LeaguePastEvents/"+this.props.location.state.sportID)
            .then(response => this.setState({PastEvents:response.data}))
        axios.get("/api/LeagueFutureEvents/"+this.props.location.state.sportID)
            .then(response => this.setState({FutureEvents:response.data}))
    }


        render() {

        if (this.state.TeamList.length > 0 && this.state.SeasonList.length > 0 && this.state.LiveEventList.length > 0) {
            return (
                <div className="container">
                    <Header/>
                    {this.props.location.state.LeagueDescription}
                    <TeamList teams={this.state.TeamList}/>
                    Seasons:
                    <Season SeasonList={this.state.SeasonList} LeagueId={this.props.location.state.sportID}/>
                    Live Events:
                    <ListOfLiveEvents LiveEventList={this.state.LiveEventList}/>
                    Fixtures:
                    <FixturesInfo user={this.state.FutureEvents}/>
                    Results:
                    <ResultsInfo user={this.state.PastEvents}/>
                </div>
            );
        }else if (this.state.TeamList.length > 0 && this.state.SeasonList.length > 0){
            return (
                <div className="container">
                    <Header/>
                    {this.props.location.state.LeagueDescription}
                    <TeamList teams={this.state.TeamList}/>
                    Seasons:
                    <Season SeasonList={this.state.SeasonList} LeagueId={this.props.location.state.sportID}/>
                    Fixtures:
                    <FixturesInfo user={this.state.FutureEvents}/>
                    Results:
                    <ResultsInfo user={this.state.PastEvents}/>
                </div>
            );
        }else{
            return (<div> </div>);
        }
    }
}

export default ListOfTeamPage;