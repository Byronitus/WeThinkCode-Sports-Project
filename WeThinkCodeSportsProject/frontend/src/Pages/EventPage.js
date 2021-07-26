import React, {Component} from "react";
import YouTube from "react-youtube";
import Header from "../components/layouts/Header";
import MultipleLines from "../components/Map/MultipleLines";

export class EventPage extends Component {


    render() {
        const opts = {
            height: '390',
            width: '640',
            playerVars: {
                autoplay: 1,
            },
        };
        let text = "Sport : "+this.props.location.state.sportType+
        "\nEvent : "+this.props.location.state.Event+
        "\nLeague : "+this.props.location.state.League+
        "\nRound : "+this.props.location.state.Round+
        "\nDate : "+this.props.location.state.Date +
        "\nVenue : "+this.props.location.state.Venue +
        "\nCountry : "+this.props.location.state.Country+
        "\nDescription: "+this.props.location.state.Description;
        if (this.props.location.state.Video == null){
            return (
                <div className="container">
                    <Header/>
                    <div>
                        <MultipleLines text={text}/>
                    </div>
                </div>

            );
        }else {
            return (
                <div className="container">
                    <Header/>
                    <div>
                        <MultipleLines text={text}/>
                        <YouTube videoId={this.props.location.state.Video} opts={opts} onReady={this._onReady}/>
                    </div>
                </div>

            );
        }
    }

    _onReady(event) {
        // access to player in all event handlers via event.target
        event.target.pauseVideo();
    }
}


export default EventPage;