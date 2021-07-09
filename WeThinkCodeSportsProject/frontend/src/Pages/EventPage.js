import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import ListOfEvents from "../components/Map/ListOfEvents";
import MultipleLines from "../components/Map/MultipleLines";

export class EventPage extends Component {


    render() {
        let text = "Sport : "+this.props.location.state.sportType+
        "\nEvent : "+this.props.location.state.Event+
        "\nLeague : "+this.props.location.state.League+
        "\nRound : "+this.props.location.state.Round+
        "\nDate : "+this.props.location.state.Date +
        "\nVenue : "+this.props.location.state.Venue +
        "\nCountry : "+this.props.location.state.Country;
        return (
            <div className="container">
                <Header/>
                <div>
                    <MultipleLines text={text}/>
                </div>
            </div>

        );
    }
}


export default EventPage;