import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import TeamList from "../components/Map/TeamList";
import Sports from "../components/Map/Sports";
import ListOfPlayers from "../components/Map/ListOfPlayers";

export class TeamsPage extends Component {


    constructor(props) {
        super(props);
        this.state = {PlayerList:[]}
    }


    componentDidMount() {
        axios.get("/api/Players/"+this.props.location.state.TeamID)
            .then(response => {
                console.log(response.data);
                this.setState({PlayerList:response.data});
                console.log(this.state.PlayerList)
            })
    }


    render() {
        if (this.state.PlayerList.length > 0) {
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

export default TeamsPage;