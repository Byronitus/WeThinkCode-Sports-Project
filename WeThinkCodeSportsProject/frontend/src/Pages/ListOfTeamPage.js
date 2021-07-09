import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import League from "../components/Map/League";
import TeamList from "../components/Map/TeamList";
import Season from "../components/Map/Season";


export class ListOfTeamPage extends Component {


    constructor(props) {
        super(props);
        this.state = {TeamList:[], SeasonList:[]}
    }


    componentDidMount() {
        axios.get("/api/team/"+this.props.location.state.sportID)
            .then(response => this.setState({TeamList:response.data}))
        axios.get("/api/Seasons/"+this.props.location.state.sportID)
            .then(response => this.setState({SeasonList : response.data }))
    }


        render() {
        if (this.state.TeamList.length > 0 && this.state.SeasonList.length > 0) {
            // console.log(this.state.SeasonList)
            return (
                <div className="container">
                    <Header/>
                    {this.props.location.state.LeagueDescription}
                    <TeamList teams={this.state.TeamList}/>
                    <Season SeasonList={this.state.SeasonList} LeagueId={this.props.location.state.sportID}/>
                </div>
            );
        }else{
            return (<div> </div>);
        }
    }
}

export default ListOfTeamPage;