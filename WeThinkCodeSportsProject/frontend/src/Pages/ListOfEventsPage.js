import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import Search from "../components/Map/Search";
import ListOfEvents from "../components/Map/ListOfEvents";

export class ListOfEventsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            events:[
            ]
        }
    }

    componentDidMount() {
        axios.get('/api/Event/' + this.props.location.state.LeagueId + '/' + this.props.location.state.Season)
            .then(response => {this.setState({events:response.data});})
    }


    render() {
        return (
            <div className="container">
                <Header/>
                <ListOfEvents EventList={this.state.events}/>
            </div>

        );
    }
}


export default ListOfEventsPage;